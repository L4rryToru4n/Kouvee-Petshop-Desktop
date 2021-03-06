package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;
import main.model.Pet;
import main.model.PetSize;
import main.model.ServiceTransaction;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ServiceTransactionDAO {
    //SELECT a Service transaction
    public static ObservableList<ServiceTransaction> searchTransaction(String transactionName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT st.id, st.date, st.total, p.name AS 'Pets_name', emp.name AS 'Employees_name', st.isPaid" +
                " FROM Servicetransaction AS st" +
                " JOIN Pets AS p ON p.id = st.Pets_id" +
                " JOIN Employees AS emp ON emp.id = st.Employees_id" +
                " WHERE st.id = '" + transactionName + "'" +
                " AND st.deletedAt IS NULL";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTran = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<ServiceTransaction> tran = FXCollections.observableArrayList();
            try {
                tran = getTranList(rsTran);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return tran;
        } catch (SQLException ex) {
            System.out.println("While searching a service transaction with Id : " + transactionName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static ServiceTransaction getTransactionFromResultSet(ResultSet rs) throws SQLException {
        ServiceTransaction st = null;

        if (rs.next()) {
            st = new ServiceTransaction();
            st.setId(rs.getString("id"));
            st.setDate(rs.getDate("date"));
            st.setTotal(rs.getDouble("total"));
            st.setPets_Id(rs.getString("Pets_name"));
            st.setEmployees_Id(rs.getString("Employees_name"));
            st.setIsPaid(rs.getString("isPaid"));
        }
        return st;
    }

    //SELECT ServiceTransactions
    public static ObservableList<ServiceTransaction> searchServiceTransactions() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT st.id, st.date, st.total, p.name AS 'Pets_name', emp.name AS 'Employees_name', st.isPaid" +
                " FROM Servicetransaction AS st" +
                " JOIN Pets AS p ON p.id = st.Pets_id" +
                " JOIN Employees AS emp ON emp.id = st.Employees_id" +
                " AND st.deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTrans = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetList method and get pet object

            //Return ST Object
            return getTranList(rsTrans);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM Servicetransactions operation
    public static ObservableList<ServiceTransaction> getTranList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of ServiceTransaction Objects
        ObservableList<ServiceTransaction> tranList = FXCollections.observableArrayList();

        while (rs.next()) {

            ServiceTransaction st = new ServiceTransaction();
            st = new ServiceTransaction();
            st.setId(rs.getString("id"));
            st.setDate(rs.getDate("date"));
            st.setTotal(rs.getDouble("total"));
            st.setPets_Id(rs.getString("Pets_name"));
            st.setEmployees_Id(rs.getString("Employees_name"));
            byte[] target = rs.getBytes("isPaid");
            String string = new String(target);
            st.setIsPaid(string);
//            st.setCreatedAt(rs.getTimestamp("createdAt"));
//            st.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            st.setDeletedAt(rs.getTimestamp("deletedAt"));
//            st.setCreatedBy(rs.getString("Name Created"));
//            st.setUpdatedBy(rs.getString("Name Updated"));
//            st.setDeletedBy(rs.getString("Name Deleted"));

            //Add pet to the ObservableList
            tranList.add(st);

        }

        //Return petList (ObservableList of Pets
        return tranList;
    }

    //Update a servicetransaction's entries
    public static void updateEntries(String Logged, String Id, Date date, String Servicedetails_Id, String Employees_id,
                                     String Pets_id, int isPaid)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Servicetransaction" +
                        " SET date = '" + date + "' " +
                        "    , total = total + (SELECT price FROM Servicedetails WHERE id = '" + Servicedetails_Id + "')" +
                        "    , Pets_id = '" + Pets_id + "' " +
                        "    , Employees_id = '" + Employees_id + "' " +
                        "    , updatedBy = '" + Logged + "' " +
                        "    , updatedAt = NOW() " +
                        "    , isPaid = '" + isPaid + "' " +
                        "    WHERE id = '" + Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //Update a servicetransaction's entries
    public static void updatePayment(String Logged, String Id, double total, int isPaid)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Servicetransaction" +
                        " SET" +
                        " total = '" + total + "' "+
                        ", updatedBy = '" + Logged + "' " +
                        ", updatedAt = NOW() " +
                        ", isPaid = '" + isPaid + "' " +
                        " WHERE id = '" + Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE a servicetransaction
    public static void deleteServiceTransactionWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM servicetransaction " +
                        "WHERE id = '" + Id + "' AND isPaid = '0';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE an transaction
    public static void softDeleteTransactionWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Servicetransaction " +
                        "SET " +
                        "deletedAt = NOW()" +
                        ", deletedBy = " + Logged + " " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT a Service Transaction
    public static void insertTransaction(String Logged, String Id, Date date, String Pets_id,
                                         String Employees_id, int isPaid)
            throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Servicetransaction " +
                        " (id, date, total , Pets_id, Employees_id " +
                        "    , createdBy, createdAt " +
                        "    , isPaid) " +
                        "    VALUES  " +
                        "    ('" + Id + "', '" + date + "', NULL,'"+ Pets_id + "', '" + Employees_id + "', '" + Logged + "', NOW(), '" + isPaid + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
