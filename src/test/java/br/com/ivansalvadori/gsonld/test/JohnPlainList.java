package br.com.ivansalvadori.gsonld.test;

import java.util.ArrayList;
import java.util.List;

import br.com.ivansalvadori.gsonld.SemanticClass;

@SemanticClass("Collection")
public class JohnPlainList {

    private final List<JohnPlain> list = new ArrayList<>();

    public void addTolist(JohnPlain johnPlain) {
        this.list.add(johnPlain);

    }

}
