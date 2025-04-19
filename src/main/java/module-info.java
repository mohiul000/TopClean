module com.example.top_clean {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging; // <- This is what you need to add

    opens com.example.top_clean to javafx.fxml;
    opens com.example.top_clean.AdministrationOfficer to javafx.fxml;
    exports com.example.top_clean;
    exports com.example.top_clean.AdministrationOfficer;
}
