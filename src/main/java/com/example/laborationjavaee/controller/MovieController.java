package com.example.laborationjavaee.controller;

import com.example.laborationjavaee.dto.MovieDTO;
import com.example.laborationjavaee.entity.Movie;
import com.example.laborationjavaee.exception.ExceptionForID;
import com.example.laborationjavaee.mapper.Mapper;
import com.example.laborationjavaee.repository.MovieRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;


    @Path("/movies")
    public class MovieController {

        @Inject
        MovieRepository movieRepository;

        @Inject
        Mapper mapper;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<MovieDTO> getAll(@QueryParam("name") Optional<String> name) {
            List<Movie> movies = name.map(movieRepository::findAllByName).orElse(movieRepository.findAll());
            return mapper.map(movies);
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getOne(@PathParam("id") Long id) {
            var movie = movieRepository.findOne(id);
            if(movie.isPresent())
               return Response.ok().entity(movie.get()).build();
            throw new ExceptionForID("Not found Id: " + id);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addOne(@Valid Movie movie) {
            movieRepository.insertMovie(movie);
            return Response.created(URI.create("movies/" + movie.getId())).build();
        }


        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response update(@PathParam("id") Long id, MovieDTO movieDTO) {
            var movie = movieRepository.findOne(id);
            if(movie.isEmpty())
                throw new ExceptionForID("Not found Id: " + id);
            movieRepository.update(id, movieDTO);
            return Response.ok().build();
        }
        @DELETE
        @Path("/{id}")
        public Response deleteOne(@PathParam("id") Long id) {
            movieRepository.deleteMovie(id);
            return Response.noContent().build();
        }

    }

//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public List<MovieDTO> getAll(@QueryParam("name") String name) {
//            if (name == null)
//                return mapper.map(movieRepository.findAll());
//
//            return mapper.map(movieRepository.findAllByName(name));
//        }
//
//
//        @GET
//        @Path("/{id}")
//        @Produces(MediaType.APPLICATION_JSON)
//        public Response getOne(@PathParam("id")Long id) {
//            var movie = movieRepository.findOne(id);
//            if(movie.isPresent())
//                return Response.ok().entity(movie.get()).build();
//            return Response.status(404).build();
//
//
//
//        }
//        @POST
//        @Consumes(MediaType.APPLICATION_JSON)
//        public Response addOne(@Valid Movie movie) {
//            movieRepository.insertMovie(movie);
//            return Response.created(URI.create("movies/" + movie.getId())).build();
//        }
//
//        @DELETE
//        @Path("/{id}")
//        public void deleteOne(@PathParam("id")Long id) {
//            movieRepository.deleteMovie(id);
//
//        }
//
////       @PUT
////       @Path("/{id}")
////       @Consumes(MediaType.APPLICATION_JSON)
////       @Produces(MediaType.APPLICATION_JSON)
////        public Response update(@PathParam("id") Long id, MovieDTO movie) {
////        return Response.ok().entity(mapper.map(movieRepository.update(id, mapper.map(movie)))).build();
////    }
//
//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public List<Movie> filter(@QueryParam("name") String name) {
//            return movieRepository.findAll().stream().filter(e-> e.getName().equals(name)).toList();
//
//        }
//    }
//
//




