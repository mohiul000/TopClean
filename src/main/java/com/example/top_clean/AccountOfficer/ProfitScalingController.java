package com.example.top_clean.AccountOfficer;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ProfitScalingController
{
    @javafx.fxml.FXML
    private CategoryAxis xAxisMonth;
    @javafx.fxml.FXML
    private BarChart barChartProfit;
    @javafx.fxml.FXML
    private ComboBox monthComboBox;
    @javafx.fxml.FXML
    private TextField costTextField;
    @javafx.fxml.FXML
    private NumberAxis yAxisNumber;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadBarChartButton(ActionEvent actionEvent) {
    }
}