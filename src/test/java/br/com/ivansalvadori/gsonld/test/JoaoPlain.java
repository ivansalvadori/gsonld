package br.com.ivansalvadori.gsonld.test;

import br.com.ivansalvadori.gsonld.SameAs;
import br.com.ivansalvadori.gsonld.SemanticClass;
import br.com.ivansalvadori.gsonld.SemanticProperty;

import com.google.gson.Gson;

@SemanticClass("http://schema.org/Person")
@SameAs("http://xmlns.com/foaf/0.1/Person")
public class JoaoPlain {

    public JoaoPlain() {
        // TODO Auto-generated constructor stub
    }

    @SemanticProperty("http://schema.org/familyName")
    @SameAs("http://xmlns.com/foaf/0.1/surname")
    private String sobrenome;

    @SemanticProperty("http://schema.org/givenName")
    private String nome;

    @SemanticProperty("http://schema.org/birthDate")
    private String dataNascimento;

    @SemanticProperty("http://schema.org/homeLocation")
    private Residencia residencia;

    @SemanticProperty("http://schema.org/age")
    private int idade;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
