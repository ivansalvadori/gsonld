package br.com.srs.gsonld.test;

import java.util.ArrayList;
import java.util.List;

import br.com.srs.gsonld.SemanticClass;

@SemanticClass("Collection")
public class JohnPlainList {

    private final List<JohnPlain> list = new ArrayList<>();

    public void addTolist(JohnPlain johnPlain) {
        this.list.add(johnPlain);

    }

}
