package com.thread.example.model;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    private final List<RestaurantTable> tables;

    public RestaurantManager(int tableCount) {
        this.tables = new ArrayList<>();
        for(int i = 0; i < tableCount; i++) {
            tables.add(new RestaurantTable());
        }
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }
}
