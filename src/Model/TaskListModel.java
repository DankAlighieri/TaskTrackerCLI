package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskListModel {
    private List<TaskModel> taskList;
    private final String jsonPath = System.getProperty("user.home") + "\\tasks.json";

    public TaskListModel() {
        this.taskList = getTasksFromJson();
    }

    public List<TaskModel> getTasksFromJson() {
        List<TaskModel> existingTasks = new ArrayList<>();

        try {
            if (!Files.exists(Paths.get(jsonPath))) {
                return existingTasks; // Retorna lista vazia se o arquivo não existe
            }

            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
            if (jsonContent.trim().isEmpty()) {
                return existingTasks; // Retorna lista vazia se o arquivo está vazio
            }

            jsonContent = jsonContent.replace("[", "").replace("]", "").trim();
            String[] tasks = jsonContent.split("\\},\\s*\\{");

            for (String t : tasks) {
                if (!t.startsWith("{")) t = "{" + t;
                if (!t.endsWith("}")) t += "}";
                existingTasks.add(TaskModel.fromJson(t));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return existingTasks;
    }

    public boolean saveTasks() {
        StringBuilder builder = new StringBuilder("[\n");
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(taskList.get(i).toJson());
            if (i < taskList.size() - 1) builder.append(",\n");
        }
        builder.append("\n]");

        try {
            Files.createDirectories(Paths.get(jsonPath).getParent()); // Cria diretórios se necessário
            try (FileWriter jsonFile = new FileWriter(jsonPath)) {
                jsonFile.write(builder.toString());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addTask(String description) {
        TaskModel newTask = new TaskModel(description);
        taskList.add(newTask);
        System.out.println("Task " + newTask.getTaskId() + " saved successfully!");
    }

    public void removeTask(int ID){
        taskList.remove(ID-1);
        System.out.println("Task " + ID + " removed successfully!");
    }

    public void updateTask(int ID, String description){
        taskList.get(ID-1).setTaskName(description);
        System.out.println("Task " + ID + " updated successfully!");
    }

    public void markInProgress(int ID){
        taskList.get(ID-1).setStatus("In progress");
        System.out.println("Task " + ID + " marked progress successfully!");
    }

    public void markDone(int ID){
        taskList.get(ID-1).setStatus("Done");
        System.out.println("Task " + ID + " marked as done successfully!");
    }

    public void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        for (TaskModel task : taskList) {
            System.out.println(task.toJson());
        }
    }

    public void listAllFinishedTasks() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        boolean foundTask = false;
        for (TaskModel task : taskList){
            String status = task.getStatus().strip();
            if ("Done".equalsIgnoreCase(task.getStatus().strip())){
                System.out.println(task.toJson());
                foundTask = true;
            }
        }

        if (!foundTask) System.out.println("No finished task found.");
    }

    public void listAllInProgressTasks(){
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        boolean foundTask = false;
        for (TaskModel task : taskList) {
            String status = task.getStatus().strip();
            if ("In progress".equalsIgnoreCase(task.getStatus().strip())){
                System.out.println(task.toJson());
                foundTask = true;
            }
        }

        if (!foundTask) System.out.println("No in-progress tasks found.");
    }
}