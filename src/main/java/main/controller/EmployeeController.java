package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.model.Employee;
import main.model.EmployeeDAO;
import javax.swing.text.html.ImageView;
import java.sql.Date;
import java.sql.SQLException;

public class EmployeeController {


    @FXML
    private TableColumn<Employee, Integer> empId;
    @FXML
    private TableColumn<Employee, String> empUname;
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
    private TableView<Employee> tableAll;
    @FXML
    private Button btnLayanan;
    @FXML
    private TableColumn<Employee, String> empName;
    @FXML
    private Button btnLihat;
    @FXML
    private TableColumn<Employee, Date> empDateBirth;
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
    private TableColumn<Employee, String> empRole;
    @FXML
    private Button btnSupplier;
    @FXML
    private TableColumn<Employee, String> empAddress;
    @FXML
    private Button btnJenis;
    @FXML
    private TableColumn<Employee, String> empPhoneNumber;
    @FXML
    private TextField txtCari;


    @FXML
    void c4c4c4cb(ActionEvent event) {

    }

    public void buttonExit(MouseEvent me) {
        if(me.getSource() == btnKeluar)
            System.exit(0);
    }

    //Search an Employee
    @FXML
    private void searchEmployee(ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            //Get Employee Information
            Employee emp = EmployeeDAO.searchEmployee(txtCari.getText());

            //Populate Employee on TableView and Display on TextField
            populateAndShowEmployee(emp);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Employee information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void searchEmployees(ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            //Get all Employee information
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();

            //Populate Employees on TableView
            populateEmployees(empData);
        } catch(SQLException e) {
            System.out.println("Error occurred while getting employees information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {

        empId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        empName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        empDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        empAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        empPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        empRole.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        empUname.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
    }

    //Populate Employee
    @FXML
    private void populateEmployee(Employee emp) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the tableAll
        tableAll.setItems(empData);
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
        if(emp != null) {
            populateEmployee(emp);
        } else {
            System.out.println("This employee doesn't exist");
        }
    }

    @FXML
    private void populateEmployees(ObservableList<Employee> empData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(empData);
    }

    @FXML
    private void updateEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            EmployeeDAO.updateEntries(txtID.getText(), txtNama.getText(), txtTglLahir.getText(), txtAlamat.getText(),
                    txtTelp.getText(), txtRole.getText(), txtUname.getText(), txtPawd.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating employee");
        }
    }

    @FXML
    private void insertEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException {
        try {
            EmployeeDAO.insertEmp(txtNama.getText(), txtTglLahir.getText(), txtAlamat.getText(),
                    txtTelp.getText(), txtRole.getText(), txtUname.getText(), txtPawd.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting employee");
        }
    }

    @FXML
    private void deleteEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException {
        try {
            EmployeeDAO.deleteEmpWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting employee");
        }
    }

}
