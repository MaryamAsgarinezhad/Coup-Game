package com.example.demo1;

import Model.Card;
import Model.Suit;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Board.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Coup Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Suit> person=new ArrayList<>(Arrays.asList(Suit.Duke,Suit.Ambassador));
        ArrayList<Suit> Bot1=new ArrayList<>(Arrays.asList(Suit.Captain,Suit.Ambassador));
        ArrayList<Suit> Bot2=new ArrayList<>(Arrays.asList(Suit.Contessa,Suit.Captain));
        ArrayList<Suit> Bot3=new ArrayList<>(Arrays.asList(Suit.Assassin,Suit.Contessa));

        ArrayList<Integer> bots=new ArrayList<>(Arrays.asList(1,2,3));
        Settings settings=new Settings(bots,person,Bot1,Bot2,Bot3,2,2,2,2);

        Gson gson = new GsonBuilder().create();
        String string=gson.toJson(settings);

        try(FileWriter fw1=new FileWriter("settings")) {
            fw1.write(string);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        launch();
    }
}