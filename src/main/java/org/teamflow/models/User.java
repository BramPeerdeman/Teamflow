package org.teamflow.models;

public class User
{
    private int id;
    private String name;
    private Boolean isScrumMaster;

    public User(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Needed for Gson
    public User() {}

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getIsScrumMaster()
    {
        return isScrumMaster;
    }

    public void setIsScrumMaster(Boolean isScrumMaster)
    {
        this.isScrumMaster = isScrumMaster;
    }

    public boolean isScrumMaster() {
        return this.isScrumMaster;
    }

}
