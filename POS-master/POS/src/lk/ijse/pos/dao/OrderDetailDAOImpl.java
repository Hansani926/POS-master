package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDetailDAOImpl {
   public boolean addOrderDetail(OrderDetails orderDetails) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO OrderDetail VALUES (?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, orderDetails.getOrderId());
        pstm.setObject(2, orderDetails.getItemCode());
        pstm.setObject(3, orderDetails.getQty());
        pstm.setObject(4, orderDetails.getUnitPrice());

        return pstm.executeUpdate()>0;

    }


}
