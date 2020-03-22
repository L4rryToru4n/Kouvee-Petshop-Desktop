package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.model.Employee;


public class MainPanelController {

    @FXML
    private TableColumn<?, ?> empId;

    @FXML
    private TableColumn<?, ?> empUname;

    @FXML
    private Button btnBantuan;

    @FXML
    private TextField txtNama;

    @FXML
    private Button btnTentang;

    @FXML
    private ImageView imgProfil;

    @FXML
    private TextField txtTglLahir;

    @FXML
    private Button btnProduk;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnUkuran;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<?> tableAll;

    @FXML
    private Button btnLayanan;

    @FXML
    private TableColumn<?, ?> empName;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<?, ?> empDateBirth;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnHapus;

    @FXML
    private TextField txtTelp;

    @FXML
    private TextField txtRole;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnCustomer;

    @FXML
    private TextField txtPawd;

    @FXML
    private Button btnLaporan;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Button btnPegawai;

    @FXML
    private Button btnTransaksi;

    @FXML
    private Button btnKeluar;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TextField txtUname;

    @FXML
    private TableColumn<?, ?> empRole;

    @FXML
    private Button btnSupplier;

    @FXML
    private TableColumn<?, ?> empAddress;

    @FXML
    private Button btnJenis;

    @FXML
    private TableColumn<?, ?> empPhoneNumber;

    @FXML
    private TextField txtCari;

    //Exit the program
    public void handleMainPannelButton(MouseEvent me) {

        if (me.getSource() == btnKeluar)
            System.exit(0);

        if(me.getSource() == btnPegawai)
        {
            EmployeeController.class.getClassLoader();
        }

    }

    public void buttonAbout(ActionEvent ae) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kouvee Pet Shop");
        alert.setHeaderText("Pure Java Desktop Application ~ Pemenuhan Tugas P3L");
        alert.setContentText("Anda dapat menambah, melihat, mencari, menghapus dan memperbaharui data dengan program ini");
        alert.show();
    }
}
