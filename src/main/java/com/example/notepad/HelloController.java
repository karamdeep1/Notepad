package com.example.notepad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HelloController extends HelloApplication{
    //default constructor
    public HelloController(){}
    //variables
    @FXML
    private TextArea textArea;
    double size = 11;
    private HelloApplication ha;
    String textFont;
    String url = "https://www.bing.com/search?q=get+help+with+notepad+in+windows&filters=guid:%224466414-en-dia%22%20lang:%22en%22&form=T00032&ocid=HelpPane-BingIA";
    public void viewHelp(ActionEvent e) throws IOException {
        //creates an instance for the host service
        HostService.createInstance(getHostServices());
        //gets the instance to open the url
        HostService.getInstance().openURL(url);
    }
    public void zoomIn(ActionEvent e) throws IOException {
        //gets the current font
        Font font = textArea.getFont();
        //gets the font size
        size = font.getSize();
        //increments the size
        size++;
        //gets current font
        textFont = font.getName();
        //sets new font size to the text
        textArea.setFont(Font.font(textFont, FontWeight.NORMAL, FontPosture.REGULAR, size));
    }
    public void zoomOut(ActionEvent e) throws IOException {
        //gets the current font
        Font font = textArea.getFont();
        //gets the font size
        size = font.getSize();
        //decrements the size
        size--;
        //gets current font
        textFont = font.getName();
        //sets new font size to the text
        textArea.setFont(Font.font(textFont, FontWeight.NORMAL, FontPosture.REGULAR, size));
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
    //calls the showFontPicker method
    public void formatFont(ActionEvent e) throws IOException {
        showFontPicker();
    }
    public void editUndo(ActionEvent e) throws IOException {
        //undoes the previous action
        textArea.undo();
    }
    public void editCut(ActionEvent e) throws IOException {
        //cuts the highlighted text
        textArea.cut();
    }
    public void editCopy(ActionEvent e) throws IOException {
        //copies the highlighted text
        textArea.copy();
    }
    public void editPaste(ActionEvent e) throws IOException {
        //paste the copied text
        textArea.paste();
    }
    public void fileReset(ActionEvent e) throws IOException {
        //clears the text
        textArea.clear();
    }
    public void fileNew(ActionEvent e) throws IOException {
        //creates a new scene so everything is reset
        createNewScene();
    }
    public void createNewScene() {
        //calls the createNewScene method from HelloApplication
        ha.createNewScene();
    }
    public void setApplication(HelloApplication myApplication) {
        //initializes the HelloApplication 'ha' variable
        ha = myApplication;
    }
    //allows for saving the text as a file
    private void saveFile() {
        //creates a file chooser
        FileChooser fileChooser = new FileChooser();
        //names the file chooser
        fileChooser.setTitle("Save Text File");
        //makes the file choose go for text files
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //creates a file based off the stage
        File file =  fileChooser.showSaveDialog(stage);
        //if its not null then it gets the test and writes it as a text file for the user to save it somewhere
        if (file != null) {
            String text = textArea.getText();
            //writes the file
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //calls the saveFile method
    public void save(ActionEvent e) throws IOException {
        saveFile();
    }
    //lets user open any text file
    private void openFile() {
        //creates a file chooser
        FileChooser fileChooser = new FileChooser();
        //names the file chooser
        fileChooser.setTitle("Open Text File");
        //sets the file chooser to go for text filess
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //creates a file based off the stage
        File file = fileChooser.showOpenDialog(stage); // Assuming stage is your main application stage
        //if the file is not null it opens the file
        if (file != null) {
            //searches for a file
            try (Scanner scanner = new Scanner(file)) {
                //stores content of file
                StringBuilder content = new StringBuilder();
                //keeps looping for all lines of the file
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                //sets the text area to have the text of the file
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //opens the file
    public void open(ActionEvent e) {
        openFile();
    }
}