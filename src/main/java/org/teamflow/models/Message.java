package org.teamflow.models;

public class Message {
    private String inhoud;
    private User afzender;
    private int id;

    public Message (String inhoud, User afzender, int id) {
        this.inhoud = inhoud;
        this.afzender = afzender;
        this.id = id;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public void setAfzender(User afzender) {
        this.afzender = afzender;
    }

    public String getInhoud() {
        return inhoud;
    }

    public User getAfzender() {
        return afzender;
    }

    public void toonBericht() {
        System.out.printf("%s%n -%s", inhoud, afzender.getName());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
