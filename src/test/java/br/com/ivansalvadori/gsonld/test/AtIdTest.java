package br.com.ivansalvadori.gsonld.test;

import org.junit.Test;

import br.com.ivansalvadori.gsonld.GsonLD;

public class AtIdTest {

    @Test
    public void serializeWithIdNotation() {
        SomeOneWithId someOneWithId = new SomeOneWithId("http://example.com", "Ada");
        System.out.println(new GsonLD().toJsonLD(someOneWithId));
    }

}
