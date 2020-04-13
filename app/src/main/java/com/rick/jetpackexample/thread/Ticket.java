package com.rick.jetpackexample.thread;

public class Ticket {

    private int number;

    private String name;

    public Ticket(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public Ticket() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
