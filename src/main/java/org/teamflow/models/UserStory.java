package org.teamflow.models;

import java.util.ArrayList;

public class UserStory {
    private String titel;
    private int id;
    ArrayList<Task> taskList = new ArrayList<>();

    public UserStory(String titel, int id, ArrayList<Task> taken) {
        this.titel = titel;
        this.id = id;
        this.taskList = taken;
    }


    public String getTitel() {
        return titel;
    }

    // Setter voor titel
    public void setTitel(String titel) {
        this.titel = titel;
    }

    // Getter voor takenLijst
    public ArrayList<Task> gettaskList() {
        return taskList;
    }

    // Setter voor takenLijst
    public void setTakenLijst(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public void addTask(Task task){
        taskList.add(task);
    }
    public void verwijderTask(Task task){
        taskList.remove(task);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
