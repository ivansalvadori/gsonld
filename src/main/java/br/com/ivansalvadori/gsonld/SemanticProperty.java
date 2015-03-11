package br.com.ivansalvadori.gsonld;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SemanticProperty {

    String value() default "";
    
    String prefix() default "";
    
    String term() default "";

    boolean required() default false;

    boolean readonly() default false;

    boolean writeonly() default false;

}
