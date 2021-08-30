package lk.ijse.pos.dao;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.model.Orders;
import lk.ijse.pos.view.tblmodel.OrderDetailTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderDAOImpl implements OrderDAO {




    public boolean addOrder(Orders orders) throws Exception {


        return CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?)",orders.getId(),
                orders.getDate(),orders.getCustomerId());


    }

    public boolean updateOrder(Orders orders) throws Exception {
        return false;

    }

    public boolean deleteOrder(String id) throws Exception {
        return false;


    }

    @Override
    public Item searchItem(String s) throws Exception {
        return null;
    }


}
