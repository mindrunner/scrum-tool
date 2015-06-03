package de.mindrunner.scrum.simple.model;

import de.mindrunner.scrum.base.model.Task;
import de.mindrunner.scrum.base.model.UserStory;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 * Simple {@link UserStory} implementation
 */
public class SimpleUserStory implements UserStory {
    protected String description;
    protected Boolean completed;
    protected HashMap<String, Task> tasks;

    public SimpleUserStory(String description) {
        this.description = description;
        this.completed = Boolean.FALSE;
        tasks = new HashMap<>();
    }

    @Override
    public Boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public void createTask(String id, Task task) {
        tasks.put(id, task);
    }

    @Override
    public void deleteTask(String id) {
        tasks.remove(id);
    }

    @Override
    public boolean containsTask(String taskId) {
        return tasks.containsKey(taskId);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    @Override
    public Collection<? extends String> getTaskIds() {
        return tasks.keySet();
    }
}
