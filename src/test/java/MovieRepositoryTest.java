import com.example.laborationjavaee.Movie.Movie;
import com.example.laborationjavaee.Movie.MovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MovieRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    MovieRepository movieRepository;

    @Mock
    private Query query;

    Movie movie1 = new Movie(1L,"Test1","Action");
    Movie movie2 = new Movie(2L,"Test2","Comedy");

    List<Movie> expectedMovies = List.of(movie1,movie2);



    @Test
    void testFindAllMovies() {
        when(query.getResultList()).thenReturn(expectedMovies);
        when(entityManager.createQuery("SELECT m FROM Movie m")).thenReturn(query);

        var result = movieRepository.findAll();
        assertEquals(expectedMovies,result);
    }

    @Test
    void testFindOneMovie() {

        when(entityManager.find(Movie.class, 1L)).thenReturn(movie1);

        Optional<Movie> result = movieRepository.findOne(1L);
        assertEquals(movie1,result.get());
    }

    @Test
    void testFindMovieByName() {
        var name = "Test2";
        when(entityManager.createQuery("SELECT m FROM Movie m WHERE m.name like :name")).thenReturn(query);
        when(query.setParameter("name", name)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(movie2));

        var result = movieRepository.findByName(name);
        assertEquals(List.of(movie2),result);
    }



}
