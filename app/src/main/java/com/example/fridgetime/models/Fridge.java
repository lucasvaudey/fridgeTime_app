package com.example.fridgetime.models;

import java.util.List;
import java.util.Map;

public class Fridge {
    Map<String, Integer> ingredients;
    int person;

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public Map<String, Integer> getToBuy() {
        return toBuy;
    }

    public void setToBuy(Map<String, Integer> toBuy) {
        this.toBuy = toBuy;
    }

    Map<String, Integer> toBuy;
}
