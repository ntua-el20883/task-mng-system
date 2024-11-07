package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static int idCounter = 1;

    private final int id;
    private String name;
    private List<Task> tasks; // List to store associated tasks

    // Constructor
    public Category(String name) {
        this.id = idCounter++;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Setter for name (if renaming is allowed)
    public void setName(String name) {
        this.name = name;
    }

    // Methods to manage tasks in this category
    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasksCount=" + tasks.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
