package Shoscho;

import com.example.topclean.Cleaner.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InventorySupplyManagement {

    @FXML private VBox inventorySection;
    @FXML private TableView<InventoryItem> inventoryTable;
    @FXML private TableColumn<InventoryItem, String> itemNameCol;
    @FXML private TableColumn<InventoryItem, Integer> quantityCol;
    @FXML private TableColumn<InventoryItem, Integer> reorderLevelCol;
    @FXML private TableColumn<InventoryItem, String> statusCol;

    @FXML private TextField itemIdField;
    @FXML private TextField statusField1;

    private ObservableList<InventoryItem> inventoryData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemName());
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantity().asObject());
        reorderLevelCol.setCellValueFactory(cellData -> cellData.getValue().reorderLevel().asObject());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().status());

        // Sample data
        inventoryData.addAll(
                new InventoryItem("Paper", 500, 100, "In Stock"),
                new InventoryItem("Ink", 50, 30, "Low Stock")
        );

        inventoryTable.setItems(inventoryData);
    }

    @FXML
    void handleLogout(ActionEvent event) {
        System.out.println("Logout clicked");
        // Add logout logic here
    }

    @FXML
    void handleOpenInventorySection(ActionEvent event) {
        inventorySection.setVisible(true);
    }

    @FXML
    void handleUpdateStatus(ActionEvent event) {
        String itemId = itemIdField.getText();
        String newStatus = statusField1.getText();

        for (InventoryItem item : inventoryData) {
            if (item.getItemName().equalsIgnoreCase(itemId)) {
                item.setStatus(newStatus);
                inventoryTable.refresh();
                break;
            }
        }
    }

    public InventorySupplyManagement(VBox inventorySection, TableView<InventoryItem> inventoryTable, TableColumn<InventoryItem, String> itemNameCol, TableColumn<InventoryItem, Integer> quantityCol, TableColumn<InventoryItem, Integer> reorderLevelCol, TableColumn<InventoryItem, String> statusCol, TextField itemIdField, ObservableList<InventoryItem> inventoryData, TextField statusField1) {
        this.inventorySection = inventorySection;
        this.inventoryTable = inventoryTable;
        this.itemNameCol = itemNameCol;
        this.quantityCol = quantityCol;
        this.reorderLevelCol = reorderLevelCol;
        this.statusCol = statusCol;
        this.itemIdField = itemIdField;
        this.inventoryData = inventoryData;
        this.statusField1 = statusField1;
    }

    public VBox getInventorySection() {
        return inventorySection;
    }

    public void setInventorySection(VBox inventorySection) {
        this.inventorySection = inventorySection;
    }

    public TableView<InventoryItem> getInventoryTable() {
        return inventoryTable;
    }

    public void setInventoryTable(TableView<InventoryItem> inventoryTable) {
        this.inventoryTable = inventoryTable;
    }

    public TableColumn<InventoryItem, String> getItemNameCol() {
        return itemNameCol;
    }

    public void setItemNameCol(TableColumn<InventoryItem, String> itemNameCol) {
        this.itemNameCol = itemNameCol;
    }

    public TableColumn<InventoryItem, Integer> getQuantityCol() {
        return quantityCol;
    }

    public void setQuantityCol(TableColumn<InventoryItem, Integer> quantityCol) {
        this.quantityCol = quantityCol;
    }

    public TableColumn<InventoryItem, Integer> getReorderLevelCol() {
        return reorderLevelCol;
    }

    public void setReorderLevelCol(TableColumn<InventoryItem, Integer> reorderLevelCol) {
        this.reorderLevelCol = reorderLevelCol;
    }

    public TableColumn<InventoryItem, String> getStatusCol() {
        return statusCol;
    }

    public void setStatusCol(TableColumn<InventoryItem, String> statusCol) {
        this.statusCol = statusCol;
    }

    public TextField getItemIdField() {
        return itemIdField;
    }

    public void setItemIdField(TextField itemIdField) {
        this.itemIdField = itemIdField;
    }

    public TextField getStatusField1() {
        return statusField1;
    }

    public void setStatusField1(TextField statusField1) {
        this.statusField1 = statusField1;
    }

    public ObservableList<InventoryItem> getInventoryData() {
        return inventoryData;
    }

    public void setInventoryData(ObservableList<InventoryItem> inventoryData) {
        this.inventoryData = inventoryData;
    }

    @Override
    public String toString() {
        return "InventorySupplyManagement{" +
                "inventorySection=" + inventorySection +
                ", inventoryTable=" + inventoryTable +
                ", itemNameCol=" + itemNameCol +
                ", quantityCol=" + quantityCol +
                ", reorderLevelCol=" + reorderLevelCol +
                ", statusCol=" + statusCol +
                ", itemIdField=" + itemIdField +
                ", statusField1=" + statusField1 +
                ", inventoryData=" + inventoryData +
                '}';
    }
}
