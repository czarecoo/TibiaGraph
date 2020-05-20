package com.czareg;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class Controller {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button refreshButton;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private void initialize() {
        choiceBox.getItems().add("1");
        choiceBox.getItems().add("2");
        choiceBox.getSelectionModel().select(0);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(createChoiceBoxOnSelectListener());

        refreshButton.setOnAction(createButtonOnClickHandler());
    }

    private EventHandler<ActionEvent> createButtonOnClickHandler() {
        return e -> {
            String newValue = choiceBox.getValue();
            onSelect(newValue);
        };
    }

    private ChangeListener<Number> createChoiceBoxOnSelectListener() {
        return (observableValue, oldValueIndex, newValueIndex) -> {
            String newValue = choiceBox.getItems().get((Integer) newValueIndex);
            onSelect(newValue);
        };
    }

    private void onSelect(String newValue) {
        System.out.println(newValue);

        barChart.setTitle(newValue);
    }
}
