package de.mindrunner.scrum.simple.model;

import de.mindrunner.scrum.base.model.ScrumBoard;
import de.mindrunner.scrum.base.model.UserStory;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * Simple {@link ScrumBoard} implementation
 */
public class SimpleScrumBoard implements ScrumBoard {
    protected HashMap<String, UserStory> backLog;

    public SimpleScrumBoard() {
        backLog = new HashMap<>();
    }

    @Override
    public void createStory(String id, UserStory story) {
        backLog.put(id, story);
    }

    @Override
    public void deleteStory(String id) {
        backLog.remove(id);
    }

    @Override
    public boolean containsStory(String id) {
        return backLog.containsKey(id);
    }

    @Override
    public UserStory getStory(String storyId) {
        return backLog.get(storyId);
    }

    @Override
    public Collection<? extends String> getStoryIds() {
        return backLog.keySet();
    }
}
