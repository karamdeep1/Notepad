package com.example.notepad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController extends HelloApplication{
    public HelloController(){}
    @FXML
    private TextArea textArea;
    double size = 11;
    private HelloApplication ha;
    String url = "https://www.bing.com/search?q=get+help+with+notepad+in+windows&filters=guid:%224466414-en-dia%22%20lang:%22en%22&form=T00032&ocid=HelpPane-BingIA";
    public void viewHelp(ActionEvent e) throws IOException {
        HostService.createInstance(getHostServices());
        HostService.getInstance().openURL(url);
    }
    public void zoomIn(ActionEvent e) throws IOException {
        Font font = textArea.getFont();
        size = font.getSize();
        size++;
        textArea.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, size));
    }
    public void zoomOut(ActionEvent e) throws IOException {
        Font font = textArea.getFont();
        size = font.getSize();
        size--;
        textArea.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, size));
    }
    private void showFontPicker() {
        // Create a dialog for font picking
        Dialog<Font> dialog = new Dialog<>();
        dialog.setTitle("Font Picker");
        dialog.setHeaderText("Select a font");

        // Set the button types
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the font family combobox
        ObservableList<String> fontFamilies = FXCollections.observableArrayList(Font.getFamilies());
        ComboBox<String> fontFamilyComboBox = new ComboBox<>(fontFamilies);
        fontFamilyComboBox.setValue(textArea.getFont().getFamily());

        // Create the font size spinner
        Spinner<Double> fontSizeSpinner = new Spinner<>(1, 100, textArea.getFont().getSize(), 1);


        // Set the content of the dialog
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.addRow(0, new Label("Font Family:"), fontFamilyComboBox);
        gridPane.addRow(1, new Label("Font Size:"), fontSizeSpinner);
        dialog.getDialogPane().setContent(gridPane);

        // Convert the result to a font object when OK is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String fontFamily = fontFamilyComboBox.getValue();
                double fontSize = fontSizeSpinner.getValue();
                String fontStyle = "";

                return Font.font(fontFamily, FontWeight.findByName(fontStyle), fontSize);
            }
            return null;
        });

        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(font -> {
            if (font != null) {
                // Update the label with the selected font
                textArea.setFont(font);
            }
        });
    }
    public void formatFont(ActionEvent e) throws IOException {
        showFontPicker();
    }
    public void editUndo(ActionEvent e) throws IOException {
        textArea.undo();
    }
    public void editCut(ActionEvent e) throws IOException {
        textArea.cut();
    }
    public void editCopy(ActionEvent e) throws IOException {
        textArea.copy();
    }
    public void editPaste(ActionEvent e) throws IOException {
        textArea.paste();
    }
    public void fileReset(ActionEvent e) throws IOException {
        textArea.clear();
    }
    public void fileNew(ActionEvent e) throws IOException {
        createNewScene();
    }
    public void createNewScene() {
        ha.createNewScene();
    }
    public void setApplication(HelloApplication myApplication) {
        ha = myApplication;
    }
}