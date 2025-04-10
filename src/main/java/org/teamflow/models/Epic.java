package org.teamflow.models;

import java.util.ArrayList;

public class Epic {
    private String titel;
    private ArrayList<UserStory> userStories;

    public Epic (String titel, ArrayList<UserStory> userStories) {
        this.titel = titel;
        this.userStories = userStories;
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
