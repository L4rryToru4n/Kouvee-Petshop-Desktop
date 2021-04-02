package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.dao.PetSizeDAO;
import main.model.Employee;
import main.model.PetSize;
import main.model.PetType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class PetSizeController implements Initializable {

    private static String returnID;
    private static String returnRole;
    private static ActionEvent getEvent;

    @FXML
    private Button btnHapus;

    @FXML
    private TableColumn<PetSize, Integer> psId;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TextField txtID;

    @FXML
    private TableView<PetSize> tableAll;

    @FXML
    private Button btnUkuranKeluar;

    @FXML
    private TextField txtUkuran;

    @FXML
    private TableColumn<PetSize, String> psSize;

    @FXML
    private TextField txtCari;

    @FXML
    private Label addLabel;

    @FXML
    private Label editLabel;

    @FXML
    private Label deleteLabel;

    @FXML
    private ImageView addLogo;

    @FXML
    private ImageView editLogo;

    @FXML
    private ImageView deleteLogo;

    @FXML
    private TableColumn<PetSize, Timestamp> psCreatedAt;

    @FXML
    private TableColumn<PetSize, Timestamp> psUpdatedAt;

    @FXML
    private TableColumn<PetSize, Timestamp> psDeletedAt;

    @FXML
    private TableColumn<PetSize, String> psCreatedBy;

    @FXML
    private TableColumn<PetSize, String> psUpdatedBy;

    @FXML
    private TableColumn<PetSize, String> psDeletedBy;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public static void getEvent(ActionEvent ae) {
        getEvent = ae;
    }

    @FXML
    public void handleButtonPetSize(MouseEvent me) {
        if (me.getSource() == btnUkuranKeluar) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setX(550);
            alert.setY(300);
            alert.setTitle("Exit Kouvee PetShop");
            alert.setHeaderText("");
            alert.setContentText("Are you sure you want to exit Kouvee PetShop ?");
            alert.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK) {
                    System.exit(0);
                }
            });
        }

        if (me.getSource() == btnMenuUtama) {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenu.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void switchOperations(MouseEvent me) {
        addLabel.setTextFill(Color.WHITE);
        if (me.getSource() == addLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(false);
            btnHapus.setDisable(true);
            txtID.setDisable(true);
            txtUkuran.setDisable(false);

            addLabel.setTextFill(Color.WHITE);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if (me.getSource() == editLabel) {
            btnPerbarui.setDisable(false);
            btnTambah.setDisable(true);
            btnHapus.setDisable(true);
            txtID.setDisable(false);
            txtUkuran.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.WHITE);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_white_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if (me.getSource() == deleteLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(true);
            btnHapus.setDisable(false);
            txtID.setDisable(false);
            txtUkuran.setDisable(true);

            addLabel.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.WHITE);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_white_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

    }

    //Show All PetSizes
    @FXML
    private void searchPetSizes(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all PetSize information
            ObservableList<PetSize> psData = PetSizeDAO.searchPetSizes();

            //Populate PetSizes on TableView
            populatePetSizes(psData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting petsize information from DB " + e);
            throw e;
        }
    }

    //Search a PetSize
    @FXML
    private void searchPetSize(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get PetSize Information
            populatePetSizes(PetSizeDAO.searchPetSize(txtCari.getText()));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting PetSize information from DB" + e);
            DialogShowInfo("Error occurred while getting pet size information. Check your database.");
            throw e;
        }
    }

    //Populate PetSizes
    @FXML
    private void populatePetSize(PetSize ps) {

        //Declare an ObservableList for TableView
        ObservableList<PetSize> psData = FXCollections.observableArrayList();
        //Add petsize to the ObservableList
        psData.add(ps);
        //Set items to the tableAll
        tableAll.setItems(psData);
    }

    @FXML
    private void populateAndShowPetSize(PetSize ps) {
        if (ps != null) {
            populatePetSize(ps);
        } else {
            System.out.println("This petsize doesn't exist");
            DialogShowInfo("Pet size not found with size name" + txtCari.getText());
        }
    }

    @FXML
    private void populatePetSizes() {
        populatePetSizes();
    }

    @FXML
    private void populatePetSizes(ObservableList<PetSize> psData) {

        //Set items to the tableAll
        tableAll.setItems(psData);
    }

    @FXML
    private void deletePetSize(ActionEvent event) {
        try {
            PetSizeDAO.softDeletePsWithId(returnID, txtID.getText());
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting petsize");
        }
    }



    @FXML
    private void updatePetSize(ActionEvent event) {
        String petSize = txtUkuran.getText();
        if (!petSize.equals("Small") && !petSize.equals("Medium") && !petSize.equals("Large") && !petSize.equals("Extra Large")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        try {
            PetSizeDAO.updateEntries(returnID, txtID.getText(), petSize);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating petsize");
        }
    }

    @FXML
    private void insertPetSize(ActionEvent event) {
        String petSize = txtUkuran.getText();
        if (!petSize.equals("Small") && !petSize.equals("Medium") && !petSize.equals("Large") && !petSize.equals("Extra Large")) {
            DialogShowInfo("Hanya Small, Medium, Large, dan Extra Large");
            return;
        }

        try {
            PetSizeDAO.insertPs(returnID, petSize);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting petsize");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        psId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        psSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        psCreatedAt.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());
        psUpdatedAt.setCellValueFactory(cellData -> cellData.getValue().updatedAtProperty());
        psDeletedAt.setCellValueFactory(cellData -> cellData.getValue().deletedAtProperty());
        psCreatedBy.setCellValueFactory(cellData -> cellData.getValue().createdByProperty());
        psUpdatedBy.setCellValueFactory(cellData -> cellData.getValue().updatedByProperty());
        psDeletedBy.setCellValueFactory(cellData -> cellData.getValue().deletedByProperty());
        loadAllData();
    }

    @FXML
    private void selectedRow(MouseEvent me) {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() {
        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            PetSize petSize = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(petSize.getId()));
            txtUkuran.setText(petSize.getSize());
        }
    }

    private void loadAllData() {
        ObservableList<PetSize> psData = null;
        try {
            psData = PetSizeDAO.searchPetSizes();
            populatePetSizes(psData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void clearFields() {
        txtCari.clear();
        txtID.clear();
        txtUkuran.clear();

        loadAllData();
    }
    private void DialogShowInfo(String text) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setX(550);
        info.setY(300);
        info.setHeaderText("");
        info.setContentText(text);
        info.showAndWait();
    }

    @FXML
    private void selectedRow(MouseEvent me) {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() {


        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            PetSize petSize = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(petSize.getId()));
            txtUkuran.setText(petSize.getSize());
        }
    }
}
