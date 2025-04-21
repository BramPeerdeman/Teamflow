package org.teamflow.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.teamflow.models.*;

public class JSONHelper
{
    private static final String FILE_PATH = "data/users.json";
    private static final String FILE_PATH2 = "data/tasks.json";
    private static final String FILE_PATH3 = "data/epics.json";
    private static final String FILE_PATH4 = "data/messages.json";
    private static final String FILE_PATH5 = "data/userstories.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

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


    public static ArrayList<Epic> loadEpics()
    {
        try (FileReader reader = new FileReader(FILE_PATH3))
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
        try (FileWriter writer = new FileWriter(FILE_PATH3))
        {
            gson.toJson(epics, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van epics: " + e.getMessage());
        }
    }

    public static ArrayList<UserStory> loadUserStories()
    {
        try (FileReader reader = new FileReader(FILE_PATH5))
        {
            Type listType = new TypeToken<ArrayList<UserStory>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveUserStories(ArrayList<UserStory> userStories)
    {
        try (FileWriter writer = new FileWriter(FILE_PATH5))
        {
            gson.toJson(userStories, writer);
        } catch (IOException e)
        {
            System.out.println("Fout bij het opslaan van userstories: " + e.getMessage());
        }
    }

    public static ArrayList<Message> loadMessages() {
        try (FileReader reader = new FileReader(FILE_PATH4)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            ArrayList<Message> messages = gson.fromJson(reader, listType);
            return messages != null ? messages : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveMessages(ArrayList<Message> messages) {
        try (FileWriter writer = new FileWriter(FILE_PATH4)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Fout bij opslaan van berichten: " + e.getMessage());
        }
    }
}
