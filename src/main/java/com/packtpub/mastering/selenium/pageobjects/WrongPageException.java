package com.packtpub.mastering.selenium.pageobjects;

public class WrongPageException extends RuntimeException{
    public WrongPageException(String message){
        super(message);
    }
}
