package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.util.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    @FXML
    private Button btnExit;

    @FXML
    private Circle imgLogo;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUname;

    @FXML
    private TextField txtPawd;

    @FXML
    private Label lblErrors;

    @FXML
    void bcbcbc(ActionEvent event) {

    }

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public void handleButtonAction(MouseEvent me) {
        if(me.getSource() == btnExit) {
            System.exit(0);
        }

        if(me.getSource() == btnLogin)
        {
            //login here
            if (loginAction().equals("Success")) {

                Node node = (Node) me.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/EmployeeView.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (conn == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Restart the application");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is Up : Good to go");
        }
    }

    public LoginController() {
        conn = DBUtil.conDB();
    }

    @FXML
    private String loginAction() {

        String status = "Success";
        String password = txtPawd.getText();
        String username = txtUname.getText();
        String role = "Admin";

        //Query
        String query = "SELECT * FROM employees WHERE role = ? and username = ? and password = ?;";

        if(username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            try {
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, role);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next() == false)
                {
//                    do {
//                        String data = resultSet.getString("role");
//                        if(data != "Admin")
//                        {
//                            setLblError(Color.TOMATO, "Access Denied");
//                            status = "Error";
//                        }
//                        else {
//                            setLblError(Color.TOMATO, "Username or password not match ! Try Again");
//                            status = "Error";
//                        }
//                    } while(resultSet.next());
                    setLblError(Color.TOMATO, "Access Denied");
                    status = "Error";
                }
                else
                {
                    setLblError(Color.GREEN, "Login Successful");
                    status = "Success";
                }

            } catch (SQLException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
}
