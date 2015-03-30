package br.com.srs.gsonld.test;

import br.com.srs.gsonld.SemanticClass;
import br.com.srs.gsonld.SemanticProperty;

@SemanticClass("http://schema.org/Place")
public class Residencia {

    public Residencia() {
        // TODO Auto-generated constructor stub
    }

    public Residencia(String descricao, String numeroTelefone, Endereco localizacao) {
        super();
        this.descricao = descricao;
        this.numeroTelefone = numeroTelefone;
        this.localizacao = localizacao;
    }

    @SemanticProperty("http://schema.org/name")
    private String descricao;

    @SemanticProperty("http://schema.org/telephone")
    private String numeroTelefone;

    @SemanticProperty("http://schema.org/address")
    private Endereco localizacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
        result = prime * result + ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
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
        Residencia other = (Residencia) obj;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (localizacao == null) {
            if (other.localizacao != null)
                return false;
        } else if (!localizacao.equals(other.localizacao))
            return false;
        if (numeroTelefone == null) {
            if (other.numeroTelefone != null)
                return false;
        } else if (!numeroTelefone.equals(other.numeroTelefone))
            return false;
        return true;
    }

}
