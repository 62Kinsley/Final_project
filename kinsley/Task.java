import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Task {
    // Attributes
    private int taskID;
    private String title;
    private String description;
    private Date dueDate;
    private int priority; // Could be an enum for better readability
    private boolean status; // true for completed, false for not completed

    // Constructor
    public Task(int taskID, String title, String description, Date dueDate, int priority) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = false; // Default status is not completed
    }

    // Methods
    public void createTask(String title, String description, Date dueDate, int priority) {
        // Logic to create a task
        // This might involve adding the task to a list and displaying it in the UI
    }

    public void editTask(String title, String description, Date dueDate, int priority) {
        // Logic to edit the task details
        // Update the attributes and then refresh the display in the UI
    }

    public void deleteTask() {
        // Logic to delete the task
        // Remove the task from the list and update the UI
    }

    public void markAsCompleted() {
        this.status = true;
        // Logic to mark the task as completed
        // Update the display in the UI to reflect the task's completion
    }

    // Getters and Setters
    public int getTaskID() {
        return taskID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
