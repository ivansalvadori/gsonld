package br.com.srs.gsonld.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.srs.gsonld.GsonLD;
import br.com.srs.gsonld.SemanticClass;

public class ObjectWithStaticFieldsTest {
	
	private GsonLD gsonLD = new GsonLD();

	@SemanticClass(value="testStatic")
	static class TestWithPrivateField{
		static String shouldBeIgnored = "value";

		String string;
		int integer = 0;
		Byte b= 3;
	}
	
	@Test
	public void serializeObjectWithStaticFields(){
		String expectedValue = "{\"b\":3,\"@type\":\"testStatic\",\"integer\":0}";
		String jsonLD = gsonLD.toJsonLD(new TestWithPrivateField());
		
		Assert.assertEquals(expectedValue, jsonLD);
	}

}
