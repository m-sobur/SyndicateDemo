package com.task10.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/*TODO change tableName*/
@DynamoDBTable(tableName = "cmtr-048d7043-Tables-test")
public class Table {
    private Integer id;
    private Integer number;
    private Integer places;
    private boolean isVip;
    private Integer minOrder;

    public Table(Integer id, Integer number, Integer places, Boolean isVip, Integer minOrder) {
        this.id = id;
        this.number = number;
        this.places = places;
        this.isVip = isVip;
        this.minOrder = minOrder;
    }

    public Table() {
    }

    @DynamoDBHashKey(attributeName = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @DynamoDBAttribute(attributeName = "places")
    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @DynamoDBAttribute(attributeName = "isVip")
    public Boolean isVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    @DynamoDBAttribute(attributeName = "minOrder")
    public Integer getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Integer minOrder) {
        this.minOrder = minOrder;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", number=" + number +
                ", places=" + places +
                ", isVip=" + isVip +
                ", minOrder=" + minOrder +
                '}';
    }
}
