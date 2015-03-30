package br.com.srs.gsonld.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.srs.gsonld.GsonLD;
import br.com.srs.gsonld.SemanticClass;

public class ObjectWithTransientFields {

	private GsonLD gsonLD = new GsonLD();

	@SemanticClass(value = "testStaticTransient")
	static class TestWithTransientField {

		transient String string = "string";
		
		transient int integer = 7;
		
		Byte b = 26;
	}

	@Test
	public void serializeObjectWithStaticFields() {
		String expectedValue = "{\"b\":26,\"@type\":\"testStaticTransient\"}";
		String jsonLD = gsonLD.toJsonLD(new TestWithTransientField());

		Assert.assertEquals(expectedValue, jsonLD);
	}
}
