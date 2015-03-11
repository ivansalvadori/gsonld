package br.com.ivansalvadori.gsonld.test;

import java.util.Date;

import br.com.ivansalvadori.gsonld.SemanticClass;
import br.com.ivansalvadori.gsonld.SemanticProperty;
import br.com.ivansalvadori.gsonld.Vocabulary;

@Vocabulary(prefix="schema", id="http://schema.org/")
@SemanticClass("schema:Person")
public class JohnContext {
	
	@SemanticProperty("schema:familyName")
	private String surname;
	
	@SemanticProperty("schema:givenName")
	private String name;
	
	@SemanticProperty("schema:birthDate")
	private Date bd;
	
}
