package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.base.exception.InvalidOperationException;
import de.mindrunner.scrum.base.model.ScrumBoard;
import de.mindrunner.scrum.base.model.Task;
import de.mindrunner.scrum.base.model.TaskState;
import de.mindrunner.scrum.base.model.UserStory;
import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * SimpleScrumController without persistence
 */
public class SimpleScrumController implements ScrumController {

    protected ScrumBoard scrumBoard;
    protected SimpleScrumFactory scrumFactory;

    public SimpleScrumController(SimpleScrumFactory simpleScrumFactory) {
        this.scrumFactory = simpleScrumFactory;
        scrumBoard = simpleScrumFactory.createScrumBoard();
    }

    public void cleanup() {
    }

    private void checkStoryExist(String storyId) throws InvalidOperationException {
        if (!scrumBoard.containsStory(storyId))
            throw new InvalidOperationException(String.format("Story with id %s does not exist", storyId));
    }

    private void checkTaskExist(String storyId, String taskId) throws InvalidOperationException {
        if (!scrumBoard.getStory(storyId).containsTask(taskId))
            throw new InvalidOperationException(String.format("Task with id %s does not exist", taskId));
    }

    @Override
    public void createStory(String storyId, String description) throws InvalidOperationException {
        if (scrumBoard.containsStory(storyId))
            throw new InvalidOperationException(String.format("Story with id %s already exist", storyId));
        scrumBoard.createStory(storyId, scrumFactory.createUserStory(description));
    }

    @Override
    public List<String> listStories() {
        List<String> storyList = new LinkedList<>();
        for (String s : scrumBoard.getStoryIds()) {
            storyList.add(String.format("%s %s", s, scrumBoard.getStory(s).getDescription()));
        }
        return storyList;
    }

    @Override
    public void deleteStory(String storyId) throws InvalidOperationException {
        checkStoryExist(storyId);
        scrumBoard.deleteStory(storyId);
    }

    @Override
    public void completeStory(String storyId) throws InvalidOperationException {
        checkStoryExist(storyId);
        UserStory userStory = scrumBoard.getStory(storyId);
        if (userStory.isCompleted()) {
            throw new InvalidOperationException("Story is already completed");
        }
        for (String taskId : userStory.getTaskIds()) {
            Task t = userStory.getTask(taskId);
            if (t.getState() != TaskState.DONE) {
                throw new InvalidOperationException("Cannot complete Story with open Tasks");
            }
        }
        userStory.setCompleted(true);
    }

    @Override
    public void createTask(String storyId, String taskId, String description) throws InvalidOperationException {
        checkStoryExist(storyId);
        if (scrumBoard.getStory(storyId).containsTask(taskId))
            throw new InvalidOperationException(String.format("Task with id %s already exist", taskId));
        scrumBoard.getStory(storyId).createTask(taskId, scrumFactory.createTask(description));
    }

    @Override
    public List<String> listTasks(String storyId) {
        List<String> taskList = new LinkedList<>();
        for (String s : scrumBoard.getStory(storyId).getTaskIds()) {
            taskList.add(String.format("%s %s %s",
                    s,
                    scrumBoard.getStory(storyId).getTask(s).getDescription(),
                    scrumBoard.getStory(storyId).getTask(s).getState()
            ));
        }
        return taskList;
    }

    @Override
    public void deleteTask(String storyId, String taskId) throws InvalidOperationException {
        checkStoryExist(storyId);
        checkTaskExist(storyId, taskId);
        scrumBoard.getStory(storyId).deleteTask(taskId);
    }

    @Override
    public void moveTask(String storyId, String taskId, TaskState taskState) throws InvalidOperationException {
        checkStoryExist(storyId);
        checkTaskExist(storyId, taskId);
        Task task = scrumBoard.getStory(storyId).getTask(taskId);
        if (task.getState() == TaskState.DONE)
            throw new InvalidOperationException("Cannot modify already done Task");
        task.setState(taskState);
    }

    @Override
    public void updateTask(String storyId, String taskId, String description) throws InvalidOperationException {
        checkStoryExist(storyId);
        checkTaskExist(storyId, taskId);
        scrumBoard.getStory(storyId).getTask(taskId).setDescription(description);

    }
}
