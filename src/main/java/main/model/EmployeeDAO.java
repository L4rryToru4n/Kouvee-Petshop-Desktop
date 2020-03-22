package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    //SELECT an Employee
    public static Employee searchEmployee(String empId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM employees WHERE Id =" + empId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            Employee employee = getEmployeeFromResultSet(rsEmp);

            return employee;
        } catch (SQLException ex) {
            System.out.println("While searching an employee with Id : " + empId + ", an error occured: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = null;

        if(rs.next()) {
            emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setDateBirth(rs.getDate("dateBirth"));
            emp.setPhoneNumber(rs.getString("phoneNumber"));
            emp.setAddress(rs.getString("address"));
            emp.setRole(rs.getString("role"));
            emp.setUsername(rs.getString("username"));
            emp.setPassword(rs.getString("password"));
        }

        return emp;
    }

    //SELECT Employees
    public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees";

        //Execute SELECT Stamtement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Employee> empList = getEmployeeList(rsEmps);

            //Return Employee Object
            return empList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM employees operation
    public static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Employee Objects
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while(rs.next()) {
            Employee emp = new Employee();
            emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setDateBirth(rs.getDate("dateBirth"));
            emp.setPhoneNumber(rs.getString("phoneNumber"));
            emp.setAddress(rs.getString("address"));
            emp.setRole(rs.getString("role"));
            emp.setUsername(rs.getString("username"));
            emp.setPassword(rs.getString("password"));

            //Add employee to the ObservableList
            empList.add(emp);
        }

        //Return empList (ObservableList of Employees
        return empList;
    }

    //Update an employee's entries
    public static void updateEntries (String Id, String name, String dateBirth, String address,
                                      String phoneNumber, String role, String username, String password)
            throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                    "UPDATE employees " +
                    "SET name = '" + name + "' " +
                    ", address = '" + address + "' " +
                    ", dateBirth = '" + dateBirth + "' " +
                    ", phoneNumber = '" + phoneNumber + "' " +
                    ", role = '" + role + "' " +
                    ", username = '" + username + "' " +
                    ", password = '" + password + "' " +
                    "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE an employee
    public static void deleteEmpWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                        "DELETE FROM employees " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //INSERT an Employee
    public static void insertEmp(String name, String dateBirth, String address,
                                 String phoneNumber, String role, String username, String password)
            throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                        "INSERT INTO employees " +
                        "(name, address, dateBirth, phoneNumber, createdAt ,role, username, password)" +
                        "VALUES " +
                        "('" + name + "','" + address + "','" + dateBirth + "','" + phoneNumber + "',NOW()," +
                                "'" + role + "','" + username + "','" + password + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }

}
