package br.com.ivansalvadori.gsonld.test;

import br.com.ivansalvadori.gsonld.SemanticClass;
import br.com.ivansalvadori.gsonld.SemanticProperty;

@SemanticClass("http://schema.org/Place")
public class Residencia {
	
	@SemanticProperty("http://schema.org/name")
	private String localizacao;
	
	@SemanticProperty("http://schema.org/telephone")
	private String numeroTelefone;

}
