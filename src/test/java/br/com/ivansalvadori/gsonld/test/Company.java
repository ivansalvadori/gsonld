package br.com.ivansalvadori.gsonld.test;

import br.com.ivansalvadori.gsonld.SemanticClass;
import br.com.ivansalvadori.gsonld.SemanticProperty;
import br.com.ivansalvadori.gsonld.Vocabulary;

@Vocabulary(prefix="foaf", id="http://xmlns.com/foaf/0.1/")
@SemanticClass("foaf:Company")
public class Company {
	
	@SemanticProperty("foaf:name")
	private String nome;

	public Company(String nome) {
		super();
		this.nome = nome;
	}
	
	

}
