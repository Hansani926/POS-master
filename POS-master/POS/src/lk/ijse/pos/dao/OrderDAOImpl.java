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


public class OrderDAOImpl {

    /*CustomerDAOImpl customerDAO;
    ItemDAOImpl itemDAO;
  public String txtCustomerName;
    public  String  txtDescription;
    public  String  txtUnitPrice;

    public  String txtQtyOnHand;
    public ArrayList<Object> cmbCustomerID;
    public ArrayList<Object> cmbItemCode;

    public boolean addOrder(Customer customer) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();


        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setObject(1, customer.getcID());
        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            String customerName = rst.getString(2);

        customer.setName("Customer Name");



        }
        return (pstm.executeUpdate() > 0);
    }
        public boolean changed(Item item) throws Exception {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code = ?");
            pstm.setObject(1, item.getCode());

            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                String description = rst.getString(2);
                double unitPrice = rst.getDouble(3);
                int qtyOnHand = rst.getInt(4);

               item.setDescription(description);
                item.setUnitPrice(unitPrice);
                item.setQtyOnHand(qtyOnHand);

            }
            return (pstm.executeUpdate() > 0);

        }

    public ArrayList<Date> loadalldata() throws Exception {




        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        cmbCustomerID.getItems.removeAll(cmbCustomerID.getItems());
        while (rst.next()) {
            String id = rst.getString(1);
            cmbCustomerID.getItems().add(id);
        }
        rst = stm.executeQuery("SELECT * FROM Item");
        cmbItemCode.getItems().removeAll(cmbItemCode.getItems());
        while (rst.next()) {
            String itemCode = rst.getString(1);
            cmbItemCode.getItems().add(itemCode);
        }

     return loadalldata();
    }

    public boolean placeOrder(Orders orders) throws Exception {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Orders VALUES (?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, orders.getId());
            pstm.setObject(2, orders.getDate());
            pstm.setObject(3, cmbCustomerID.gets);
            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();


            }


            pstm = connection.prepareStatement("INSERT INTO OrderDetails VALUES (?,?,?,?)");


            for (OrderDetailTM orderDetail : olOrderDetails) {
                pstm.setObject(1, orders.getId());
                pstm.setObject(2, orderDetail.getItemCode());
                pstm.setObject(3, orderDetail.getQty());
                pstm.setObject(4, orderDetail.getUnitPrice());
                affectedRows = pstm.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();

                }
                int qtyOnHand = 0;

                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM Item WHERE code='" + orderDetail.getItemCode() + "'");
                if (rst.next()) {
                    qtyOnHand = rst.getInt(4);
                }
                PreparedStatement pstm2 = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");
                pstm2.setObject(1, qtyOnHand - orderDetail.getQty());
                pstm2.setObject(2, orderDetail.getItemCode());

                affectedRows = pstm2.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();

                }

            }

            connection.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Placed", ButtonType.OK);
            alert.show();
        }

    }

    }*/

    public boolean addOrder(Orders orders) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Orders VALUES (?,?,?)");

        pstm.setObject(1, orders.getId());
        pstm.setObject(2, orders.getDate());
        pstm.setObject(3, orders.getCustomerId());

        return (pstm.executeUpdate()>0);


    }

    public boolean updateOrder(Orders orders) throws Exception {
       throw  new UnsupportedOperationException("This feacher is not supported yet");

    }

    public boolean deleteOrder(String id) throws Exception {
     /*   Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Orders WHERE id=?");
        pstm.setObject(1, id);
        return pstm.executeUpdate()>0;*/

        throw  new UnsupportedOperationException("This feacher is not supported yet");
    }











}
