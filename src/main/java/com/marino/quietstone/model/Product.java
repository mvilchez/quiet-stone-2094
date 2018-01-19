package com.marino.quietstone.model;

/**
 * Class Product
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
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

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
