package br.com.srs.gsonld.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.srs.gsonld.GsonLD;

public class NullObjectTest {

	private GsonLD gsonLD = new GsonLD();
	
	@Test
	public void testNullObjectToJson() {
		String expectedValue = "null";
		String json = gsonLD.toJsonLD(null);

		Assert.assertEquals(expectedValue, json);
	}
	
	@Test
	public void testFromNullToObject(){
		//it can be any class here, right?
		Object object = gsonLD.fromJsonLD("null", Object.class);
		Assert.assertNull(object);
	}
}
