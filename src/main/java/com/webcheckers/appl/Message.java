package com.webcheckers.appl;

import java.io.Serializable;

public class Message implements Serializable{

    public enum Type{
        info,
        error
    }

    private String text;
    private Type type;

    public Message(String text, Type type){
        this.text = text;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
