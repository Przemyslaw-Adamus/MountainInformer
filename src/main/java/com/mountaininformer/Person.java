package com.mountaininformer;

public class Person {
    private String name;
    private String surname;
    private String number;
    private int SMSstart;
    private int SMSend;
    private int SMShelp;

    public Person(String name, String surname, String number, int SMSstart, int SMSend, int SMShelp) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.SMSstart = SMSstart;
        this.SMSend = SMSend;
        this.SMShelp = SMShelp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSMSstart() {
        return SMSstart;
    }

    public void setSMSstart(int SMSstart) {
        this.SMSstart = SMSstart;
    }

    public int getSMSend() {
        return SMSend;
    }

    public void setSMSend(int SMSend) {
        this.SMSend = SMSend;
    }

    public int getSMShelp() {
        return SMShelp;
    }

    public void setSMShelp(int SMShelp) {
        this.SMShelp = SMShelp;
    }
}
