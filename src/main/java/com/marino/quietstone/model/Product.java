package com.marino.quietstone.model;

public class Product {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o){
        if (o instanceof Product){
            Product temp = (Product)o;
            if (this.id.equals(temp.getId()))
                return true;
        }
        return false;
    }
}
