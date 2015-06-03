package de.mindrunner.scrum.simple.util;

import java.io.File;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * Constants
 */
public class C {
    /**
     * Space Character
     */
    public static final String CHAR_SPACE =
            " ";
    /**
     * Newline Separator
     */
    public static final String CHAR_NEWLINE =
            System.getProperty("line.separator");
    /**
     * Quote character
     */
    public static final String CHAR_QUOTE =
            "\"";
    /**
     * Empty string
     */
    public static final String EMPTYSTR =
            "";
    /**
     * Exit result code
     */
    public static final String EXIT_RESULT =
            "__EXITME__";

    public static final String HOME_DIR = System.getProperty("user.home") + File.separator + ".scrumtool";
    public static final String DB_FILE_PATH = HOME_DIR + File.separator + "scrumtool.db";

    static {
        File homedir = new File(HOME_DIR);
        if (!homedir.isDirectory()) {
            homedir.mkdir();
        }
    }

    private C() {

    }
}
