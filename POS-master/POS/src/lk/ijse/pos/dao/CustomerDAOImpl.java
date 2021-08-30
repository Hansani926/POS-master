package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.view.tblmodel.CustomerTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean addCustomer(Customer customer) throws Exception {

        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",customer.getcID(),customer.getName(),customer.getAddress());
    }
@Override
    public boolean updateCustomer(Customer customer) throws Exception {

    return CrudUtil.execute("UPDATE Customer SET Name=?,Address=? Where id=?",customer.getName(),customer.getAddress(),customer.getcID());
    }
@Override
    public boolean deleteCustomer(String id) throws Exception {
    return CrudUtil.execute("DELETE FROM Customer WHERE  CID=?",id);

    }
@Override
    public Customer searchCustomer(String id) throws Exception {

    ResultSet resultSet=CrudUtil.execute("SELECT * FROM Customer WHERE id=?",id);
    if(resultSet.next()) {
        return new Customer(
                resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
    }else {
        return  null;
    }
    }
@Override
    public ArrayList<Customer> getAllCustomers() throws Exception {
    ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
    ArrayList<Customer> customerList = new ArrayList<>();
    while (resultSet.next()) {
        customerList.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
    }
    return customerList;
}

}
