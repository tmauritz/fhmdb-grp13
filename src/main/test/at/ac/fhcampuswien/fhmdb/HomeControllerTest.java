package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    @Test
    public void show_only_movies_of_the_selected_genre_filter(){
        //GIVEN
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie1);
        expectedMovies.add(movie2);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"",Genre.SCIENCE_FICTION);

        //THEN
        assertEquals(expectedMovies,filteredMovies);
    }

    @Test
    public void show_all_movies_if_all_genre_is_selected(){
        //GIVEN
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"",Genre.ALL);

        //THEN
        assertEquals(allTestMovies,filteredMovies);
    }
}