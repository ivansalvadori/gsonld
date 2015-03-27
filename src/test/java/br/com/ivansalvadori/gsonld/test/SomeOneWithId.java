package br.com.ivansalvadori.gsonld.test;

import br.com.ivansalvadori.gsonld.Id;
import br.com.ivansalvadori.gsonld.SameAs;
import br.com.ivansalvadori.gsonld.SemanticClass;

import com.google.gson.Gson;

@SemanticClass("http://schema.org/Person")
@SameAs("http://xmlns.com/foaf/0.1/Person")
public class SomeOneWithId {

    public SomeOneWithId() {
        // TODO Auto-generated constructor stub
    }

    public SomeOneWithId(String someId, String nome) {
        super();
        this.someId = someId;
        this.nome = nome;
    }

    @Id
    private String someId;

    // @SemanticProperty("http://schema.org/givenName")
    private String nome;

    public String getSomeId() {
        return someId;
    }

    public void setSomeId(String someId) {
        this.someId = someId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
