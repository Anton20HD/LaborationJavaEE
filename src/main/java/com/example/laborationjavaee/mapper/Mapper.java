package com.example.laborationjavaee.mapper;


import com.example.laborationjavaee.Movie.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Mapper {

    public List<MovieDTO> map(List<Movie> all) {
        return all.stream().map(MovieDTO::new).toList();
    }

    public Movie map(MovieDTO movie) {
        var m =  new Movie();
        m.setId(movie.getId());
        m.setName(movie.getName());
        m.setCategory(movie.getCategory());
        return m;


    }

    public MovieDTO map(Movie movie) {
        return new MovieDTO(movie);


    }

}
