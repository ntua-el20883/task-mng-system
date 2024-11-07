package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Category;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryStorage {
    // Path to categories.json in the medialab folder
    private static final String CATEGORIES_FILE_PATH = "../medialab/categories.json";
    
    // Jackson ObjectMapper for JSON operations
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads categories from the JSON file, setting up relationships with tasks.
     *
     * @param tasks List of Task objects that need to be linked with their categories
     * @return A list of Category objects loaded from JSON
     */
    public List<Category> loadCategories(List<Task> tasks) {
        List<Category> categories = new ArrayList<>();
        File file = new File(CATEGORIES_FILE_PATH);

        if (file.exists()) {
            try {
                // Deserialize JSON to List<Category>
                List<Category> loadedCategories = mapper.readValue(file, new TypeReference<List<Category>>() {});

                for (Category category : loadedCategories) {
                    // Associate tasks with this category by matching the category's ID
                    for (Task task : tasks) {
                        if (task.getCategory().getId() == category.getId()) {
                            category.addTask(task);
                        }
                    }
                    categories.add(category);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }

    /**
     * Saves the list of categories to the JSON file.
     *
     * @param categories List of Category objects to be saved
     */
    public void saveCategories(List<Category> categories) {
        try {
            mapper.writeValue(new File(CATEGORIES_FILE_PATH), categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
