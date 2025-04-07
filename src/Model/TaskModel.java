package Model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TaskModel {
    private static int sequential = 0;
    private String taskName;
    private int taskId;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public TaskModel () {}

    public TaskModel(String taskName) {
        this.taskName = taskName;
        this.taskId = ++sequential;
        this.status = "pending";
        this.startedAt = LocalDateTime.now();
        this.finishedAt = null;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    // Convert String to json
    public String toJson() {
        return "{\n"
            + "\"taskName\": \"" + this.taskName + "\",\n"
            + "\"taskId\": " + this.taskId + ",\n"
            + "\"status\": \"" + this.status + "\",\n"
            + "\"startedAt\": \"" + this.startedAt.format(formatter) + "\",\n"
            + "\"finishedAt\": \"" + (this.finishedAt == null ? "Not finished" : this.finishedAt.format(formatter)) + "\"\n"
            + "}";
    }

    // Convert json to String
    public static TaskModel fromJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "").trim();
        String[] pairs = json.split(",");
        TaskModel task = new TaskModel();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2); // Split em chave e valor
            if (keyValue.length != 2) continue;

            String key = keyValue[0].strip();
            String value = keyValue[1].strip();

            switch (key) {
                case "taskName":
                    task.taskName = value;
                    break;
                case "taskId":
                    task.taskId = Integer.parseInt(value);
                    break;
                case "status":
                    task.status = value;
                    break;
                case "startedAt":
                    task.startedAt = LocalDateTime.parse(value, formatter);
                    break;
                case "finishedAt":
                    if (!value.equals("Not finished")) {
                        task.finishedAt = LocalDateTime.parse(value, formatter);
                    }
                    break;
            }
        }

        if (task.taskId > sequential) {
            sequential = task.taskId;
        }

        return task;
    }
}