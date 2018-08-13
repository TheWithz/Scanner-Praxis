package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    File curretDirectory = null;
    File defaultDirectory;
    String projectName;

    //Easy Tab
    @FXML
    Button easyCreate;
    @FXML
    Button easyFileBrowser;
    @FXML
    Label easyFilePath;
    @FXML
    ProgressBar easyLoading;
    @FXML
    TextArea easyLog;
    @FXML
    TextField easyProjectName;

    //Medium Tab
    @FXML
    Button normalFileBrowser;
    @FXML
    Label normalFilePath;
    @FXML
    Button mediumCreatePDF;
    @FXML
    ChoiceBox mediumColorMode = new ChoiceBox();

    //Hard Tab
    @FXML
    Button hardFileBrowser;
    @FXML
    Label hardFilePath;
    @FXML
    Button hardImport;
    @FXML
    Button hardDelete;
    @FXML
    Button hardScan;
    @FXML
    Button hardRunScanTailor;
    @FXML
    Button hardCreatePDF;
    @FXML
    Spinner hardMargins;
    @FXML
    ChoiceBox hardLayoutOption = new ChoiceBox();
    @FXML
    ChoiceBox hardOrientation = new ChoiceBox();
    @FXML
    ChoiceBox hardColorMode = new ChoiceBox();

    @FXML File locateFile(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        defaultDirectory = new File("/Users/paul/Documents/Programming/VML/Scanner-Praxis/");
        chooser.setInitialDirectory(defaultDirectory);

        curretDirectory = chooser.showDialog(new Stage());
        return curretDirectory;
    }


    public void initialize() {
        setText();

        easyTab();
        mediumTab();
        hardTab();
    }

    private void setText(){


        mediumColorMode.getItems().addAll("Text and Line Drawings Only", "Text and Photographs", "Full Photographs");
        mediumColorMode.setValue("Text and Line Drawings Only");


        hardRunScanTailor.setDisable(true);
        hardCreatePDF.setDisable(true);
        hardLayoutOption.getItems().addAll("Auto Detect", "One Page", "Two Page");
        hardLayoutOption.setValue("Auto Detect");
        hardOrientation.getItems().addAll("Left", "Right", "Upsidedown");
        hardOrientation.setValue("Left");
        hardMargins.setPromptText("0.0 mm");
        hardColorMode.getItems().addAll("Text and Line Drawings Only", "Text and Photographs", "Full Photographs");
        hardColorMode.setValue("Text and Line Drawings Only");

    }

    void easyTab(){
        easyFileBrowser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try{
                    File results = locateFile(new ActionEvent());

                    easyFilePath.setText(results.toString());
                    normalFilePath.setText(results.toString());
                    hardFilePath.setText(results.toString());
                }
                catch (Exception e){
                    System.out.println("No directory selected.");
                }
            }
        });
        easyCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(curretDirectory == null)){
                    easyLog.appendText("Downloading Pictures from Cameras...\n");

                    System.out.println("Starting");
                    easyCreate.setText("Processing...");
                    easyLog.appendText("Processing Pictures...\n");
//                    easyCreate.setDisable(true);

                    scanTail();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please chose a place to scan to.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });

    }

    void scanTail(){
        try{
            String fileOfDirectory = curretDirectory.toString() + "/";
            System.out.println(fileOfDirectory);
            String[] command = {"/Users/paul/Documents/Programming/VML/Scanner-Praxis/auto_scan.sh", fileOfDirectory, fileOfDirectory};
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();
//            process.waitFor();
            process.onExit();
            System.out.println("Finished");

//            Task<Void> task = new Task<>() {
//                @Override
//                protected Void call() throws Exception {
//                    updateMessage("Running");
//                    for (int i = 0; i < 10; i++) {
//                        Thread.sleep(200);
//                        updateProgress(i+1, 10);
//                    }
//                    updateMessage("Finished.");
//                    return null;
//                }
//            };
//
//            easyCreate.disableProperty().bind(task.runningProperty());
//            easyLog.textProperty().bind(task.messageProperty());
        }
        catch (Exception e){
            System.out.println("Failed");
        }
    }

    void mediumTab(){
        normalFileBrowser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File results = locateFile(new ActionEvent());
                easyFilePath.setText(results.toString());
                normalFilePath.setText(results.toString());
                hardFilePath.setText(results.toString());
                int test = 0;
                test = 1;
            }
        });
        mediumColorMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(mediumColorMode.getValue());
                if(mediumColorMode.getValue().equals("Text and Photographs")){
                    mediumCreatePDF.setDisable(true);
                }
            }
        });
        mediumCreatePDF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediumCreatePDF.setText("Processing!");
            }
        });
    }

    void hardTab() {
        hardFileBrowser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File results = locateFile(new ActionEvent());
                easyFilePath.setText(results.toString());
                normalFilePath.setText(results.toString());
                hardFilePath.setText(results.toString());
                int test = 0;
            }
        });
        hardImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hardImport.setText("Importing!");
                hardDelete.setDisable(true);
                try {
                    ProcessBuilder pb = new ProcessBuilder("sh test");
                    pb.redirectErrorStream(true);
                    pb.start();
                    //StringBuilder out = new StringBuilder();
                    //BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

                }
                catch (IOException e){e.printStackTrace();}
            }
        });
        hardDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete contents on camera??", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    hardDelete.setText("Deleting!");
                }
            }
        });
        hardScan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hardScan.setText("Processing!");
                hardRunScanTailor.setDisable(false);
                hardCreatePDF.setDisable(false);
            }
        });
        hardRunScanTailor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hardRunScanTailor.setText("Opening!");
            }
        });
        hardCreatePDF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hardCreatePDF.setText("Creating!");
            }
        });

    }
}