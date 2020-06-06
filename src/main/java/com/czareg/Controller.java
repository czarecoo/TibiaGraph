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
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {
    public static final int MAX_LEVEL_BRACKET = 16;
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
    private List<String> allWorldNames;
    private GetWorlds getWorlds;

    @FXML
    private void initialize() throws IOException {
        mapper = new ObjectMapper();
        setUpChoiceBox(mapper);
        refreshButton.setOnAction(createButtonOnClickHandler());
    }

    private void setUpChoiceBox(ObjectMapper mapper) throws IOException {
        String url = "https://api.tibiadata.com/v2/worlds.json";
        URLConnection connection = createUrlConnection(url);
        getWorlds = mapper.readValue(connection.getInputStream(), GetWorlds.class);
        List<World> allworlds = getWorlds.getWorlds().getAllworlds();
        allWorldNames = allworlds.stream().map(World::getName).collect(Collectors.toList());
        choiceBox.getItems().add("All");
        for (World world : allworlds) {
            choiceBox.getItems().add(world.getName());
        }

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(createChoiceBoxOnSelectListener());
    }

    private ChangeListener<Number> createChoiceBoxOnSelectListener() {
        return (observableValue, oldValueIndex, newValueIndex) -> {
            String newValue = choiceBox.getItems().get((Integer) newValueIndex);
            select(newValue);
        };
    }

    private EventHandler<ActionEvent> createButtonOnClickHandler() {
        return e -> {
            String newValue = choiceBox.getValue();
            select(newValue);
        };
    }

    private void select(String newValue) {
        try {
            barChart.getData().clear();
            barChart.layout();
            if (newValue.equals("All")) {
                onSelectAll();
            } else {
                onSelectOne(newValue);
            }
        } catch (IOException e) {
            LOG.error("error", e);
        }
    }

    private void onSelectAll() throws IOException {
        String title = String.format("All online players: %d", getWorlds.getWorlds().getOnline());
        barChart.setTitle(title);
        List<List<Player>> playersForServer = new ArrayList<>();
        for (String world : allWorldNames) {
            String url = String.format("https://api.tibiadata.com/v2/world/%s.json", world);
            URLConnection connection = createUrlConnection(url);
            GetWorld getWorld = mapper.readValue(connection.getInputStream(), GetWorld.class);
            List<Player> players = getWorld.getWorld().getPlayers_online();
            if (players != null) {
                playersForServer.add(players);
            }
        }

        int low = 1;
        int high = 100;
        List<String> labels = new ArrayList<>();
        XYChart.Series series = new XYChart.Series();
        series.setName("All");
        LOG.info("All:");
        for (int i = 0; i < MAX_LEVEL_BRACKET; i++) {
            String label = String.format("%s-%s", low, high);
            labels.add(label);
            int count = 0;
            for (List<Player> players : playersForServer) {
                count += players.stream()
                        .map(Player::getLevel)
                        .filter(getIntegerPredicate(low, high))
                        .count();
            }
            LOG.info("Label: {}, count: {}", label, count);
            XYChart.Data data = new XYChart.Data(label, count);
            series.getData().add(data);

            low += 100;
            high += 100;
        }
        barChart.getData().add(series);
        xAxis.setCategories(FXCollections.observableList(labels));

    }

    private void onSelectOne(String newValue) throws IOException {
        String url = String.format("https://api.tibiadata.com/v2/world/%s.json", newValue);
        URLConnection connection = createUrlConnection(url);
        GetWorld getWorld = mapper.readValue(connection.getInputStream(), GetWorld.class);
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
        LOG.info(newValue);
        for (int i = 0; i < MAX_LEVEL_BRACKET; i++) {
            String label = String.format("%s-%s", low, high);
            labels.add(label);
            long count = players.stream()
                    .map(Player::getLevel)
                    .filter(getIntegerPredicate(low, high))
                    .count();
            LOG.info("Label: {}, count: {}", label, count);
            XYChart.Data data = new XYChart.Data(label, count);
            series.getData().add(data);

            low += 100;
            high += 100;
        }
        barChart.getData().add(series);
        xAxis.setCategories(FXCollections.observableList(labels));
    }

    private URLConnection createUrlConnection(String url) throws IOException {
        if (TibiaGraph.getProxy() != null) {
            return new URL(url).openConnection(TibiaGraph.getProxy());
        } else {
            return new URL(url).openConnection();
        }
    }

    private Predicate<Integer> getIntegerPredicate(int low, int high) {
        return l -> l >= low && l <= high;
    }
}
