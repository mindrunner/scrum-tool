package de.mindrunner.scrum.base.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 */
public interface UserStory extends Serializable {
    /**
     * Checks if the user story is already completed
     *
     * @return True if completed, false otherwise
     */
    Boolean isCompleted();

    /**
     * Sets the completed flag
     *
     * @param completed The flag to be set
     */
    void setCompleted(Boolean completed);

    /**
     * Creates a new task for the user story
     *
     * @param id   The taks's id
     * @param task The {@link Task}
     */
    void createTask(String id, Task task);

    /**
     * Deletes a task
     *
     * @param id The task's id
     */
    void deleteTask(String id);

    /**
     * Checks if a task is existing in the user story
     *
     * @param taskId The task id
     * @return True if id was found, false otherwise
     */
    boolean containsTask(String taskId);

    /**
     * Gets the story's description
     *
     * @return The story's description
     */
    String getDescription();

    /**
     * Sets the story's description
     *
     * @param description The new description
     */
    void setDescription(String description);

    /**
     * Get a {@link Task} by a given id
     *
     * @param taskId The id
     * @return The {@link Task} associated with the given id
     */
    Task getTask(String taskId);

    /**
     * Gets a collection of all task ids
     *
     * @return Collection of task ids
     */
    Collection<? extends String> getTaskIds();
}
