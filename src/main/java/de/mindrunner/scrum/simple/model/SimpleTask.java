package de.mindrunner.scrum.simple.model;

import de.mindrunner.scrum.base.model.Task;
import de.mindrunner.scrum.base.model.TaskState;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * Simple {@link Task} implementation
 */
public class SimpleTask implements Task {
    protected String description;
    protected TaskState state;

    public SimpleTask(String description) {
        this.description = description;
        this.state = TaskState.TODO;
    }

    @Override
    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TaskState getState() {
        return state;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
