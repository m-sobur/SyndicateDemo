package com.task07.model;

import java.util.List;

public class Ids {
    private List<String> ids;

    public Ids(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Ids{" +
                "ids=" + ids +
                '}';
    }
}
