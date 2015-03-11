package br.com.ivansalvadori.gsonld;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import javassist.util.HotSwapper;
import br.com.ivansalvadori.gsonld.test.JoaoPlain;

import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class GsonLDbyGson {

	public String toJsonLD() {
		return null;
	}

	public <T> T fromJsonLD(String jsonld, Class<T> classOfT) {		

		try {

			ClassPool cp = ClassPool.getDefault();
			CtClass cc = cp.get(classOfT.getName());
			// Without the call to "makePackage()", package information is lost
			cp.makePackage(cp.getClassLoader(), "br.com.ivansalvadori.gsonld.test.modified");
			
			Field[] declaredFields = classOfT.getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				if (isSemanticField(field)) {
					String semanticTerm = field.getAnnotation(SemanticProperty.class).value();
					addAnnotation(cc, field.getName(), semanticTerm);
				}
			}
			cc.setName(UUID.randomUUID().toString());
			Class<T> c = cc.toClass();
			
			Gson gson = new Gson();
			T fromJson = gson.fromJson(jsonld, c);
			
			T newInstance = classOfT.newInstance();
			
			
			Field[] fromJsonFields = fromJson.getClass().getDeclaredFields();
			for (Field fromJsonField : fromJsonFields) {
				fromJsonField.setAccessible(true);
				Object fromJsonValue = fromJsonField.get(fromJson);
				
				Field[] newInstanceFields = newInstance.getClass().getDeclaredFields();
				for (Field newInstanceField : newInstanceFields) {
					newInstanceField.setAccessible(true);
					if(newInstanceField.getName().equals(fromJsonField.getName())){
						newInstanceField.set(newInstance, fromJsonValue);				
					}
				}
				
			}			
			
			return newInstance;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		

	}

	private boolean isSemanticField(Field field) {
		return field.isAnnotationPresent(SemanticProperty.class);
	}

	public static void addAnnotation(CtClass clazz, String fieldName, String serializedName) throws Exception {
		ClassFile cfile = clazz.getClassFile();
		ConstPool cpool = cfile.getConstPool();
		CtField cfield = clazz.getField(fieldName);

		AnnotationsAttribute attr = new AnnotationsAttribute(cpool,	AnnotationsAttribute.visibleTag);
		Annotation annot = new Annotation("com.google.gson.annotations.SerializedName", cpool);
		annot.addMemberValue("value", new StringMemberValue(serializedName, cfile.getConstPool()));
		attr.addAnnotation(annot);
		cfield.getFieldInfo().addAttribute(attr);
	}

}
