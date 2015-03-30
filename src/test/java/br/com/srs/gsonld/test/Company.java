package br.com.srs.gsonld.test;

import br.com.srs.gsonld.SemanticClass;
import br.com.srs.gsonld.SemanticProperty;
import br.com.srs.gsonld.Vocabulary;

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
