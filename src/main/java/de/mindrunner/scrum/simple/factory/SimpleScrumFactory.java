package de.mindrunner.scrum.simple.factory;

import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.base.model.ScrumBoard;
import de.mindrunner.scrum.base.model.Task;
import de.mindrunner.scrum.base.model.UserStory;
import de.mindrunner.scrum.simple.controller.SimpleFileDbScrumController;
import de.mindrunner.scrum.simple.controller.SimpleMemoryDbScrumController;
import de.mindrunner.scrum.simple.controller.SimpleScrumController;
import de.mindrunner.scrum.simple.model.SimpleScrumBoard;
import de.mindrunner.scrum.simple.model.SimpleTask;
import de.mindrunner.scrum.simple.model.SimpleUserStory;

import java.io.File;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * Factory for SimpleScrum related classes
 */
public class SimpleScrumFactory {
    /**
     * Creates a {@link ScrumBoard}
     *
     * @return A new {@link SimpleScrumBoard}
     */
    public ScrumBoard createScrumBoard() {
        return new SimpleScrumBoard();
    }

    /**
     * Creates a {@link Task}
     *
     * @param description the task's description
     * @return A new {@link SimpleTask}
     */
    public Task createTask(String description) {
        return new SimpleTask(description);
    }

    /**
     * Creates a {@link UserStory}
     *
     * @param description The user story's description
     * @return A new {@link SimpleUserStory}
     */
    public UserStory createUserStory(String description) {
        return new SimpleUserStory(description);
    }

    /**
     * Creates a non persisting {@link ScrumController}
     *
     * @return A new {@link SimpleScrumController}
     */
    public ScrumController createNonPersistentScrumController() {
        return new SimpleScrumController(this);
    }

    /**
     * Creates a persisting {@link ScrumController}
     *
     * @param dbFile The database file
     * @return A new {@link SimpleFileDbScrumController}
     */
    public ScrumController createFileDbScrumController(File dbFile) {
        return new SimpleFileDbScrumController(dbFile, this);
    }

    /**
     * Creates a persisting {@link ScrumController}
     *
     * @param dbFile    The database file
     * @param boardName The scrum board's name
     * @return A new {@link SimpleFileDbScrumController}
     */
    public ScrumController createFileDbScrumController(File dbFile, String boardName) {
        return new SimpleFileDbScrumController(dbFile, boardName, this);
    }

    /**
     * Creates a memory persisting {@link ScrumController}
     *
     * @return A new {@link SimpleMemoryDbScrumController}
     */
    public ScrumController createMemoryDbScrumController() {
        return new SimpleMemoryDbScrumController(this);
    }

    /**
     * Creates a memory persisting {@link ScrumController}
     *
     * @param boardName The scrum board's name
     * @return A new {@link SimpleMemoryDbScrumController}
     */
    public ScrumController createMemoryDbScrumController(String boardName) {
        return new SimpleMemoryDbScrumController(boardName, this);
    }
}
