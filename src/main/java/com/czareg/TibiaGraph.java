package com.czareg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class TibiaGraph extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(TibiaGraph.class);
    private static Proxy proxy;

    public static void main(String[] args) {
        setProxy(args);
        launch(TibiaGraph.class);
    }

    private static void setProxy(String[] args) {
        if (args.length == 2) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(args[0], Integer.valueOf(args[1])));
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/layout.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1024, 700);
        stage.setTitle("TibiaGraph");
        stage.setResizable(true);
        stage.setScene(scene);
        LOG.info("Started UI");
        stage.show();
    }

    public static Proxy getProxy() {
        return proxy;
    }
}