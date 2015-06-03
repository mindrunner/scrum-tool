package de.mindrunner.scrum.base.model;

import java.io.Serializable;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * Task interface
 */
public interface Task extends Serializable {
    /**
     * Sets the task's state
     *
     * @param state The new TaskState
     */
    void setState(TaskState state);

    /**
     * Gets the task's description
     *
     * @return The task's description
     */
    String getDescription();


    /**
     * Gets the task's state
     *
     * @return The task's state
     */
    TaskState getState();

    /**
     * Sets the task's description
     *
     * @param description The new description
     */
    void setDescription(String description);
}
