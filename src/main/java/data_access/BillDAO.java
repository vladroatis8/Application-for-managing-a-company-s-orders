package data_access;

import model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clasa BillDAO ofera metode pentru manipularea datelor facturilor in baza de date.
 */
public class BillDAO {
    private Connection connection;

    public BillDAO() {
        connection = ConnectionFactory.getConnection();
    }
    /**
     * Insereaza o noua factura in baza de date.
     *
     * @param bill Factura de inserat
     */
    public void insert(Bill bill) {
        try {
            String query = "INSERT INTO Bill (idclient, nume, telefon, idprodus, cantitate, pret) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bill.idclient());
            preparedStatement.setString(2, bill.nume());
            preparedStatement.setString(3, bill.telefon());
            preparedStatement.setInt(4, bill.idprodus());
            preparedStatement.setInt(5, bill.cantitate());
            preparedStatement.setInt(6, bill.pret());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Bill");
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("idclient"),
                        resultSet.getString("nume"),
                        resultSet.getString("telefon"),
                        resultSet.getInt("idprodus"),
                        resultSet.getInt("cantitate"),
                        resultSet.getInt("pret")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
