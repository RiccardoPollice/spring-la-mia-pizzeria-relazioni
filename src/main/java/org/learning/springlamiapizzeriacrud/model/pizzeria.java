package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.net.URL;

@Entity
@Table(name="pizze")
public class pizzeria {
    @Id
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    private URL image;

    // Get and Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column (nullable = false)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public BigDecimal getPrice () {
        return price
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
