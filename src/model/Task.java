package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    // Static counter for auto-incremented ID
    private static int idCounter = 1;

    // Unique ID for each task
    private final int id;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Date deadline;
    private String status; // e.g., "Open", "In Progress", "Postponed", "Completed", "Delayed"
    private List<Reminder> reminders;

    // Constants for status values
    public static final String STATUS_OPEN = "Open";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_POSTPONED = "Postponed";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_DELAYED = "Delayed";

    // Constructor
    public Task(String title, String description, Category category, Priority priority, Date deadline) {
        this.id = idCounter++; // Assign current counter value and increment
        this.title = title;
        this.description = description;
        setCategory(category); // Use setter to manage references
        setPriority(priority); // Use setter to manage references
        this.deadline = deadline;
        this.status = STATUS_OPEN; // Default status is "Open"
        this.reminders = new ArrayList<>();
        updateStatusIfDelayed(); // Check if status should be "Delayed" on creation
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category newCategory) {
        if (this.category != null) {
            this.category.removeTask(this); // Remove from old category
        }
        this.category = newCategory;
        if (newCategory != null) {
            newCategory.addTask(this); // Add to new category
        }
    }

    public void setPriority(Priority newPriority) {
        if (this.priority != null) {
            this.priority.removeTask(this); // Remove from old priority
        }
        this.priority = newPriority;
        if (newPriority != null) {
            newPriority.addTask(this); // Add to new priority
        }
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
        updateStatusIfDelayed(); // Check if status needs to be updated to "Delayed"
    }

    // Method to update status if the deadline has passed
    private void updateStatusIfDelayed() {
        Date currentDate = new Date();
        if (currentDate.after(deadline) && !status.equals(STATUS_COMPLETED)) {
            this.status = STATUS_DELAYED;
        }
    }

    public void setStatus(String newStatus) {
        switch (newStatus) {
            case STATUS_IN_PROGRESS:
                if (status.equals(STATUS_OPEN) || status.equals(STATUS_POSTPONED)) {
                    this.status = STATUS_IN_PROGRESS;
                }
                break;
            case STATUS_POSTPONED:
                if (status.equals(STATUS_OPEN) || status.equals(STATUS_IN_PROGRESS)) {
                    this.status = STATUS_POSTPONED;
                }
                break;
            case STATUS_COMPLETED:
                this.completeTask();
                break;
            default:
                // No manual setting to "Open" or "Delayed" is allowed
                System.out.println("Invalid status transition.");
        }
    }

    // Method to mark the task as completed and clear reminders
    public void completeTask() {
        this.status = STATUS_COMPLETED;
        reminders.clear(); // Clear reminders when task is completed
    }

    // Methods to manage reminders
    public void addReminder(Reminder reminder) {
        if (!status.equals(STATUS_COMPLETED)) {
            reminders.add(reminder);
        }
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.getName() +
                ", priority=" + priority.getName() +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", reminders=" + reminders.size() +
                '}';
    }
}
