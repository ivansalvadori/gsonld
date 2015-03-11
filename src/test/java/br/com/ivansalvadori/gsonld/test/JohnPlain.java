package br.com.ivansalvadori.gsonld.test;


import com.google.gson.Gson;

import br.com.ivansalvadori.gsonld.SemanticClass;
import br.com.ivansalvadori.gsonld.SemanticProperty;

@SemanticClass("http://schema.org/Person")
public class JohnPlain {
	
	public JohnPlain() {
		// TODO Auto-generated constructor stub
	}
	
		
	public JohnPlain(String surname, String name, String bd) {
		super();
		this.surname = surname;
		this.name = name;
		this.bd = bd;
	}

	@SemanticProperty("http://schema.org/familyName")
	private String surname;
	
	@SemanticProperty("http://schema.org/givenName")
	private String name;
	
	@SemanticProperty("http://schema.org/birthDate")
	private String bd;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bd == null) ? 0 : bd.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		JohnPlain other = (JohnPlain) obj;
		if (bd == null) {
			if (other.bd != null)
				return false;
		} else if (!bd.equals(other.bd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	

	
}
