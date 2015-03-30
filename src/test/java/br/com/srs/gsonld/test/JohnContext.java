package br.com.srs.gsonld.test;

import java.util.Date;

import br.com.srs.gsonld.SemanticClass;
import br.com.srs.gsonld.SemanticProperty;
import br.com.srs.gsonld.Vocabulary;

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
