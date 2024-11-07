package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Reminder;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReminderStorage {
    // Path to reminders.json in the medialab folder
    private static final String REMINDERS_FILE_PATH = "../medialab/reminders.json";
    
    // Jackson ObjectMapper for JSON operations
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads reminders from the JSON file, setting up relationships with tasks.
     *
     * @param tasks List of Task objects that need to be linked with their reminders
     * @return A list of Reminder objects loaded from JSON
     */
    public List<Reminder> loadReminders(List<Task> tasks) {
        List<Reminder> reminders = new ArrayList<>();
        File file = new File(REMINDERS_FILE_PATH);

        if (file.exists()) {
            try {
                // Deserialize JSON to List<Reminder>
                List<Reminder> loadedReminders = mapper.readValue(file, new TypeReference<List<Reminder>>() {});

                for (Reminder reminder : loadedReminders) {
                    // Associate this reminder with its corresponding task by matching the task's ID
                    for (Task task : tasks) {
                        if (task.getId() == reminder.getTask().getId()) {
                            task.addReminder(reminder);
                            reminder.setTask(task); // Set the task reference in the reminder
                            break;
                        }
                    }
                    reminders.add(reminder);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return reminders;
    }

    /**
     * Saves the list of reminders to the JSON file.
     *
     * @param reminders List of Reminder objects to be saved
     */
    public void saveReminders(List<Reminder> reminders) {
        try {
            mapper.writeValue(new File(REMINDERS_FILE_PATH), reminders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
