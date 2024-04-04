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
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );
        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );
        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie1);
        expectedMovies.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"",Genre.DRAMA);

        //THEN
        assertEquals(expectedMovies,filteredMovies);
    }

    @Test
    public void show_all_movies_if_all_genre_is_selected(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

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
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie1);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"Eternal Echoes",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }
    @Test
    public void show_only_movies_with_search_query_partly_matching_titles(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"choes of",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }

    @Test
    public void show_only_movies_with_search_query_matching_descriptions(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovie = new ArrayList<>();
        expectedMovie.add(movie3);

        //WHEN
        HomeController testController = new HomeController();
        List<Movie> filteredMovies = testController.filterMovies(allTestMovies,"must navigate the brutal",Genre.ALL);

        //THEN
        assertEquals(expectedMovie,filteredMovies);
    }

    @Test
    public void show_all_movies_when_empty_query_is_sent(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );
        HomeController homeController = new HomeController();

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
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );
        HomeController homeController = new HomeController();

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "SHADOWED PATHS", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }

    @Test
    public void check_if_all_lower_case_returns_correct_title(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );
        HomeController homeController = new HomeController();

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "shadowed paths", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }

    @Test
    public void extra_spaces_do_not_change_search_result(){
        //GIVEN
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );
        HomeController homeController = new HomeController();

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, " Shadowed    Paths    ", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);

    }


    /* --------- Mixed Filter Tests--------*/
    @Test
    public void show_movies_matching_title_and_description_only_once(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "War", Genre.ALL);

        //THEN
        assertEquals(resultMovie, expectedMovies);
    }

    /* --------- Sorting Tests--------*/
    @Test
    public void sort_movies_ascending_by_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> sortedMovies = new ArrayList<>();
        sortedMovies.add(movie1);
        sortedMovies.add(movie2);
        sortedMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);
        expectedMovies.add(movie1);
        expectedMovies.add(movie2);

        //WHEN
        homeController.sortMovies(sortedMovies, true);

        //THEN
        assertEquals(sortedMovies, expectedMovies);
    }
    @Test
    public void sort_movies_descending_by_title(){
        //GIVEN
        HomeController homeController = new HomeController();
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> sortedMovies = new ArrayList<>();
        sortedMovies.add(movie1);
        sortedMovies.add(movie2);
        sortedMovies.add(movie3);

        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie1);
        expectedMovies.add(movie3);

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
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

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
        Movie movie1 = new Movie(
                "001",
                "Eternal Echoes",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.MYSTERY),
                2023,
                "In a small coastal town, a young woman discovers a series of love letters hidden in an antique book, sparking a journey to unravel a decades-old mystery and find the true meaning of love.",
                "example.com/eternal-echoes",
                130,
                List.of("Ava Smith"),
                Arrays.asList("Ryan Johnson", "Emily Greene"),
                Arrays.asList("Lily Collins", "Ryan Gosling", "Helen Mirren"),
                8.2
        );

        Movie movie2 = new Movie(
                "002",
                "Shadowed Paths",
                Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.CRIME),
                2024,
                "A seasoned detective is drawn into a web of intrigue and danger when he investigates a series of seemingly unrelated crimes, only to discover they are all connected by a dark secret buried in the past.",
                "example.com/shadowed-paths",
                120,
                List.of("David Fincher"),
                Arrays.asList("Sarah Johnson", "Michael Thompson"),
                Arrays.asList("Jake Gyllenhaal", "Charlize Theron", "Anthony Hopkins"),
                8.6
        );

        Movie movie3 = new Movie(
                "003",
                "Echoes of War",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
                2022,
                "Set during World War II, a group of soldiers must navigate the brutal realities of combat while grappling with their own inner demons and the moral complexities of war.",
                "example.com/echoes-of-war",
                150,
                List.of("Christopher Nolan"),
                Arrays.asList("Matthew Carter", "Emma Thompson"),
                Arrays.asList("Tom Hanks", "Benedict Cumberbatch", "Alicia Vikander"),
                8.9
        );

        List<Movie> allTestMovies = new ArrayList<>();
        allTestMovies.add(movie1);
        allTestMovies.add(movie2);
        allTestMovies.add(movie3);

        //WHEN
        List<Movie> resultMovie = homeController.filterMovies(allTestMovies, "Shadowed Paths", Genre.BIOGRAPHY);

        //THEN
        assertTrue(resultMovie.isEmpty());
    }
}