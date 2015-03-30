package br.com.srs.gsonld.test;

import br.com.srs.gsonld.SemanticClass;
import br.com.srs.gsonld.SemanticProperty;

@SemanticClass("http://schema.org/PostalAddress")
public class Endereco {

    public Endereco() {
        // TODO Auto-generated constructor stub
    }

    public Endereco(String estado, String cidade, String rua, String cep) {
        super();
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.cep = cep;
    }

    @SemanticProperty("http://schema.org/addressRegion")
    private String estado;

    @SemanticProperty("http://schema.org/addressLocality")
    private String cidade;

    @SemanticProperty("http://schema.org/streetAddress")
    private String rua;

    @SemanticProperty("http://schema.org/postalCode")
    private String cep;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
        Endereco other = (Endereco) obj;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (rua == null) {
            if (other.rua != null)
                return false;
        } else if (!rua.equals(other.rua))
            return false;
        return true;
    }

}
