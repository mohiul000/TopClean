package com.example.topclean.Cleaner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class ManageInventory {

    @FXML
    private TextField unitTF;
    @FXML
    private TableView<InventoryItem> inventoryTV;
    @FXML
    private ComboBox<String> actionTypeCB;
    @FXML
    private TableColumn<InventoryItem, String> unitTC;
    @FXML
    private TableColumn<InventoryItem, Integer> quantityTC;
    @FXML
    private TableColumn<InventoryItem, String> actionTC;
    @FXML
    private TableColumn<InventoryItem, String> itemNameTC;
    @FXML
    private TextField quantityTF;
    @FXML
    private TextField itemNameTF;

    private static final String INVENTORY_FILE = "inventory.bin";
    private ObservableList<InventoryItem> inventoryList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitTC.setCellValueFactory(new PropertyValueFactory<>("unit"));
        actionTC.setCellValueFactory(new PropertyValueFactory<>("action"));

        inventoryTV.setItems(inventoryList);
        actionTypeCB.getItems().addAll("Add", "Update");
        loadInventory();
    }

    private void loadInventory() {
        inventoryList.clear();
        File file = new File(INVENTORY_FILE);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        InventoryItem item = (InventoryItem) ois.readObject();
                        inventoryList.add(item);
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void addAndUpdatebtnOnAction(ActionEvent event) {
        String itemName = itemNameTF.getText().trim();
        String quantityText = quantityTF.getText().trim();
        String unit = unitTF.getText().trim();
        String actionType = actionTypeCB.getValue();

        if (itemName.isEmpty() || quantityText.isEmpty() || unit.isEmpty() || actionType == null) {
           //
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            InventoryItem newItem = new InventoryItem(itemName, quantity, unit, actionType);

            boolean itemExists = false;
            for (int i = 0; i < inventoryList.size(); i++) {
                if (inventoryList.get(i).getItemName().equals(itemName)) {
                    inventoryList.set(i, newItem); // Update alreay have in inventory item
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists && actionType.equals("Add")) {
                inventoryList.add(newItem);
            } else if (itemExists && actionType.equals("Add")) {
                //
            } else if (!itemExists && actionType.equals("Update")) {

            }

            saveInventory();
            clearInputFields();

        } catch (NumberFormatException e) {
            //
        }
    }

    private void saveInventory() {
        try (FileOutputStream fos = new FileOutputStream(INVENTORY_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (InventoryItem item : inventoryList) {
                oos.writeObject(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        itemNameTF.clear();
        quantityTF.clear();
        unitTF.clear();
        actionTypeCB.setValue(null);
    }
}