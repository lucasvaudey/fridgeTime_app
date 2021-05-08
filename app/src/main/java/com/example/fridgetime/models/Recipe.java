package com.example.fridgetime.models;

import java.lang.reflect.Array;
import java.util.Map;

public class Recipe {
    String title;
    Array description;
    Map<String, Integer> ingredient;

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    int person;
    int authorID;

    public String getTitle() {
        return title;
    }

    public Array getDescription() {
        return description;
    }

    public Map<String, Integer> getIngredient() {
        return ingredient;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(Array description) {
        this.description = description;
    }

    public void setIngredient(Map<String, Integer> ingredient) {
        this.ingredient = ingredient;
    }
}
