package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.view.tblmodel.ItemTM;
import sun.plugin.dom.html.HTMLBodyElement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl {
    public boolean addItem(Item item) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");


        pstm.setObject(1, txtCode.getText());
        pstm.setObject(2, txtDescription.getText());
        pstm.setObject(3, new BigDecimal(txtUnitPrice.getText()));
        pstm.setObject(4, Integer.parseInt(txtQty.getText()));
        return (pstm.executeUpdate() > 0);
    }

    public boolean updateItem(Item item) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setObject(1, txtDescription.getText());
        pstm.setObject(2, new BigDecimal(txtUnitPrice.getText()));
        pstm.setObject(3, Integer.parseInt(txtQty.getText()));
        pstm.setObject(4, txtItemCode.getText());
        return (pstm.executeUpdate() > 0);
    }

    public boolean deleteItem(String code) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");

        pstm.setObject(1, code);
        return (pstm.executeUpdate() > 0);
    }


    public ArrayList<Item> getAllItems() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<ItemTM> alItems = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4));
            alItems.add(item);
        }
        return alItems;
    }


}
