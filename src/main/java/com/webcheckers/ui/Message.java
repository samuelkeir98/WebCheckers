package com.webcheckers.ui;

import java.io.Serializable;

/**
 * Messages sent to JSON object to validate game moves
 */
public class Message implements Serializable{

    /**
     * Type of message
     */
    public enum Type{
        info,
        error
    }

    /** contents of message */
    private String text;

    /** type of message */
    private Type type;

    /**
     * Message to be sent as move validation
     * @param text message content
     * @param type type of message
     */
    public Message(String text, Type type){
        this.text = text;
        this.type = type;
    }

    /**
     * @return type of message
     */
    public Type getType() {
        return type;
    }

    /**
     * @return message content
     */
    public String getText() {
        return text;
    }
}
