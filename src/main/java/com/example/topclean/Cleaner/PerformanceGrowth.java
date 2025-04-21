package com.example.topclean.Cleaner;

import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PerformanceGrowth
{
    @javafx.fxml.FXML
    private Label starsL;
    @javafx.fxml.FXML
    private Label CleanerLevelL;
    @javafx.fxml.FXML
    private ComboBox monthCB;
    @javafx.fxml.FXML
    private Label avlRateL;
    @javafx.fxml.FXML
    private Label successScoreL;
    @javafx.fxml.FXML
    private LineChart linechart;
    @javafx.fxml.FXML
    private NumberAxis numberaxis;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadbtnOnAction(ActionEvent actionEvent) {
    }
}