package Controller;
import Model.TaskListModel;
public class TaskController {
    private static final TaskListModel list = new TaskListModel();

    public static boolean addTaskFromCli(String Description) {
        list.addTask(Description);
        return list.saveTasks();
    }

    public static boolean removeTaskFromCli(int ID) {
        list.removeTask(ID);
        return list.saveTasks();
    }

    public static boolean updateTaskFromCli(int ID, String description){
        list.updateTask(ID, description);
        return list.saveTasks();
    }

    public static boolean markInProgressFromCli(int ID){
        list.markInProgress(ID);
        return list.saveTasks();
    }

    public static boolean markDoneFromCli(int ID){
        list.markDone(ID);
        return list.saveTasks();
    }

    public static boolean listAllTasksToCli(){
        list.listAllTasks();
        return list.saveTasks();
    }

    public static boolean listAllFinishedTasksToCli(){
        list.listAllFinishedTasks();
        return list.saveTasks();
    }

    public static boolean listAllInProgressTasksToCli(){
        list.listAllInProgressTasks();
        return list.saveTasks();
    }
}