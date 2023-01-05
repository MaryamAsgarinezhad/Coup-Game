package com.example.demo1;

import Model.Card;
import Model.Suit;

import java.util.ArrayList;
import java.util.Arrays;

public class Settings {
    public ArrayList<Integer> bots;

    public ArrayList<Suit> person_hand;
    public ArrayList<Suit> A_hand;
    public ArrayList<Suit> B_hand;
    public ArrayList<Suit> C_hand;

    public int person_coins;
    public int A_coins;
    public int B_coins;
    public int C_coins;

    public Settings(ArrayList<Integer> bots, ArrayList<Suit> person_hand, ArrayList<Suit> a_hand, ArrayList<Suit> b_hand, ArrayList<Suit> c_hand, int person_coins, int a_coins, int b_coins, int c_coins) {
        this.bots = bots;
        this.person_hand = person_hand;
        A_hand = a_hand;
        B_hand = b_hand;
        C_hand = c_hand;
        this.person_coins = person_coins;
        A_coins = a_coins;
        B_coins = b_coins;
        C_coins = c_coins;
    }

    public ArrayList<Integer> getBots() {
        return bots;
    }

    public ArrayList<Suit> getPerson_hand() {
        return person_hand;
    }

    public ArrayList<Suit> getA_hand() {
        return A_hand;
    }

    public ArrayList<Suit> getB_hand() {
        return B_hand;
    }

    public ArrayList<Suit> getC_hand() {
        return C_hand;
    }

    public int getPerson_coins() {
        return person_coins;
    }

    public int getA_coins() {
        return A_coins;
    }

    public int getB_coins() {
        return B_coins;
    }

    public int getC_coins() {
        return C_coins;
    }

    public void setBots(ArrayList<Integer> bots) {
        this.bots = bots;
    }

    public void setPerson_hand(ArrayList<Suit> person_hand) {
        this.person_hand = person_hand;
    }

    public void setA_hand(ArrayList<Suit> bot1_hand) {
        A_hand = bot1_hand;
    }

    public void setB_hand(ArrayList<Suit> bot2_hand) {
        B_hand = bot2_hand;
    }

    public void setC_hand(ArrayList<Suit> bot3_hand) {
        C_hand = bot3_hand;
    }

    public void setPerson_coins(int person_coins) {
        this.person_coins = person_coins;
    }

    public void setA_coins(int a_coins) {
        A_coins = a_coins;
    }

    public void setB_coins(int b_coins) {
        B_coins = b_coins;
    }

    public void setC_coins(int c_coins) {
        C_coins = c_coins;
    }
}
