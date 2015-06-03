package de.mindrunner.scrum.simple;

import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.simple.controller.Shell;
import de.mindrunner.scrum.simple.controller.ShellCommand;
import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;
import de.mindrunner.scrum.simple.util.C;

import java.io.File;
import java.io.IOException;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * Entry point class of the program
 */
public class ScrumTool {
    /**
     * The main entry point of the program. Requests the GoEuro REST Api and writes CSV data to either a file or
     * stdout.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        SimpleScrumFactory scrumFactory = new SimpleScrumFactory();
        ScrumController scrumController = scrumFactory.createFileDbScrumController(new File(C.DB_FILE_PATH));

        // Shell Mode
        if (args.length == 0) {
            Shell shell = new Shell(scrumController);
            try {
                shell.startShell();
                scrumController.cleanup();
                System.exit(0);
            } catch (IOException e) {
                scrumController.cleanup();
                System.exit(1);
            }
        }

        // Single Command Mode
        try {
            System.out.println(new ShellCommand(args, scrumController).execute());
        } catch (Exception e) {
            scrumController.cleanup();
            System.exit(1);
        }
        scrumController.cleanup();
        System.exit(0);
    }
}
