package com.example.topclean.Customer;

import com.example.topclean.AppendableObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WardrobeController {

    @FXML
    private TableView<Cloth> washedTV;
    @FXML
    private TableColumn<Cloth, Integer> washedClothIDTC;
    @FXML
    private TableColumn<Cloth, String> washedNameTC;
    @FXML
    private TableView<Cloth> nonWashedTV;
    @FXML
    private TableColumn<Cloth, Integer> nonWashedClothIDTC;
    @FXML
    private TableColumn<Cloth, String> nonWashedClothNameTC;
    @FXML
    private ComboBox<Integer> UpdateClothIDCB;
    @FXML
    private ComboBox<String> washedOrNonWasedTypeCB;
    @FXML
    private TextField addNewClothTF;

    private static final String WARDROBE_FILE = "wardrobe.bin";
    private ObservableList<Cloth> washedList = FXCollections.observableArrayList();
    private ObservableList<Cloth> nonWashedList = FXCollections.observableArrayList();
    private int customerId;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        loadWardrobeData();
        populateUpdateComboBox();
    }

    @FXML
    public void initialize() {
        washedClothIDTC.setCellValueFactory(new PropertyValueFactory<>("clothId"));
        washedNameTC.setCellValueFactory(new PropertyValueFactory<>("clothName"));
        nonWashedClothIDTC.setCellValueFactory(new PropertyValueFactory<>("clothId"));
        nonWashedClothNameTC.setCellValueFactory(new PropertyValueFactory<>("clothName"));

        washedTV.setItems(washedList);
        nonWashedTV.setItems(nonWashedList);

        washedOrNonWasedTypeCB.getItems().addAll("Washed", "Non-Washed");
    }

    private void loadWardrobeData() {
        washedList.clear();
        nonWashedList.clear();
        UpdateClothIDCB.getItems().clear();

        File file = new File(WARDROBE_FILE);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Cloth cloth = (Cloth) ois.readObject();
                        if (cloth.getCustomerId() == customerId) {
                            if (cloth.isWashed()) {
                                washedList.add(cloth);
                            } else {
                                nonWashedList.add(cloth);
                            }
                            UpdateClothIDCB.getItems().add(cloth.getClothId());
                        }
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.err.println("Error: ClassNotFoundException while reading wardrobe data.");
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.err.println("Error: IOException while reading wardrobe data.");
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: IOException while opening/reading wardrobe file.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void addNewClothbtnOnAction(ActionEvent actionEvent) {
        String clothName = addNewClothTF.getText().trim();
        if (!clothName.isEmpty()) {
            int clothId = generateUniqueClothId();
            Cloth newCloth = new Cloth(clothId, customerId, clothName, false); // Initially non-washed
            saveClothToFile(newCloth);
            loadWardrobeData(); // Refresh the table views and combo box
            addNewClothTF.clear();
        }
    }

    @FXML
    public void updatebtnOnAction(ActionEvent actionEvent) {
        Integer clothIdToUpdate = UpdateClothIDCB.getValue();
        String newStatus = washedOrNonWasedTypeCB.getValue();

        if (clothIdToUpdate != null && newStatus != null) {
            boolean isWashed = newStatus.equals("Washed");

            List<Cloth> allClothes = new ArrayList<>();
            File file = new File(WARDROBE_FILE);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    while (true) {
                        try {
                            Cloth cloth = (Cloth) ois.readObject();
                            allClothes.add(cloth);
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

            List<Cloth> updatedClothes = allClothes.stream()
                    .map(cloth -> {
                        if (cloth.getCustomerId() == customerId && cloth.getClothId() == clothIdToUpdate) {
                            return new Cloth(cloth.getClothId(), cloth.getCustomerId(), cloth.getClothName(), isWashed);
                        }
                        return cloth;
                    })
                    .collect(Collectors.toList());

            saveAllClothesToFile(updatedClothes);
            loadWardrobeData(); // Refresh the table views and combo box
        }
    }

    private void saveClothToFile(Cloth cloth) {
        File file = new File(WARDROBE_FILE);
        boolean append = file.exists();
        try (FileOutputStream fos = new FileOutputStream(file, append);
             ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(cloth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllClothesToFile(List<Cloth> clothes) {
        try (FileOutputStream fos = new FileOutputStream(WARDROBE_FILE, false); // Overwrite
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Cloth cloth : clothes) {
                oos.writeObject(cloth);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueClothId() {
        Random random = new Random();
        return random.nextInt(1000000);
    }

    private void populateUpdateComboBox() {
        UpdateClothIDCB.getItems().clear();
        File file = new File(WARDROBE_FILE);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Cloth cloth = (Cloth) ois.readObject();
                        if (cloth.getCustomerId() == customerId) {
                            UpdateClothIDCB.getItems().add(cloth.getClothId());
                        }
                    } catch (EOFException e) {
                        break;
                    } catch (ClassNotFoundException | IOException e) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}