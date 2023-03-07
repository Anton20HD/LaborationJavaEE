package com.example.laborationjavaee.dto;

import com.example.laborationjavaee.entity.Movie;

public class MovieDTO {

    private Long id;

    String name;

    String category;



    public MovieDTO() {
    }



    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.category = movie.getCategory();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
