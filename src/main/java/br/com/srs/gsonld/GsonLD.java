package br.com.srs.gsonld;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonLD {

	private static final String NULL_OBJECT_JSON = "null";
	
    public String toJsonLD(Object src) {
    	if(src == null){
    		return NULL_OBJECT_JSON;
    	}

        JsonLdDocument jsonLdDocument;
        try {
            jsonLdDocument = serializeObject(src);
            return jsonLdDocument.toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonLdDocument serializeObject(Object src) throws IllegalArgumentException, IllegalAccessException {
        JsonLdDocument jsonLdDocument = new JsonLdDocument();
        if (src.getClass().isAnnotationPresent(SemanticClass.class)) {
            String objectSemanticType = src.getClass().getAnnotation(SemanticClass.class).value();
            jsonLdDocument.addProperties("@type", objectSemanticType);

            Field[] declaredFields = src.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
            	if(Modifier.isStatic(field.getModifiers())){
            		continue;
            	}
            	if(Modifier.isTransient(field.getModifiers())){
            		continue;
            	}
                field.setAccessible(true);
                if (!field.getType().isAnnotationPresent(SemanticClass.class)) {
                    if (field.isAnnotationPresent(Id.class)) {
                        jsonLdDocument.addProperties("@id", field.get(src));
                    } else if (field.get(src) instanceof List<?>) {
                        return serializeList(field.get(src), field.getName());
                    } else {
                        jsonLdDocument.addProperties(field.getName(), field.get(src));
                    }
                } else if (field.getType().isAnnotationPresent(SemanticClass.class)) {
                    JsonLdDocument innerSemanticObject = serializeObject(field.get(src));
                    // POG initiate
                    innerSemanticObject.addProperties("@context", innerSemanticObject.getContext());
                    // POG applied successfully
                    jsonLdDocument.addProperties(field.getName(), innerSemanticObject.getProperties());
                }
                if (field.isAnnotationPresent(SemanticProperty.class)) {
                    jsonLdDocument.addContext(field.getName(), field.getAnnotation(SemanticProperty.class).value());
                }
            }

            return jsonLdDocument;
        }

        if (src instanceof List<?>) {
            return serializeList(src, "@graph");
        }

        return null;
    }

    private JsonLdDocument serializeList(Object src, String listName) throws IllegalAccessException {
        if (src instanceof List<?>) {
            JsonLdDocument jsonLdDocument = new JsonLdDocument();
            List<?> list = (List<?>) src;
            List<JsonObject> semanticList = new ArrayList<JsonObject>();
            for (Object object : list) {
                JsonLdDocument serializedItem = serializeObject(object);

                JsonObject jsonLd = new JsonObject();

                if (!serializedItem.getContext().isEmpty()) {
                    jsonLd.add("@context", new Gson().toJsonTree(serializedItem.getContext()));
                }

                Set<String> keySet = serializedItem.getProperties().keySet();
                for (String propertyName : keySet) {
                    JsonElement element = new Gson().toJsonTree(serializedItem.getProperties().get(propertyName));
                    jsonLd.add(propertyName, element);
                }
                semanticList.add(jsonLd);

            }
            jsonLdDocument.addProperties(listName, semanticList);
            return jsonLdDocument;
        }

        return null;
    }

    public <T> T fromJsonLD(String jsonld, Class<T> classOfT) {
    	//if the JSON value is null, instead of an empty object, we return a null value.
    	if(StringUtils.equalsIgnoreCase(NULL_OBJECT_JSON, jsonld)){
    		return null;
    	}
        try {
            Map<String, String> context = new HashMap<String, String>();
            JsonLdOptions options = new JsonLdOptions();

            Object fromString = JsonUtils.fromString(jsonld);
            Object compact = JsonLdProcessor.compact(fromString, context, options);
            if (compact instanceof Map) {
                Map<?, ?> jsonLDProperties = (Map<?, ?>) compact;
                return mapJsonLDtoObject(jsonLDProperties, classOfT);
            }
        } catch (IOException | JsonLdError | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    private <T> T mapJsonLDtoObject(Map<?, ?> jsonLDProperties, Class<T> classOfT) throws InstantiationException, IllegalAccessException {
        // create a map of all semantic properties <semantic term, FIELD>
        Map<String, Field> semanticFields = new HashMap<>();
        Field[] declaredFields = classOfT.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(SemanticProperty.class)) {
                String semanticTerm = field.getAnnotation(SemanticProperty.class).value();
                semanticFields.put(semanticTerm, field);
            }
        }

        // create a object setting the jsonld properties into object fields T
        T newInstance = classOfT.newInstance();
        Set<?> semanticTerms = jsonLDProperties.keySet();
        for (Object semanticTerm : semanticTerms) {
            Field semanticField = semanticFields.get(semanticTerm);

            if (semanticField != null && !isSemanticClass(semanticField)) {
                semanticField.set(newInstance, jsonLDProperties.get(semanticTerm));
            } else if (semanticField != null && isSemanticClass(semanticField)) {
                Map<?, ?> innerClassProperties = (Map<?, ?>) jsonLDProperties.get(semanticTerm);
                Object innerMappedObject = mapJsonLDtoObject(innerClassProperties, semanticField.getType());
                semanticField.set(newInstance, innerMappedObject);
            }
        }

        return newInstance;
    }

    private boolean isSemanticClass(Field field) {
        return field.getType().isAnnotationPresent(SemanticClass.class);
    }

}
