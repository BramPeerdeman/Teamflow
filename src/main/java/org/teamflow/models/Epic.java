package org.teamflow.models;

import java.util.ArrayList;

public class Epic {
    private int id;
    private String titel;
    ArrayList<UserStory> userStories = new ArrayList<>();

    public Epic (int id, String titel, ArrayList<UserStory> userStories) {
        this.id = id;
        this.titel = titel;
        this.userStories = userStories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }

    public void voegUserStoryToe (UserStory userStory){
        userStories.add(userStory);
    }

    public void verwijderUserStory(UserStory userStory) {
        userStories.remove(userStory);
    }
}
