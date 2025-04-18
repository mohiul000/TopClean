module com.example.topclean {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.example.topclean to javafx.fxml;
    opens com.example.topclean.Customer to javafx.fxml, javafx.base;
    opens com.example.topclean.Cleaner to javafx.fxml, javafx.base; // Add javafx.base here

    exports com.example.topclean;
    exports com.example.topclean.Customer;
    exports com.example.topclean.Cleaner; // It's good practice to export this package as well
}