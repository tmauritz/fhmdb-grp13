package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie implements Comparable<Movie>{
    private final String title;
    private final String description;
    private final List<Genre> genres;
    private final int releaseYear;
    private final double rating;
    private final String id;
    private final List<String> mainCast;
    private final int lengthInMinutes;
    private final List<String> directors;
    private final List<String> writers;
    private final String url;

    public Movie(String id, String title, List<Genre> genres, int releaseYear, String description, String url,  int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.id = id;
        this.mainCast = mainCast;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public String getUrl() {
        return url;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
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

        Movie movie4 = new Movie(
                "004",
                "Midnight Serenade",
                Arrays.asList(Genre.MUSICAL, Genre.ROMANCE, Genre.DRAMA),
                2023,
                "In the vibrant world of 1920s New York City, a young jazz musician falls in love with a talented singer, but their romance is threatened by the pressures of fame and the shadows of the past.",
                "example.com/midnight-serenade",
                135,
                List.of("Damien Chazelle"),
                Arrays.asList("Olivia Parker", "James Smith"),
                Arrays.asList("Emma Stone", "Ryan Gosling", "John Legend"),
                8.5
        );

        Movie movie5 = new Movie(
                "005",
                "The Last Frontier",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION),
                2024,
                "In a future where humanity has colonized other planets, a group of explorers embarks on a perilous journey to the edge of the universe, encountering alien civilizations and confronting the mysteries of the cosmos.",
                "example.com/the-last-frontier",
                160,
                List.of("Ridley Scott"),
                Arrays.asList("Jonathan Nolan", "Lisa Johnson"),
                Arrays.asList("Matt Damon", "Jessica Chastain", "Michael Fassbender"),
                9.0
        );

        Movie movie6 = new Movie(
                "006",
                "City of Dreams",
                Arrays.asList(Genre.DRAMA, Genre.CRIME, Genre.THRILLER),
                2023,
                "In the seedy underworld of 1950s Los Angeles, a private detective becomes entangled in a web of corruption, betrayal, and murder as he investigates the mysterious disappearance of a wealthy socialite.",
                "example.com/city-of-dreams",
                145,
                List.of("Martin Scorsese"),
                Arrays.asList("David Fincher", "Jennifer Lawrence"),
                Arrays.asList("Leonardo DiCaprio", "Emma Stone", "Robert De Niro"),
                8.7
        );

        Movie movie7 = new Movie(
                "007",
                "Beyond the Stars",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.DRAMA),
                2024,
                "A team of astronauts embarks on a daring mission to explore a newly discovered exoplanet, only to find themselves facing unexpected challenges and dangers that threaten their very survival.",
                "example.com/beyond-the-stars",
                155,
                List.of("James Cameron"),
                Arrays.asList("Christopher Nolan", "Emma Watson"),
                Arrays.asList("Chris Pratt", "Jennifer Lawrence", "Tom Hardy"),
                8.8
        );

        Movie movie8 = new Movie(
                "008",
                "Heartstrings",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA),
                2023,
                "Two strangers from different worlds find their lives intertwined by fate, leading them on a journey of love, loss, and self-discovery that challenges everything they thought they knew about themselves and each other.",
                "example.com/heartstrings",
                125,
                List.of("Greta Gerwig"),
                Arrays.asList("Noah Baumbach", "Greta Gerwig"),
                Arrays.asList("Saoirse Ronan", "Timothée Chalamet", "Meryl Streep"),
                8.4
        );

        Movie movie9 = new Movie(
                "009",
                "Ghostly Whispers",
                Arrays.asList(Genre.HORROR, Genre.MYSTERY, Genre.THRILLER),
                2022,
                "After moving into a remote mansion with a dark past, a family is terrorized by malevolent spirits, forcing them to confront their deepest fears and unravel the chilling secrets hidden within the walls of their new home.",
                "example.com/ghostly-whispers",
                140,
                List.of("James Wan"),
                Arrays.asList("Leigh Whannell", "James Wan"),
                Arrays.asList("Vera Farmiga", "Patrick Wilson", "Joey King"),
                8.3
        );

        Movie movie10 = new Movie(
                "010",
                "Infinite Realms",
                Arrays.asList(Genre.FANTASY, Genre.ADVENTURE),
                2024,
                "In a world where magic is real and mythical creatures roam the land, a young hero sets out on an epic quest to defeat an ancient evil and restore balance to the realm.",
                "example.com/infinite-realms",
                170,
                List.of("Peter Jackson"),
                Arrays.asList("J.R.R. Tolkien", "Peter Jackson"),
                Arrays.asList("Elijah Wood", "Orlando Bloom", "Cate Blanchett"),
                9.1
        );

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);
        movies.add(movie7);
        movies.add(movie8);
        movies.add(movie9);
        movies.add(movie10);

        return movies;
    }

    @Override
    public int compareTo(Movie o){
        return this.title.compareTo(o.getTitle());
    }
}
