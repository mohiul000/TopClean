package com.example.topclean.Cleaner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SetAvailability {
    @FXML
    private TableColumn<Slot, Integer> slotIDTC;
    @FXML
    private TableColumn<Slot, LocalDate> dateTC;
    @FXML
    private TableColumn<Slot, String> timeTC;
    @FXML
    private TextField timeTF;
    @FXML
    private DatePicker dayDP;
    @FXML
    private TableView<Slot> slotsTV;

    private static final String SLOTS_FILE = "slots.dat";
    private ObservableList<Slot> slotList = FXCollections.observableArrayList();
    private int nextSlotId = 1;

    @FXML
    public void initialize() {
        // Initialize Table Columns
        slotIDTC.setCellValueFactory(new PropertyValueFactory<>("slotId"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeTC.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set TableView Data Source
        slotsTV.setItems(slotList);

        // Load existing slots from file
        loadSlots();
    }

    @FXML
    public void setSlotbtnOnAction(ActionEvent actionEvent) {
        LocalDate date = dayDP.getValue();
        String time = timeTF.getText();

        if (date == null || time.isEmpty()) {

            System.out.println("Please select a date and enter a time.");
            return;
        }

        Slot newSlot = new Slot(nextSlotId++, date, time);
        slotList.add(newSlot);
        saveSlots();
        clearInputFields();
        loadSlots();
    }

    private void loadSlots() {
        slotList.clear(); // clear loading
        File file = new File(SLOTS_FILE);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Slot slot = (Slot) ois.readObject();
                        slotList.add(slot);
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

        if (!slotList.isEmpty()) {
            nextSlotId = slotList.get(slotList.size() - 1).getSlotId() + 1;
        } else {
            nextSlotId = 1;
        }
    }

    private void saveSlots() {
        try (FileOutputStream fos = new FileOutputStream(SLOTS_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Slot slot : slotList) {
                oos.writeObject(slot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        dayDP.setValue(null);
        timeTF.clear();
    }


    public static class Slot implements Serializable {
        private int slotId;
        private LocalDate date;
        private String time;

        public Slot(int slotId, LocalDate date, String time) {
            this.slotId = slotId;
            this.date = date;
            this.time = time;
        }

        public int getSlotId() {
            return slotId;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }
    }
}
