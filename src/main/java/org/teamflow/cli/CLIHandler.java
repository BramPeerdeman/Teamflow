package org.teamflow.cli;

import org.teamflow.controllers.EpicController;
import org.teamflow.controllers.MessageController;
import org.teamflow.controllers.TaskController;
import org.teamflow.controllers.UserStoryController;
import org.teamflow.controllers.UserController;

public class CLIHandler {
    private UserController userController;
    private EpicController epicController;
    private UserStoryController userStoryController;
    private TaskController taskController;
    private MessageController messageController;

    private boolean running = true;

    private StartupMenu startupMenu;
    private MainMenu mainMenu;
    private EpicMenu epicMenu;
    private UserStoryMenu userStoryMenu;
    private TaskMenu taskMenu;
    private MessageMenu messageMenu;

    public CLIHandler() {
        userController = new UserController();
        epicController = EpicController.getInstance();
        userStoryController = UserStoryController.getInstance();
        taskController = new TaskController();
        messageController = new MessageController();

        startupMenu = new StartupMenu(this);
        mainMenu = new MainMenu(this);
        epicMenu = new EpicMenu(this);
        userStoryMenu = new UserStoryMenu(this);
        taskMenu = new TaskMenu(this);
        messageMenu = new MessageMenu(this);
    }

    public void start() {
        startupMenu.displayMenu();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    // Getter-methodes voor elke controller
    public UserController getUserController() {
        return userController;
    }

    public EpicController getEpicController() {
        return epicController;
    }

    public UserStoryController getUserStoryController() {
        return userStoryController;
    }

    public TaskController getTaskController() {
        return taskController;
    }

    public MessageController getMessageController() {
        return messageController;
    }

    // Getter-methodes voor elke menu
    public StartupMenu getStartupMenu() {
        return startupMenu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public EpicMenu getEpicMenu() {
        return epicMenu;
    }

    public UserStoryMenu getUserStoryMenu() {
        return userStoryMenu;
    }

    public TaskMenu getTaskMenu() {
        return taskMenu;
    }

    public MessageMenu getMessageMenu() {
        return messageMenu;
    }
}

