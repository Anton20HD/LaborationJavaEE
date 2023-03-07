package com.example.laborationjavaee.repository;

import com.example.laborationjavaee.dto.MovieDTO;
import com.example.laborationjavaee.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class MovieRepository {


    @PersistenceContext
    EntityManager entityManager;


    public List<Movie> findAll() {
        var query = entityManager.createQuery("SELECT m FROM Movie m");
        return (List<Movie>) query.getResultList();


        //Get all movie objects from database with entitymanager.


    }
    public Optional<Movie> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
        // Optional objekt som antingen innehåller ett movie objekt elelr som är empty.


    }
    public void insertMovie(Movie movie) {
        entityManager.persist(movie);



    }
    public void deleteMovie(Long id) {
        var movie = findOne(id);
        movie.ifPresent((m) -> entityManager.remove(m));

    }

    public List<Movie> findAllByName(String name) {
        var query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.name like :name");
        query.setParameter("name",name);
        return (List<Movie>) query.getResultList();
    }

    public Movie update(Long id, MovieDTO movie){
        var entity = entityManager.find(Movie.class, id);
        entity.setName(movie.getName());
        entity.setCategory(movie.getCategory());
        entityManager.persist(entity);
        return entity;
    }

}







