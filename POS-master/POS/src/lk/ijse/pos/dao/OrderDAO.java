package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface OrderDAO {
  boolean addOrder(Orders orders) throws Exception ;
     boolean updateOrder(Orders orders) throws Exception ;;

    boolean deleteOrder(String id) throws Exception ;
    Item searchItem(String s) throws Exception;


}
