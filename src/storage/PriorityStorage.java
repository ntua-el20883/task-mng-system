package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Priority;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriorityStorage {
    // Path to priorities.json in the medialab folder
    private static final String PRIORITIES_FILE_PATH = "../medialab/priorities.json";
    
    // Jackson ObjectMapper for JSON operations
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads priorities from the JSON file, setting up relationships with tasks.
     *
     * @param tasks List of Task objects that need to be linked with their priorities
     * @return A list of Priority objects loaded from JSON
     */
    public List<Priority> loadPriorities(List<Task> tasks) {
        List<Priority> priorities = new ArrayList<>();
        File file = new File(PRIORITIES_FILE_PATH);

        if (file.exists()) {
            try {
                // Deserialize JSON to List<Priority>
                List<Priority> loadedPriorities = mapper.readValue(file, new TypeReference<List<Priority>>() {});

                for (Priority priority : loadedPriorities) {
                    // Associate tasks with this priority by matching the priority's ID
                    for (Task task : tasks) {
                        if (task.getPriority().getId() == priority.getId()) {
                            priority.addTask(task);
                        }
                    }
                    priorities.add(priority);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return priorities;
    }

    /**
     * Saves the list of priorities to the JSON file.
     *
     * @param priorities List of Priority objects to be saved
     */
    public void savePriorities(List<Priority> priorities) {
        try {
            mapper.writeValue(new File(PRIORITIES_FILE_PATH), priorities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
