package model;

import java.util.Date;

public class Reminder {
    // Static counter for auto-incremented ID
    private static int idCounter = 1;

    // Unique ID for each reminder
    private final int id;
    private Date reminderDate; // Date for the reminder
    private final Task task; // The task this reminder is associated with

    // Constructor
    public Reminder(Date reminderDate, Task task) {
        this.id = idCounter++; // Assign current counter value and increment
        this.task = task;
        setReminderDate(reminderDate); // Set and validate reminder date
    }

    // Getters
    public int getId() {
        return id;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public Task getTask() {
        return task;
    }

    // Setter for reminder date with validation
    public void setReminderDate(Date reminderDate) {
        if (reminderDate.before(task.getDeadline())) {
            this.reminderDate = reminderDate;
        } else {
            throw new IllegalArgumentException("Reminder date must be before the task's deadline.");
        }
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", reminderDate=" + reminderDate +
                ", taskId=" + task.getId() +
                '}';
    }

    // Equality based on ID (or reminderDate and task if needed)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reminder reminder = (Reminder) obj;
        return id == reminder.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
