package de.mindrunner.scrum.simple.controller;

import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.base.exception.InvalidOperationException;
import de.mindrunner.scrum.base.model.TaskState;

import java.util.Hashtable;

import static de.mindrunner.scrum.simple.util.C.*;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 * <p>
 * Command class for the user shell. Parses user input and executes commands
 */
public class ShellCommand {

    /**
     * Enumeration with all existing command types
     */
    private enum CommandType {
        QUIT,
        HELP,
        STORY_CREATE,
        STORY_LIST,
        STORY_DELETE,
        STORY_COMPLETE,
        TASK_CREATE,
        TASK_LIST,
        TASK_DELETE,
        TASK_MOVE,
        TASK_UPDATE,
        EMPTY,
        UNKNOWN,
    }

    private Hashtable<Integer, CommandType> commands;
    private CommandType commandType;
    private String paramName;


    private String[] params = new String[3];
    private ScrumController scrumController;

    /**
     * Constructor for creating a command
     *
     * @param args            The instruction array with all parameters
     * @param scrumController The {@link ScrumController}
     */
    public ShellCommand(String[] args, ScrumController scrumController) {
        init(args, scrumController);

    }

    /**
     * Constructor for creating a command
     *
     * @param instruction     The instruction string with all parameters separated by spaces
     * @param scrumController The {@link ScrumController}
     */
    public ShellCommand(String instruction, ScrumController scrumController) {
        init(instruction.split(CHAR_SPACE), scrumController);

    }

    /**
     * Parses the user's input. Extract command and its parameters for later execution
     *
     * @param args            The instruction array with all parameters
     * @param scrumController The {@link ScrumController}
     */
    public void init(String[] args, ScrumController scrumController) {
        this.scrumController = scrumController;
        this.commandType = CommandType.UNKNOWN;
        this.paramName = EMPTYSTR;
        fillCommandSet();
        String command;
        if (args.length == 1) {
            command = args[0];
        } else {
            command = args[0] + " " + args[1];
            params = new String[args.length - 2];
            System.arraycopy(args, 2, params, 0, args.length - 2);
        }

        if (commands.containsKey(command.hashCode())) {
            setCommandType(command);
        }
    }


    /**
     * Static method for getting the EXIT_RESULT string
     *
     * @return The EXIT_RESULT string
     */
    public static String getExitResult() {
        return EXIT_RESULT;
    }

    private void checkParams(int count) {
        if (this.params.length != count) {
            throw new IllegalArgumentException(String.format("Param count mismatch, expected %d, got %d", count, params.length));
        }
    }


    /**
     * Executes the current command
     *
     * @return The result of this command
     */
    public String execute() {
        try {
            switch (commandType) {
                case STORY_CREATE: {
                    checkParams(2);
                    scrumController.createStory(params[0], params[1]);
                    return EMPTYSTR;
                }
                case STORY_LIST: {
                    checkParams(0);
                    StringBuilder sb = new StringBuilder();
                    for (String s : scrumController.listStories()) {
                        sb.append(s);
                        sb.append(CHAR_NEWLINE);
                    }
                    return sb.toString();
                }
                case STORY_DELETE: {
                    checkParams(1);
                    scrumController.deleteStory(params[0]);
                    return EMPTYSTR;
                }
                case STORY_COMPLETE: {
                    checkParams(1);
                    scrumController.completeStory(params[0]);
                    return EMPTYSTR;
                }
                case TASK_CREATE: {
                    checkParams(3);
                    scrumController.createTask(params[0], params[1], params[2]);
                    return EMPTYSTR;
                }
                case TASK_LIST: {
                    checkParams(1);
                    StringBuilder sb = new StringBuilder();
                    for (String s : scrumController.listTasks(params[0])) {
                        sb.append(s);
                        sb.append(CHAR_NEWLINE);
                    }
                    return sb.toString();
                }
                case TASK_DELETE: {
                    checkParams(2);
                    scrumController.deleteTask(params[0], params[1]);
                    return EMPTYSTR;
                }
                case TASK_MOVE: {
                    checkParams(3);
                    scrumController.moveTask(params[0], params[1], TaskState.valueOf((params[2])));
                    return EMPTYSTR;
                }
                case TASK_UPDATE: {
                    checkParams(3);
                    scrumController.updateTask(params[0], params[1], params[2]);
                    return EMPTYSTR;
                }
                case HELP:
                    return executeCommandUsage();
                case QUIT:
                    return executeCommandQuit();
                case EMPTY:
                    return EMPTYSTR;
                default:
                    return executeCommandUsage();
            }
        } catch (IllegalArgumentException | InvalidOperationException e) {
            return e.getMessage();
        }
    }

    private String executeCommandQuit() {
        return getExitResult();
    }

    private String executeCommandUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScrumTool usage:");
        sb.append(CHAR_NEWLINE);
        sb.append(CHAR_NEWLINE);
        sb.append("(c)reate (s)tory <id> <description>");
        sb.append(CHAR_NEWLINE);
        sb.append("(l)ist (s)tories");
        sb.append(CHAR_NEWLINE);
        sb.append("(d)elete (s)tory <id>");
        sb.append(CHAR_NEWLINE);
        sb.append("(co)mplete (s)tory <id>");
        sb.append(CHAR_NEWLINE);
        sb.append("(c)reate (t)ask <storyId> <id> <description>");
        sb.append(CHAR_NEWLINE);
        sb.append("(l)ist (t)asks <storyId>");
        sb.append(CHAR_NEWLINE);
        sb.append("(d)elete (t)task <storyId> <id>");
        sb.append(CHAR_NEWLINE);
        sb.append("(m)ove (t)task <storyId> <id> <new column>");
        sb.append(CHAR_NEWLINE);
        sb.append("(u)pdate (t)task <storyId> <id> <new description>");
        sb.append(CHAR_NEWLINE);
        sb.append(CHAR_NEWLINE);
        sb.append("(q)uit");
        sb.append(CHAR_NEWLINE);
        sb.append("(h)elp");
        sb.append(CHAR_NEWLINE);
        return sb.toString();
    }

    /**
     * Method for filling the command set
     */
    private void fillCommandSet() {
        commands = new Hashtable<Integer, CommandType>();
        commands.put("quit".hashCode(), CommandType.QUIT);
        commands.put("help".hashCode(), CommandType.HELP);

        commands.put("create story".hashCode(), CommandType.STORY_CREATE);
        commands.put("list stories".hashCode(), CommandType.STORY_LIST);
        commands.put("delete story".hashCode(), CommandType.STORY_DELETE);
        commands.put("complete story".hashCode(), CommandType.STORY_COMPLETE);
        commands.put("create task".hashCode(), CommandType.TASK_CREATE);
        commands.put("list tasks".hashCode(), CommandType.TASK_LIST);
        commands.put("delete task".hashCode(), CommandType.TASK_DELETE);
        commands.put("move task".hashCode(), CommandType.TASK_MOVE);
        commands.put("update task".hashCode(), CommandType.TASK_UPDATE);

        // shortcuts
        commands.put("q".hashCode(), CommandType.QUIT);
        commands.put("h".hashCode(), CommandType.HELP);

        commands.put("c s".hashCode(), CommandType.STORY_CREATE);
        commands.put("l s".hashCode(), CommandType.STORY_LIST);
        commands.put("d s".hashCode(), CommandType.STORY_DELETE);
        commands.put("co s".hashCode(), CommandType.STORY_COMPLETE);
        commands.put("c t".hashCode(), CommandType.TASK_CREATE);
        commands.put("l t".hashCode(), CommandType.TASK_LIST);
        commands.put("d t".hashCode(), CommandType.TASK_DELETE);
        commands.put("m t".hashCode(), CommandType.TASK_MOVE);
        commands.put("u t".hashCode(), CommandType.TASK_UPDATE);

        commands.put("".hashCode(), CommandType.EMPTY);
    }

    /**
     * Helper method for setting the command type
     *
     * @param s The command as string
     */
    private void setCommandType(String s) {
        commandType = commands.get(s.hashCode());
    }
}
