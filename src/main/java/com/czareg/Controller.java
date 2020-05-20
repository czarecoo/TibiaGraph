package com.czareg;

import com.czareg.model.choicebox.GetWorlds;
import com.czareg.model.choicebox.World;
import com.czareg.model.graph.GetWorld;
import com.czareg.model.graph.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;

public class Controller {
    private static final Logger LOG = LoggerFactory.getLogger(TibiaGraph.class);

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button refreshButton;

    @FXML
    private BarChart<String, Number> barChart;
    private ObjectMapper mapper;

    @FXML
    private void initialize() throws IOException {
        mapper = new ObjectMapper();
        setUpChoiceBox(mapper);

        refreshButton.setOnAction(createButtonOnClickHandler());
    }

    private void setUpChoiceBox(ObjectMapper mapper) throws IOException {
        GetWorlds getWorlds = mapper.readValue(new URL("https://api.tibiadata.com/v2/worlds.json"), GetWorlds.class);
        List<World> allworlds = getWorlds.getWorlds().getAllworlds();

        //choiceBox.getItems().add("All");
        for (World world : allworlds) {
            choiceBox.getItems().add(world.getName());
        }

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(createChoiceBoxOnSelectListener());
    }

    private ChangeListener<Number> createChoiceBoxOnSelectListener() {
        return (observableValue, oldValueIndex, newValueIndex) -> {
            String newValue = choiceBox.getItems().get((Integer) newValueIndex);
            try {
                onSelect(newValue);
            } catch (IOException e) {
                LOG.error("error", e);
            }
        };
    }

    private EventHandler<ActionEvent> createButtonOnClickHandler() {
        return e -> {
            String newValue = choiceBox.getValue();
            try {
                onSelect(newValue);
            } catch (IOException f) {
                LOG.error("error", f);
            }
        };
    }

    private void onSelect(String newValue) throws IOException {
        barChart.setTitle(newValue);

        String url = String.format("https://api.tibiadata.com/v2/world/%s.json", newValue);
        GetWorld getWorld = mapper.readValue(new URL(url), GetWorld.class);
        List<Player> players = getWorld.getWorld().getPlayers_online();

        int low = 1;
        int high = 100;
        for (int i = 0; i < 20; i++) {
            String label = String.format("%s-%s", low, high);
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(label, players.stream()
                    .map(Player::getLevel)
                    .filter(getIntegerPredicate(low, high))
                    .count()));
            barChart.getData().add(series);
            low += 100;
            high += 100;
        }

    }

    private Predicate<Integer> getIntegerPredicate(int low, int high) {
        return l -> l >= low && l <= high;
    }
}
