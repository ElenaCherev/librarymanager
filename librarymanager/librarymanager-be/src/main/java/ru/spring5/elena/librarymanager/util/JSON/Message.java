package ru.spring5.elena.librarymanager.util.JSON;

public class Message {
    private String type;
    private String message;


    public Message(String type, String message) {
        super();
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
