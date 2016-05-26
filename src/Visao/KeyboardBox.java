/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Gustavo Freitas
 */
public class KeyboardBox extends VBox {
    
    private DoubleProperty fontSize = new SimpleDoubleProperty(Font.getDefault().getSize());

    private TextField associatedTextField;
    
    public KeyboardBox(String[] chars, TextField associatedTextField) {
        
        this.associatedTextField = associatedTextField;
        
        setAlignment(Pos.BOTTOM_CENTER);
        setMinSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        //getStyleClass().add("keyboard");

        onFontSizeChange(fontSize.getValue());
        fontSize.addListener((observable, oldValue, newValue) ->
            onFontSizeChange(newValue)
        );

        for (String row: chars) {
            HBox keyRow = new HBox();
            //keyRow.getStyleClass().add("key-row");

            keyRow.setAlignment(Pos.CENTER);
            for (char c: row.toCharArray()) {
                KeyButton key = new KeyButton(Character.toString(c));
                keyRow.getChildren().add(key);
            }
            getChildren().add(keyRow);
        }
    }
    
    private void onFontSizeChange(Number newValue) {
        setStyle("-fx-font-size: " + newValue + "px;");
    }
    
    public double getFontSize() {
        return fontSize.get();
    }

    public DoubleProperty fontSizeProperty() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize.set(fontSize);
    }

    class KeyButton extends Button {
        public KeyButton(String text) {
            super(text);
            //getStyleClass().add("key");

            setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            setMaxSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

            //setOnAction(event -> lastKeyText.set(text));
            setOnAction(event -> associatedTextField.setText(associatedTextField.getText() + text));
        }
    }
}
