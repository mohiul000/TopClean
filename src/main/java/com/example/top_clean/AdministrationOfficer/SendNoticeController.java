package com.example.top_clean.AdministrationOfficer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SendNoticeController implements Initializable {

    @FXML
    private TextField noticeNameTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private DatePicker noticeDate;
    @FXML
    private TableView<sendNoticeModel> noticeTableView;
    @FXML
    private TableColumn<sendNoticeModel, String> noticeNameTableColumn;
    @FXML
    private TableColumn<sendNoticeModel, LocalDate> noticeDateTableColumn;
    @FXML
    private TableColumn<sendNoticeModel, String> noticeSubjectTableColumn;
    @FXML
    private TextArea descriptionShowTextArea;
    @FXML
    private TextArea loadNoticeTextArea;

    private ArrayList<String> noticeDescList = new ArrayList<>();
    private ObservableList<sendNoticeModel> noticeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Binding TableColumn to properties of the model class
        noticeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("noticeName"));
        noticeSubjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("noticeSubject"));
        noticeDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("noticeDate"));

        // Setting data to TableView
        noticeTableView.setItems(noticeList);
    }

    @FXML
    private void sendNoticeButtonOnClicked(ActionEvent event) {
        String name = noticeNameTextField.getText();
        String subj = subjectTextField.getText();
        LocalDate date = noticeDate.getValue();

        if (name.isEmpty() || subj.isEmpty() || date == null || noticeDescList.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        sendNoticeModel newNotice = new sendNoticeModel(name, subj, date, new ArrayList<>(noticeDescList));
        noticeList.add(newNotice);
        noticeTableView.setItems(noticeList);


        noticeDescList.clear();
        noticeNameTextField.clear();
        subjectTextField.clear();
        noticeDate.setValue(null);
        descriptionShowTextArea.clear();
    }

    @FXML
    private void loadNoticeButtonOnClicked(ActionEvent event) {
        sendNoticeModel selectedNotice = noticeTableView.getSelectionModel().getSelectedItem();
        if (selectedNotice == null) {
            System.out.println("No notice selected.");
            return;
        }

        loadNoticeTextArea.clear();
        for (String desc : selectedNotice.getNoticeDescription()) {
            loadNoticeTextArea.appendText(desc + "\n");
        }
    }

    @FXML
    private void addDescriptionButtonOnClicked(ActionEvent event) {
        String description = descriptionTextField.getText();
        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

        noticeDescList.add(description);
        descriptionShowTextArea.appendText(description + "\n");
        descriptionTextField.clear();
    }

    @FXML
    private void refreshButtonOnClick(ActionEvent event) {
        noticeTableView.refresh();
    }

    @FXML
    private void deleteNoticeButtonOnClicked(ActionEvent event) {
        sendNoticeModel selected = noticeTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("Please select a notice to delete.");
            return;
        }

        noticeList.remove(selected);
        noticeTableView.setItems(noticeList);  /
    }
}
