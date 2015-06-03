package de.mindrunner.scrum.base.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 * ScrumBoard interface
 *
 */
public interface ScrumBoard extends Serializable {
    /**
     * Creates a user story on the scrum board
     *
     * @param id The story id
     * @param story The new {@link UserStory}
     */
    void createStory(String id, UserStory story);

    /**
     * Deletes a story from the scrum board
     *
     * @param id The story id
     */
    void deleteStory(String id);

    /**
     *
     * checks if a story is existing in the scrum board
     *
     * @param id the story id
     * @return true if id was found, false otherwise
     */
    boolean containsStory(String id);

    /**
     * Gets a user story from the scrum board
     * @param storyId The story id
     * @return The user story associated with the storyId
     */

    UserStory getStory(String storyId);

    /**
     * Gets a collection of all story ids
     *
     * @return Collection of story ids
     */
    Collection<? extends String> getStoryIds();
}
