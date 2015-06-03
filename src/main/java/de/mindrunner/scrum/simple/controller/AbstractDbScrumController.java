package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.base.model.ScrumBoard;
import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;
import org.mapdb.DB;
import org.mapdb.HTreeMap;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 * Abstract scrum controller for use with MapDb
 *
 */
public abstract class AbstractDbScrumController extends SimpleScrumController {
    protected DB db;
    protected String boardName;
    protected final String defaultBoardName = "default";

    public AbstractDbScrumController(SimpleScrumFactory simpleScrumFactory) {
        super(simpleScrumFactory);
        this.boardName = defaultBoardName;
    }

    public AbstractDbScrumController(String boardName, SimpleScrumFactory simpleScrumFactory) {
        super(simpleScrumFactory);
        this.boardName = boardName;
    }

    /**
     *
     * Initializes the scrum board from persistence storage
     *
     * @param boardName The scrum board to be used
     */
    protected void init(String boardName) {
        initDatabase();
        HTreeMap<String, ScrumBoard> boards = db.getHashMap("boards");
        if (!boards.containsKey(boardName)) {
            boards.put(boardName, scrumFactory.createScrumBoard());
        }
        scrumBoard = boards.get(boardName);
    }

    /**
     * Persists the scrum board and finalizes the persistence storage
     */
    @Override
    public void cleanup() {
        super.cleanup();
        HTreeMap<String, ScrumBoard> boards = db.getHashMap("boards");
        boards.put(boardName, scrumBoard);
        db.commit();
        db.close();
    }

    /**
     * Initializes the persistence storage
     */
    protected abstract void initDatabase();
}
