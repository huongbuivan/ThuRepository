package com.example.java_project.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Ensures the annotation is available at runtime for reflection.
@Target(ElementType.FIELD) //Specifies that the annotation can only be applied to fields
public @interface Encrypted {
}
