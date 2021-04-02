package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    private static String returnName;
    private ActionEvent getEvent;
    @FXML
    private Label nameLogged;

    @FXML
    private Button btnKelUkrHewan;

    @FXML
    private Button btnMainLogout;

    @FXML
    private Button btnKelSupplier;

    @FXML
    private Button btnKelTransaksi;

    @FXML
    private Button btnKelHewan;

    @FXML
    private Button btnKelLayanan;

    @FXML
    private Button btnKelPegawai;

    @FXML
    private Button btnKelProduk;

    @FXML
    private Button btnKelJnsHewan;

    @FXML
    private Button btnKelLaporan;

    @FXML
    private Button btnMainBantuan;

    @FXML
    private Button btnMainTentang;

    @FXML
    private Button btnKelCustomer;

    @FXML
    private Button btnMainKeluar;

    @FXML
    private Button btnServiceDetail;

    public static void getUnameLogin(String uname) {
        returnName = uname;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWelcomeLabel();
    }

    private void setWelcomeLabel() {
        nameLogged.setTextFill(Color.WHITESMOKE);
        nameLogged.setText("Welcome, " + returnName);
        nameLogged.getText();
    }

    public void handleMainButtonAction(MouseEvent me) throws SQLException, ClassNotFoundException {

        Scene scene = null;

        if (me.getSource() == btnMainKeluar) {
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
        else if (me.getSource() != btnMainKeluar)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try
            {
                triggerEvent();

                if(me.getSource() == btnMainLogout)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/Main.fxml")));
                }
                else if(me.getSource() == btnKelPegawai)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/EmployeeView.fxml")));
                }

                else if(me.getSource() == btnKelSupplier)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/SupplierView.fxml")));
                }

                else if(me.getSource() == btnKelUkrHewan)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetSizeView.fxml")));
                }

                else if(me.getSource() == btnKelJnsHewan)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetTypeView.fxml")));
                }
                else if(me.getSource() == btnKelLayanan)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ServiceView.fxml")));
                }

                else if(me.getSource() == btnKelProduk)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ProductView.fxml")));
                }

                else if(me.getSource() == btnServiceDetail)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ServiceDetailView.fxml")));
                }

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public ActionEvent getGetEvent() {
        return getEvent;
    }

    public void triggerEvent() throws SQLException, ClassNotFoundException {
        EmployeeController.getEvent(getGetEvent());
        SupplierController.getEvent(getGetEvent());
        PetSizeController.getEvent(getGetEvent());
        PetTypeController.getEvent(getGetEvent());
        ServiceController.getEvent(getGetEvent());
        ProductController.getEvent(getGetEvent());
    }
}
