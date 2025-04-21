package Shoscho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplyOrderManagement implements Initializable {

    @FXML
    private Label SupplyOrderManagementLabel;

    @FXML
    private TableColumn<SupplyOrderItem, String> itemColumn;

    @FXML
    private ComboBox<String> itemComboBox;

    @FXML
    private TableView<SupplyOrderItem> orderTable;

    @FXML
    private TableColumn<SupplyOrderItem, Integer> quantityColumn;

    @FXML
    private TextField quantityField;

    private ObservableList<SupplyOrderItem> orderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Sample item list
        itemComboBox.setItems(FXCollections.observableArrayList("Paper", "Pens", "Staplers", "Folders"));

        // Setup table columns
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Bind data to table
        orderTable.setItems(orderList);
    }

    @FXML
    void handleAddItem(ActionEvent event) {
        String selectedItem = itemComboBox.getValue();
        String quantityText = quantityField.getText();

        if (selectedItem == null || quantityText.isEmpty()) {
            showAlert("Missing Information", "Please select an item and enter quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) throw new NumberFormatException();

            SupplyOrderItem newItem = new SupplyOrderItem(selectedItem, quantity);
            orderList.add(newItem);
            quantityField.clear();
            itemComboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Quantity must be a positive integer.");
        }
    }

    @FXML
    void handleSubmitOrder(ActionEvent event) {
        if (orderList.isEmpty()) {
            showAlert("No Items", "Please add items before submitting an order.");
            return;
        }

        // Logic for submitting the order can be added here.
        showAlert("Order Submitted", "Your order has been submitted successfully!");

        orderList.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public SupplyOrderManagement(Label supplyOrderManagementLabel, TableColumn<SupplyOrderItem, String> itemColumn, ComboBox<String> itemComboBox, TableView<SupplyOrderItem> orderTable, TableColumn<SupplyOrderItem, Integer> quantityColumn, TextField quantityField, ObservableList<SupplyOrderItem> orderList) {
        SupplyOrderManagementLabel = supplyOrderManagementLabel;
        this.itemColumn = itemColumn;
        this.itemComboBox = itemComboBox;
        this.orderTable = orderTable;
        this.quantityColumn = quantityColumn;
        this.quantityField = quantityField;
        this.orderList = orderList;
    }

    public Label getSupplyOrderManagementLabel() {
        return SupplyOrderManagementLabel;
    }

    public void setSupplyOrderManagementLabel(Label supplyOrderManagementLabel) {
        SupplyOrderManagementLabel = supplyOrderManagementLabel;
    }

    public TableColumn<SupplyOrderItem, String> getItemColumn() {
        return itemColumn;
    }

    public void setItemColumn(TableColumn<SupplyOrderItem, String> itemColumn) {
        this.itemColumn = itemColumn;
    }

    public ComboBox<String> getItemComboBox() {
        return itemComboBox;
    }

    public void setItemComboBox(ComboBox<String> itemComboBox) {
        this.itemComboBox = itemComboBox;
    }

    public TableView<SupplyOrderItem> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(TableView<SupplyOrderItem> orderTable) {
        this.orderTable = orderTable;
    }

    public TableColumn<SupplyOrderItem, Integer> getQuantityColumn() {
        return quantityColumn;
    }

    public void setQuantityColumn(TableColumn<SupplyOrderItem, Integer> quantityColumn) {
        this.quantityColumn = quantityColumn;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(TextField quantityField) {
        this.quantityField = quantityField;
    }

    public ObservableList<SupplyOrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(ObservableList<SupplyOrderItem> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "SupplyOrderManagement{" +
                "SupplyOrderManagementLabel=" + SupplyOrderManagementLabel +
                ", itemColumn=" + itemColumn +
                ", itemComboBox=" + itemComboBox +
                ", orderTable=" + orderTable +
                ", quantityColumn=" + quantityColumn +
                ", quantityField=" + quantityField +
                ", orderList=" + orderList +
                '}';
    }
}
