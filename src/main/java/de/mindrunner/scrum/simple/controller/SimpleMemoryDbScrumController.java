package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;
import org.mapdb.DBMaker;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 * ScrumController with a Memory Database backend
 */
public class SimpleMemoryDbScrumController extends AbstractDbScrumController {
    public SimpleMemoryDbScrumController(SimpleScrumFactory simpleScrumFactory) {
        super(simpleScrumFactory);
        init(boardName);
    }

    public SimpleMemoryDbScrumController(String boardName, SimpleScrumFactory simpleScrumFactory) {
        super(boardName, simpleScrumFactory);
        init(boardName);
    }

    @Override
    protected void initDatabase() {
        db = DBMaker.newMemoryDB().closeOnJvmShutdown().make();
    }
}
