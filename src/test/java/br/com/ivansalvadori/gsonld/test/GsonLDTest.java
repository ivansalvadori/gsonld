package br.com.ivansalvadori.gsonld.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ivansalvadori.gsonld.GsonLD;
import br.com.ivansalvadori.gsonld.GsonLDbyGson;

import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class GsonLDTest {
	
	@Test
	public void fromStringPlainPropertiesToObject() throws Exception {
		String sampleFileAsString = getSampleFileAsString("JohnPlainProperties.jsonld");
		GsonLD gsonLD = new GsonLD();
		JohnPlain john = gsonLD.fromJsonLD(sampleFileAsString, JohnPlain.class);
		
		JohnPlain johnPlainExpected = new JohnPlain("Deer", "John", "2015-03-04");
		org.junit.Assert.assertEquals(johnPlainExpected, john);
	}
	
	@Test
	public void fromStringPlainPropertiesComplexToObject() throws Exception {
		String sampleFileAsString = getSampleFileAsString("JoaoPlainProperties.jsonld");
		Object fromString = JsonUtils.fromString(sampleFileAsString);
		JoaoPlain joao = new GsonLD().fromJsonLD(sampleFileAsString, JoaoPlain.class);
		
		System.out.println(joao);
	}
	

	private String getSampleFileAsString(String filename) throws IOException, JsonLdError   {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		Object jsonObject = JsonUtils.fromInputStream(inputStream);
		Map<String, String> context = new HashMap<String, String>();
		JsonLdOptions options = new JsonLdOptions();
		Object compact = JsonLdProcessor.compact(jsonObject, context, options);
		String jsonLD = JsonUtils.toPrettyString(jsonObject);
		return jsonLD;
	}

}
