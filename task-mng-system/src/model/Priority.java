package model;

import java.util.ArrayList;
import java.util.List;

public class Priority {
    private static int idCounter = 1;
    public static final Priority DEFAULT_PRIORITY = new Priority("Default", 0);

    private final int id;
    private String name;
    private int value;
    private List<Task> tasks; // List to store associated tasks

    public Priority(String name, int value) {
        this.id = idCounter++;
        this.name = name;
        this.value = value;
        this.tasks = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Setters
    public void setName(String name) {
        if (!this.equals(DEFAULT_PRIORITY)) {
            this.name = name;
        }
    }

    public void setValue(int value) {
        if (!this.equals(DEFAULT_PRIORITY) && value > 0) {
            this.value = value;
        }
    }

    // Methods to manage tasks in this priority
    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public static Priority getDefaultPriority() {
        return DEFAULT_PRIORITY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Priority priority = (Priority) obj;
        return id == priority.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", tasksCount=" + tasks.size() +
                '}';
    }
}
