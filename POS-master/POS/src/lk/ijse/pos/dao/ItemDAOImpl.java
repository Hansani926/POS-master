package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean addItem(Item item) throws Exception {
       /* Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");


        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, new BigDecimal(String.valueOf(item.getUnitPrice())));
        pstm.setObject(4, item.getQtyOnHand());
        return (pstm.executeUpdate() > 0);*/
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean updateItem(Item item) throws Exception {
       /* Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setObject(1, item.getCode());
        pstm.setObject(2, new BigDecimal(String.valueOf(item.getUnitPrice())));
        pstm.setObject(3, item.getQtyOnHand());
        pstm.setObject(4, item.getCode());
        return (pstm.executeUpdate() > 0);*/
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override

    public boolean deleteItem(String code) throws Exception {

        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

@Override
    public ArrayList<Item> getAllItems() throws Exception {
       /* Connection connection = DBConnection.getInstance().getConnection();

        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<Item> alItems = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4));
            alItems.add(item);
        }
        return alItems;*/
    ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
    ArrayList<Item> itemList = new ArrayList<>();
    while (resultSet.next()) {
        itemList.add(
                new Item(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4))
        );
    }
    return itemList;
    }

@Override
    public Item searchItem(String s) {
        return null;
    }

@Override
    public boolean updateItemQtyOnHand(String code,int qtyOnHand) throws Exception {
       /* Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");
        pstm.setObject(1, qtyOnHand);
        pstm.setObject(2, code);
        return (pstm.executeUpdate() > 0);*/
    return CrudUtil.execute("UPDATE Item SET QtyonHand= (qtyOnHand -?) WHERE ItemCode=?",code,qtyOnHand);
    }



}
