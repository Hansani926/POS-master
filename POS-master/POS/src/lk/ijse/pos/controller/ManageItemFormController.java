package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.AppInitializer;
import lk.ijse.pos.dao.CustomerDAOImpl;
import lk.ijse.pos.dao.ItemDAO;
import lk.ijse.pos.dao.ItemDAOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.view.tblmodel.CustomerTM;
import lk.ijse.pos.view.tblmodel.ItemTM;


import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/


public class ManageItemFormController implements Initializable{

    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<ItemTM> tblItems;

    private boolean addNew = true;

    ItemDAO itemDAO=new ItemDAOImpl();

    private void loadAllItems(){

        try {

            ArrayList<Item> allItems=itemDAO.getAllItems();
            ArrayList<ItemTM>allItemForTable=new ArrayList<>();

            for (Item item :allItems) {
               allItemForTable.add(new ItemTM(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
            }



            ObservableList<ItemTM> olItems = FXCollections.observableArrayList(allItemForTable);

            tblItems.setItems(olItems);

        } catch (Exception ex) {

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblItems.getColumns().get(0).setStyle("-fx-alignment: center");
        tblItems.getColumns().get(2).setStyle("-fx-alignment: center-right");
        tblItems.getColumns().get(3).setStyle("-fx-alignment: center-right");

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadAllItems();

        tblItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {

                if (newValue == null){
                    addNew = true;
                    clearTextFields();
                    return;
                }

                txtItemCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtUnitPrice.setText(newValue.getUnitPrice().toPlainString());
                txtQty.setText(newValue.getQtyOnHand() + "");

                addNew = false;

            }
        });
    }

    private void clearTextFields(){
        txtItemCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQty.setText("");
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        AppInitializer.navigateToHome(root, (Stage) root.getScene().getWindow());
    }

    @FXML
    private void btnAddNewItem_OnAction(ActionEvent event) {

        tblItems.getSelectionModel().clearSelection();
        txtItemCode.requestFocus();
        addNew = true;

    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {

        if (addNew){

            try {



                boolean b =itemDAO.addItem(new Item(txtItemCode.getText(), txtDescription.getText(),new BigDecimal(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()) ));
                if (b) {
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to add new item", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
            }





        }else{

            try {


                boolean b = itemDAO.updateItem(new Item(txtItemCode.getText(), txtDescription.getText(), new BigDecimal(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText())));
                if (b) {
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to update the customer", ButtonType.OK).show();
                }


            } catch (Exception ex) {
                Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }


    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        Alert confirmAlert = new Alert(Alert.AlertType.WARNING, "Are you sure whether you want to delete the customer?", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.get() == ButtonType.YES) {

            String code = tblItems.getSelectionModel().getSelectedItem().getCode();

            try {

               
                boolean b = itemDAO.deleteItem(code);

                if (b) {
                    loadAllItems();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK);
                    a.show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
