package br.com.ivansalvadori.gsonld;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class GsonLD {

    public String toJsonLD() {
        return null;
    }

    public <T> T fromJsonLD(String jsonld, Class<T> classOfT) throws InstantiationException, IllegalAccessException, JsonParseException, IOException {

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
        Object fromString = JsonUtils.fromString(jsonld);
        if (fromString instanceof Map) {
            Map<?, ?> jsonLDProperties = (Map<?, ?>) fromString;
            Set<?> semanticTerms = jsonLDProperties.keySet();
            for (Object semanticTerm : semanticTerms) {
                Field semanticField = semanticFields.get(semanticTerm);
                if (semanticField != null && !isSemanticClass(semanticField)) {
                    semanticField.set(newInstance, jsonLDProperties.get(semanticTerm));
                }
            }
        }

        return newInstance;

    }

    private <T> Object mapJsonLDtoObject(Map<?, ?> jsonLDProperties, Class<T> classOfT) throws InstantiationException, IllegalAccessException {
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
            }
        }

        return newInstance;
    }

    private boolean isSemanticClass(Field field) {
        return field.getType().isAnnotationPresent(SemanticClass.class);
    }

}
