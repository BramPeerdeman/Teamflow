package org.teamflow.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.teamflow.models.Task;
import org.teamflow.models.Epic;
import org.teamflow.models.User;

public class JSONHelper
{
    private static final String FILE_PATH = "users.json";
    private static final String FILE_PATH2 = "tasks.json";
    private static final String FILE_PATH3 = "epics.json";
    private static final Gson gson = new Gson();

    public static ArrayList<User> loadUsers()
    {
        try (FileReader reader = new FileReader(FILE_PATH))
        {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveUsers(ArrayList<User> users)
    {
        try (FileWriter writer = new FileWriter(FILE_PATH))
        {
            gson.toJson(users, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van gebruikers: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks()
    {
        try (FileReader reader = new FileReader(FILE_PATH3))
        {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveTasks(ArrayList<Task> tasks)
    {
        try (FileWriter writer = new FileWriter(FILE_PATH3))
        {
            gson.toJson(tasks, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van taken: " + e.getMessage());
        }
    }


    public static ArrayList<Epic> loadEpics()
    {
        try (FileReader reader = new FileReader(FILE_PATH2))
        {
            Type epicListType = new TypeToken<ArrayList<Epic>>() {}.getType();
            return gson.fromJson(reader, epicListType);
        } catch (IOException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveEpics(ArrayList<Epic> epics)
    {
        try (FileWriter writer = new FileWriter(FILE_PATH2))
        {
            gson.toJson(epics, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van epics: " + e.getMessage());
        }
    }
}
