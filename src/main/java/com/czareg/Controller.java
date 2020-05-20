package com.czareg;

import com.czareg.model.choicebox.GetWorlds;
import com.czareg.model.choicebox.World;
import com.czareg.model.graph.GetWorld;
import com.czareg.model.graph.Player;
import com.czareg.model.graph.WorldInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private CategoryAxis xAxis;
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
        barChart.getData().clear();
        barChart.layout();

        String url = String.format("https://api.tibiadata.com/v2/world/%s.json", newValue);
        GetWorld getWorld = mapper.readValue(new URL(url), GetWorld.class);
        WorldInformation world_information = getWorld.getWorld().getWorld_information();
        String title = String.format("%s online players: %d %s", newValue, world_information.getPlayers_online(), world_information.getPvp_type());
        barChart.setTitle(title);
        List<Player> players = getWorld.getWorld().getPlayers_online();
        if (players == null) {
            LOG.error("Players is null");
            return;
        }
        int low = 1;
        int high = 100;
        List<String> labels = new ArrayList<>();
        XYChart.Series series = new XYChart.Series();
        series.setName(newValue);
        for (int i = 0; i < 15; i++) {
            String label = String.format("%s-%s", low, high);
            labels.add(label);

            XYChart.Data data = new XYChart.Data(label, players.stream()
                    .map(Player::getLevel)
                    .filter(getIntegerPredicate(low, high))
                    .count());
            series.getData().add(data);

            low += 100;
            high += 100;
        }
        barChart.getData().add(series);
        xAxis.setCategories(FXCollections.observableList(labels));

    }

    private Predicate<Integer> getIntegerPredicate(int low, int high) {
        return l -> l >= low && l <= high;
    }
}
