package de.mindrunner.scrum.base.controller;

import de.mindrunner.scrum.base.exception.InvalidOperationException;
import de.mindrunner.scrum.base.model.TaskState;

import java.util.List;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * Interface for the ScrumController
 */
public interface ScrumController {

    /**
     * Creates a new story
     *
     * @param storyId     The id for the story
     * @param description The story's description
     * @throws InvalidOperationException When the storyId already exists
     */
    void createStory(String storyId, String description) throws InvalidOperationException;

    /**
     * Lists all stories
     *
     * @return A List of {@link java.lang.String}, each of them has the format  "[storyId] [storyDescription]"
     */
    List<String> listStories();

    /**
     * Deletes a story
     *
     * @param storyId The id of the story to be deleted
     * @throws InvalidOperationException If the story does not exist
     */
    void deleteStory(String storyId) throws InvalidOperationException;

    /**
     * completes a story
     *
     * @param storyId The id of the story to be completed
     * @throws InvalidOperationException If the story is either already completed, or not all of its tasks are set to
     *                                   DONE {@link TaskState}
     */
    void completeStory(String storyId) throws InvalidOperationException;

    /**
     * Creates a task for a story
     *
     * @param storyId     The id of the story a task will be created for
     * @param taskId      The new task's id
     * @param description The task's description
     * @throws InvalidOperationException If the storyId is not existing or the taskId already exists
     */
    void createTask(String storyId, String taskId, String description) throws InvalidOperationException;

    /**
     * Lists all task to a story
     *
     * @param storyId The story's id of which the tasks will be listed
     * @return A List of {@link java.lang.String}, each of them has the format  "[taskId] [taskDescription] [taskState]"
     */
    List<String> listTasks(String storyId);

    /**
     * Deletes a story's task
     *
     * @param storyId The story's id of which a task will be deleted
     * @param taskId  The task's to be deleted id
     * @throws InvalidOperationException If the story or the task does not exist
     */
    void deleteTask(String storyId, String taskId) throws InvalidOperationException;

    /**
     * Moves a task to another column
     *
     * @param storyId   The story's id of which a task will be moved
     * @param taskId    The task's to be moved id
     * @param taskState The new task state
     * @throws InvalidOperationException If the story or the task does not exist as well as if the new TaskState is
     *                                   invalid
     */
    void moveTask(String storyId, String taskId, TaskState taskState) throws InvalidOperationException;

    /**
     * Updates a story's task
     *
     * @param storyId     The story's id of which a task will be updated
     * @param taskId      The task's to be updated id
     * @param description The new task description
     * @throws InvalidOperationException If the story or the task does not exist
     */
    void updateTask(String storyId, String taskId, String description) throws InvalidOperationException;

    /**
     * Call this method to clean up resources
     */
    void cleanup();
}
