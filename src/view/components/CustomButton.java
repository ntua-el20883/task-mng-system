package view.components;

import javafx.scene.control.Button;

public class CustomButton extends Button {
    public CustomButton(String text) {
        super(text);
        this.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
    }
}
