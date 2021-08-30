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

        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean updateItem(Item item) throws Exception {

        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override

    public boolean deleteItem(String code) throws Exception {

        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

@Override
    public ArrayList<Item> getAllItems() throws Exception {

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

    return CrudUtil.execute("UPDATE Item SET QtyonHand= (qtyOnHand -?) WHERE ItemCode=?",code,qtyOnHand);
    }



}
