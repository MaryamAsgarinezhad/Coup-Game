package com.example.demo1;

import Model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Players {
    public static final Players instance=new Players();

    public Player human;
    public Player A;
    public Player B;
    public Player C;

    public ArrayList<Player> allPlayers;
    public ArrayList<Integer> removedPlayers=new ArrayList<>(Arrays.asList(0,0,0,0,0));

    public Player getHuman() {
        return human;
    }

    public Player getA() {
        return A;
    }

    public Player getB() {
        return B;
    }

    public Player getC() {
        return C;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<Integer> getRemovedPlayers() {
        return removedPlayers;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public void setA(Player a) {
        A = a;
    }

    public void setB(Player b) {
        B = b;
    }

    public void setC(Player c) {
        C = c;
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public Players(){
//        System.out.println("inhhhhhhhh"+instance);
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("settings");
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            String s=jsonObject.get("bots").toString();
            String[] s1=s.substring(1,s.length()-1).split(",");

            A=buildPlayer(s1[0],2);
            B=buildPlayer(s1[1],3);
            C=buildPlayer(s1[2],4);

            String Acard=jsonObject.get("person_hand").toString().substring(1,jsonObject.get("person_hand").toString().length()-1);
            String[] Acard1=Acard.split(",");
            Card card1=new Card(MakeSuit(Acard1[0].substring(1,Acard1[0].length()-1)));
            Card card2=new Card(MakeSuit(Acard1[1].substring(1,Acard1[1].length()-1)));

            int Acoin=Integer.parseInt(jsonObject.get("person_coins").toString());
            this.human= Human.getInstance(true,new ArrayList<>(Arrays.asList(card1,card2)),Acoin,1);

            setAllPlayers(new ArrayList<>(Arrays.asList(A,B,C,human)));
        }
        catch (IOException I){
        }
        catch (ParseException p){
        }
    }


    public static Players getInstance() throws IOException, ParseException {
        return Players.instance;
    }

    public Suit MakeSuit(String string){
        if(string.equals("Duke")){
            return Suit.Duke;
        }
        if(string.equals("Assassin")){
            return Suit.Assassin;
        }
        if(string.equals("Captain")){
            return Suit.Captain;
        }
        if(string.equals("Ambassador")){
            return Suit.Ambassador;
        }
        if(string.equals("Contessa")){
            return Suit.Contessa;
        }
        return null;
    }

    public Player buildPlayer(String i,int j) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("settings");
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        String read="";
        String Cionread="";

        if(j==2){
            read="A_hand";
            Cionread="A_coins";
        }
        if(j==3){
            read="B_hand";
            Cionread="B_coins";
        }
        if(j==4){
            read="C_hand";
            Cionread="C_coins";
        }

        String Acard=jsonObject.get(read).toString().substring(1,jsonObject.get(read).toString().length()-1);
        String[] Acard1=Acard.split(",");
        Card card1=new Card(MakeSuit(Acard1[0].substring(1,Acard1[0].length()-1)));
        Card card2=new Card(MakeSuit(Acard1[1].substring(1,Acard1[1].length()-1)));

        int Acoin=Integer.parseInt(jsonObject.get(Cionread).toString());

        if(i.equals("1")){
            return Bot1.getInstance(false,new ArrayList<>(Arrays.asList(card1,card2)),Acoin,j);
        }
        if(i.equals("2")){
            return Bot2.getInstance(false,new ArrayList<>(Arrays.asList(card1,card2)),Acoin,j);
        }
        if(i.equals("3")){
            return Bot3.getInstance(false,new ArrayList<>(Arrays.asList(card1,card2)),Acoin,j);
        }
        if(i.equals("4")){
            return Bot4.getInstance(false,new ArrayList<>(Arrays.asList(card1,card2)),Acoin,j);
        }

        return null;
    }
}
