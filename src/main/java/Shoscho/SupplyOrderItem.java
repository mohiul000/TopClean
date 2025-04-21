package Shoscho;

    public class SupplyOrderItem {
        private String item;
        private int quantity;

        public SupplyOrderItem(String item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public String getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


