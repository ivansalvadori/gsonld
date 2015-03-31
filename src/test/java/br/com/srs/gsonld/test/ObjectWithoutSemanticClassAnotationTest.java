package br.com.srs.gsonld.test;

import org.junit.Test;

import br.com.srs.gsonld.GsonLD;
import br.com.srs.gsonld.exception.NoSemanticObjectException;

public class ObjectWithoutSemanticClassAnotationTest {
	
	private GsonLD gsonLD = new GsonLD();

	static class TestWithPrivateField{
		int integer = 0;
		Byte b= 3;
	}
	
	@Test(expected=NoSemanticObjectException.class)
	public void serializeObjectWithStaticFields(){
		gsonLD.toJsonLD(new TestWithPrivateField());
	}

}
