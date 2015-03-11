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

    public JoaoPlain(String sobrenome, String nome, String dataNascimento, Residencia residencia, long cpf) {
        super();
        this.sobrenome = sobrenome;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.residencia = residencia;
        this.cpf = cpf;
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

    @SemanticProperty("http://schema.org/taxID")
    private long cpf;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cpf ^ (cpf >>> 32));
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((residencia == null) ? 0 : residencia.hashCode());
        result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JoaoPlain other = (JoaoPlain) obj;
        if (cpf != other.cpf)
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (residencia == null) {
            if (other.residencia != null)
                return false;
        } else if (!residencia.equals(other.residencia))
            return false;
        if (sobrenome == null) {
            if (other.sobrenome != null)
                return false;
        } else if (!sobrenome.equals(other.sobrenome))
            return false;
        return true;
    }

}
