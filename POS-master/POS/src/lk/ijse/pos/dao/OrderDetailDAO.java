package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface OrderDetailDAO {
    boolean addOrderDetail(OrderDetails orderDetails) throws Exception;
}
