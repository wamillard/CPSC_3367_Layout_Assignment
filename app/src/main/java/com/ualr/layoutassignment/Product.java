package com.ualr.layoutassignment;


public class Product
{
    private String name;
    private Double cost;

    public void Product()
    {
        this.name = "";
        this.cost = 0.00;
    }

    public void Product(String newName, Double newCost)
    {
        this.name = newName;
        this.cost = newCost;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public Double getCost()
    {
        return this.cost;
    }

    public void setCost(Double newCost)
    {
        this.cost = newCost;
    }
}
