package org.teamflow.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.teamflow.models.Task;
import org.teamflow.models.User;

public class JSONHelper
{
    private static final String FILE_PATH = "users.json";
    private static final String FILE_PATH2 = "tasks.json";
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
        try (FileReader reader = new FileReader(FILE_PATH2))
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
        try (FileWriter writer = new FileWriter(FILE_PATH2))
        {
            gson.toJson(tasks, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van taken: " + e.getMessage());
        }
    }

}
