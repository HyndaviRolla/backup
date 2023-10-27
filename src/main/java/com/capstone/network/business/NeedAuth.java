package com.capstone.network.business;
 

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAuth {
    String loginPage() 
    default "/login";
}