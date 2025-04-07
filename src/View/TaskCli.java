package View;

import static Controller.TaskController.*;
import static java.lang.Integer.parseInt;

public class TaskCli {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: TaskCli <command> [options]");
            return;
        }

        switch (args[0]) {
            case "add" -> {
                if (args.length < 2) {
                    System.out.println("Please provide a description for the task.\n Usage: TaskCli add <description>");
                    return;
                }

                addTaskFromCli(args[1]);
            }
            case "remove" -> {
                if (args.length < 2) {
                    System.out.println("Please, provide a task ID.\n Usage: TaskCli remove <ID>");
                    return;
                }
               try {
                   removeTaskFromCli(parseInt(args[1]));
                   //System.out.println();
               } catch (NumberFormatException e) {
                   System.out.println("Please, provide a number for the ID.");
                   e.printStackTrace();
               }
            }
            case "update" -> {
                if (args.length < 3) {
                    System.out.println("Please, provide a task ID and a description.\n Usage: TaskCli update <id> <description>");
                    return;
                }

                updateTaskFromCli(parseInt(args[1]), args[2]);
            }
            case "list" -> {
                if (args.length < 2) {
                    System.out.println("Please, select which tasks you wish to list.\nUsage: TaskCli list <category>\nCategories are: all, done, inprogress.");
                    return;
                }

                switch (args[1]){
                    case "all" -> {
                        listAllTasksToCli();
                    }

                    case "done" -> {
                        listAllFinishedTasksToCli();
                    }

                    case "inprogress" -> {
                        listAllInProgressTasksToCli();
                    }
                }
            }
            case "done" -> {
                if (args.length < 2) {
                    System.out.println("Please, provide the id of the task that you have finished.\nUsage: java -cp .\\bin\\ View.TaskCli done <id>");
                    return;
                }

                markDoneFromCli(parseInt(args[1]));
            }
            case "inprogress" -> {
                if (args.length < 2) {
                    System.out.println("Please, provide the id of the task that you have begun.\nUsage: java -cp .\\bin\\ View.TaskCli inprogress <id>");
                    return;
                }

                markInProgressFromCli(parseInt(args[1]));
            }
        }
    }
}