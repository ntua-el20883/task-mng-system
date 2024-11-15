package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SummaryCell extends VBox {
    private Label titleLabel;
    private Label valueLabel;

    public SummaryCell(String title, String initialValue) {
        titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        
        valueLabel = new Label(initialValue);
        valueLabel.setStyle("-fx-font-size: 20px;");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleLabel, valueLabel);
        this.setStyle("-fx-padding: 20px; -fx-border-color: black; -fx-border-width: 1px;");
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }
}
