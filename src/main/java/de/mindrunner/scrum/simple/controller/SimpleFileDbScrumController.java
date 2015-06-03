package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;
import org.mapdb.DBMaker;

import java.io.File;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 * ScrumController with a File Database backend
 *
 */
public class SimpleFileDbScrumController extends AbstractDbScrumController {
    protected File dbFile;

    public SimpleFileDbScrumController(File dbFile, SimpleScrumFactory simpleScrumFactory) {
        super(simpleScrumFactory);
        this.dbFile = dbFile;
        init(boardName);
    }

    public SimpleFileDbScrumController(File dbFile, String boardName, SimpleScrumFactory simpleScrumFactory) {
        super(boardName, simpleScrumFactory);
        this.dbFile = dbFile;
        init(boardName);
    }

    @Override
    protected void initDatabase() {
        db = DBMaker.newFileDB(dbFile).closeOnJvmShutdown().make();
    }
}
