package br.com.srs.gsonld.exception;

@SuppressWarnings("serial")
public class NoSemanticObjectException extends RuntimeException{

	public NoSemanticObjectException(Object o) {
		super(String.format("Object class must be annotated with @SemanticClass. Class [%s]", o.getClass()));
	}
}
