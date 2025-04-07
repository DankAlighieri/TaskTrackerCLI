# TaskTrackerCLI 📝  

*A minimalist command-line task tracker built with Java, using no external libraries and following MVC architecture.*  

---

## 🎯 **Learning Objectives**  

This project was designed to deepen my understanding of core Java concepts while overcoming the challenges of working without external libraries. Key learnings include:  

- **Manual JSON Handling**:  
  - Parsing and writing JSON files without libraries like Gson or Jackson.  
  - Implementing robust string manipulation to avoid `ArrayIndexOutOfBoundsException` and malformed JSON.  
  - Handling edge cases (e.g., empty files, missing fields) with custom logic.  

- **MVC Architecture**:  
  - **Model**: `TaskModel` and `TaskListModel` manage data and business logic.  
  - **View**: `TaskCli` handles user input/output.  
  - **Controller**: `TaskController` bridges Model and View.  
  - Reinforced separation of concerns and modular design.  

- **File I/O Operations**:  
  - Reading/writing to `tasks.json` in the user’s home directory.  
  - Ensuring atomicity and error handling for file operations.  

---

## 🛠 **Challenges & Solutions**  

### **1. JSON Without Libraries**  
**Challenge**: Parsing and generating JSON manually is error-prone.  
**Solutions**:  
- Used `String.split()` and regex to extract key-value pairs.  
- Added validation for missing/empty fields (e.g., `finishedAt`).  
- Wrote a custom `toJson()`/`fromJson()` in `TaskModel`.  

### **2. Data Consistency**  
**Challenge**: Avoiding corruption when saving/loading tasks.  
**Solutions**:  
- Atomic writes with `FileWriter` and backups (e.g., `tasks.json.bak`).  
- Checks for file existence and empty content in `TaskListModel`.  

### **3. MVC Pitfalls**  
**Challenge**: Tight coupling between components.  
**Solutions**:  
- Strict separation:  
  - **View** (`TaskCli`) only interacts with **Controller**.  
  - **Model** has no dependency on View.  

---

## 🚀 **How to Use**  

### **Commands**  
```bash
# Add a task
java View.TaskCli add "Buy groceries"

# Remove a task (by ID)
java View.TaskCli remove 1

# List all tasks
java View.TaskCli list all
```

### **File Structure**  
```plaintext
src/
├── Model/           # Business logic and data
│   ├── TaskModel.java
│   └── TaskListModel.java
├── View/            # CLI interface
│   └── TaskCli.java
└── Controller/      # Mediates Model-View
    └── TaskController.java
```

---

## 🔍 **Key Code Snippets**  

### **Manual JSON Parsing**  
```java
// From TaskModel.java
public static TaskModel fromJson(String json) {
    json = json.replace("{", "").replace("}", "").replace("\"", "").trim();
    String[] pairs = json.split(",");
    // Iterate over key-value pairs...
}
```

### **MVC Workflow**  
```java
// In TaskCli.java (View)
addTaskFromCli(args[1]);  // Calls Controller

// In TaskController.java
list.addTask(description); // Delegates to Model
list.saveTasks();          // Saves to JSON
```

---

## 📌 **Why This Matters**  

- **Fundamentals Over Convenience**:  
  By avoiding libraries, I gained hands-on experience with file I/O, data serialization, and error handling—skills transferable to low-level systems programming.  

- **MVC in Practice**:  
  Learned how to decouple logic, making the codebase scalable and testable.  

---

## 🔗 **Links**  
- GitHub Repository: https://github.com/DankAlighieri/TaskTrackerCLI/tree/main  
- [Java Docs](https://docs.oracle.com/en/java/)  

--- 

Built with ♥ and `javac`. Feedback welcome! 🚀
