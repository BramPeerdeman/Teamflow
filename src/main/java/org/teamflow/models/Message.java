package org.teamflow.models;

public class Message {
    private String inhoud;
    private User afzender;

    public Message (String inhoud, User afzender) {
        this.inhoud = inhoud;
        this.afzender = afzender;
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
}
