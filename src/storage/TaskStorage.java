package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Task;
import model.Category;
import model.Priority;
import model.Reminder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    // Path to tasks.json file in medialab folder
    private static final String TASKS_FILE_PATH = "../medialab/tasks.json";
    
    // Jackson ObjectMapper for JSON operations
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads tasks from the JSON file, setting up relationships with categories and priorities.
     *
     * @param categories List of Category objects available in the app
     * @param priorities List of Priority objects available in the app
     * @return A list of Task objects loaded from JSON
     */
    public List<Task> loadTasks(List<Category> categories, List<Priority> priorities) {
        List<Task> tasks = new ArrayList<>();
        File file = new File(TASKS_FILE_PATH);

        if (file.exists()) {
            try {
                // Deserialize JSON to List<Task>
                List<Task> loadedTasks = mapper.readValue(file, new TypeReference<List<Task>>() {});

                for (Task task : loadedTasks) {
                    // Resolve category and priority references by ID
                    task.setCategory(findCategoryById(task.getCategory().getId(), categories));
                    task.setPriority(findPriorityById(task.getPriority().getId(), priorities));
                    tasks.add(task);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the JSON file.
     *
     * @param tasks List of Task objects to be saved
     */
    public void saveTasks(List<Task> tasks) {
        try {
            mapper.writeValue(new File(TASKS_FILE_PATH), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to find a Category by its ID in a list of categories
    private Category findCategoryById(int id, List<Category> categories) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null; // or a default category if you have one
    }

    // Helper method to find a Priority by its ID in a list of priorities
    private Priority findPriorityById(int id, List<Priority> priorities) {
        for (Priority priority : priorities) {
            if (priority.getId() == id) {
                return priority;
            }
        }
        return Priority.getDefaultPriority(); // Fallback to default priority
    }
}
