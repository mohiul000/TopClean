package Shoscho;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class inventoryItem {

        private final String itemName;
        private final IntegerProperty quantity;
        private final IntegerProperty reorderLevel;
        private final StringProperty status;

        public InventoryItem(String itemName, int quantity, int reorderLevel, String status) {
            this.itemName = new SimpleStringProperty(itemName);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.reorderLevel = new SimpleIntegerProperty(reorderLevel);
            this.status = new SimpleStringProperty(status);
        }

        public String getItemName() {
            return itemName.get();
        }

        public void setItemName(String value) {
            itemName.set(value);
        }

        public StringProperty itemNameProperty() {
            return itemName;
        }

        public int getQuantity() {
            return quantity.get();
        }

        public void setQuantity(int value) {
            quantity.set(value);
        }

        public IntegerProperty quantityProperty() {
            return quantity;
        }

        public int getReorderLevel() {
            return reorderLevel.get();
        }

        public void setReorderLevel(int value) {
            reorderLevel.set(value);
        }

        public IntegerProperty reorderLevelProperty() {
            return reorderLevel;
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String value) {
            status.set(value);
        }

        public StringProperty statusProperty() {
            return status;
        }
    }

