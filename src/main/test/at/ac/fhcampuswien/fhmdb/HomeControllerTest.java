package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    /* --------- Genre Filter --------*/
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

    /* --------- Query Filter --------*/
    @Test
    public void show_only_movies_with_search_query_matching_titles(){
        //GIVEN
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie2);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"Chrono Paradox",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }
    @Test
    public void show_only_movies_with_search_query_partly_matching_titles(){
        //GIVEN
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"ers in the Dark",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }

    @Test
    public void show_only_movies_with_search_query_matching_descriptions(){
        //GIVEN
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"starts receiving mysterious messages ",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }

    @Test
    public void show_all_movies_when_empty_query_is_sent(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "", Genre.ALL);

        //THEN
        assertEquals(resultMovie,allTestMovies);
    }

    @Test
    public void check_if_all_caps_returns_correct_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "CHRONO PARADOX", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }

    @Test
    public void check_if_all_lower_case_returns_correct_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "chrono paradox", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }

    @Test
    public void extra_spaces_do_not_change_search_result(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "  Chrono  Paradox    ", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }


    /* --------- Mixed Filter Tests--------*/
    @Test
    public void show_movies_matching_title_and_description_only_once(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "Chrono Paradox", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);
    }

    /* --------- Sorting Tests--------*/
    @Test
    public void sort_movies_ascending_by_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> sortedMovies = new ArrayList<>();
        sortedMovies.add(movie1);
        sortedMovies.add(movie2);
        sortedMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie1);
        expectedMovies.add(movie3);

        //WHEN
        homeController.sortMovies(sortedMovies, true);

        //THEN
        assertEquals(sortedMovies, expectedMovies);
    }
    @Test
    public void sort_movies_descending_by_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "Chrono Paradox: A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> sortedMovies = new ArrayList<>();
        sortedMovies.add(movie1);
        sortedMovies.add(movie2);
        sortedMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);
        expectedMovies.add(movie1);
        expectedMovies.add(movie2);

        //WHEN
        homeController.sortMovies(sortedMovies, false);

        //THEN
        assertEquals(sortedMovies, expectedMovies);
    }

    /* --------- Null Pointer Tests--------*/
    @Test
    public void throw_illegal_argument_exception_when_genre_is_null(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        //WHEN THEN
        assertThrows(IllegalArgumentException.class, ()->homeController.filterMovies(allTestMovies, "", null));
    }

    @Test
    public void throw_illegal_argument_exception_when_movie_list_is_null(){
        //GIVEN
        HomeController homeController = new HomeController();

        //WHEN THEN
        assertThrows(IllegalArgumentException.class, ()->homeController.filterMovies(null, "", Genre.ROMANCE));
    }

    @Test
    public void query_finds_movies_but_genre_does_not_fit(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "Chrono Paradox", Genre.BIOGRAPHY);

        //THEN
        assertTrue(resultMovie.isEmpty());
    }
}