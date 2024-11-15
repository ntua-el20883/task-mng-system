package view;

import view.components.CustomButton;
import view.components.SummaryCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("MediaLab Assistant");

        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));

        // Top half: Summary information (4 cells in a 2x2 grid)
        GridPane summaryGrid = new GridPane();
        summaryGrid.setHgap(10);
        summaryGrid.setVgap(10);
        summaryGrid.setAlignment(Pos.CENTER);

        // Add 4 summary cells
        SummaryCell totalTasksCell = new SummaryCell("Total Tasks", "0");
        SummaryCell completedTasksCell = new SummaryCell("Completed Tasks", "0");
        SummaryCell delayedTasksCell = new SummaryCell("Delayed Tasks", "0");
        SummaryCell upcomingTasksCell = new SummaryCell("Tasks Due Soon", "0");

        summaryGrid.add(totalTasksCell, 0, 0);
        summaryGrid.add(completedTasksCell, 1, 0);
        summaryGrid.add(delayedTasksCell, 0, 1);
        summaryGrid.add(upcomingTasksCell, 1, 1);

        // Bottom half: Navigation buttons (4 buttons in a 2x2 grid)
        GridPane navGrid = new GridPane();
        navGrid.setHgap(10);
        navGrid.setVgap(10);
        navGrid.setAlignment(Pos.CENTER);

        // Create navigation buttons
        Button taskManagementButton = new CustomButton("Task Management");
        Button categoryManagementButton = new CustomButton("Category Management");
        Button priorityManagementButton = new CustomButton("Priority Management");
        Button reminderManagementButton = new CustomButton("Reminder Management");

        // Set button actions (Placeholder for now, can add event handling later)
        taskManagementButton.setOnAction(e -> openTaskManagementView());
        categoryManagementButton.setOnAction(e -> openCategoryManagementView());
        priorityManagementButton.setOnAction(e -> openPriorityManagementView());
        reminderManagementButton.setOnAction(e -> openReminderManagementView());

        // Add buttons to navigation grid
        navGrid.add(taskManagementButton, 0, 0);
        navGrid.add(categoryManagementButton, 1, 0);
        navGrid.add(priorityManagementButton, 0, 1);
        navGrid.add(reminderManagementButton, 1, 1);

        // Add both grids to the main layout
        mainLayout.getChildren().addAll(summaryGrid, navGrid);

        // Set the scene and display the stage
        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Placeholder methods for opening other views
    private void openTaskManagementView() {
        System.out.println("Opening Task Management View...");
        // Code to switch to TaskManagementView will go here
    }

    private void openCategoryManagementView() {
        System.out.println("Opening Category Management View...");
        // Code to switch to CategoryManagementView will go here
    }

    private void openPriorityManagementView() {
        System.out.println("Opening Priority Management View...");
        // Code to switch to PriorityManagementView will go here
    }

    private void openReminderManagementView() {
        System.out.println("Opening Reminder Management View...");
        // Code to switch to ReminderManagementView will go here
    }
}
