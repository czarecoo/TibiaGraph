package com.czareg;

import com.czareg.model.AllWords;
import com.czareg.model.World;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Controller {
    private static final Logger LOG = LoggerFactory.getLogger(TibiaGraph.class);

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button refreshButton;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private void initialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AllWords allWords = mapper.readValue(new URL("https://api.tibiadata.com/v2/worlds.json"), AllWords.class);
        List<World> allworlds = allWords.getWorlds().getAllworlds();

        choiceBox.getItems().add("All");
        for(World world:allworlds){
            choiceBox.getItems().add(world.getName());
        }

        choiceBox.getSelectionModel().select(1);

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
        LOG.info(newValue);

        barChart.setTitle(newValue);
    }
}
