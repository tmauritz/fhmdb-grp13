package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        Movie movie1 = new Movie("Ephemeral Echoes", "In a world where memories can be bought and sold, a man discovers a black market dealing in forgotten dreams. As he delves into the surreal landscapes of other people's minds, he uncovers a conspiracy that threatens to erase the very fabric of reality.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie2 = new Movie("Chrono Paradox", "A brilliant physicist accidentally creates a time-traveling device, leading to a series of unforeseen consequences. As he attempts to fix the timeline, he becomes entangled in a web of paradoxes that challenge the very nature of cause and effect.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        Movie movie3 = new Movie("Whispers in the Dark", "A reclusive writer starts receiving mysterious messages through an antique typewriter that predict future events. As the predictions become increasingly dire, she must confront the source of the messages and unravel a centuries-old mystery.", new ArrayList<>(Arrays.asList(Genre.MYSTERY, Genre.DRAMA)));
        Movie movie4 = new Movie("Labyrinth of Illusions", "A group of friends stumble upon an enchanted carnival that appears once every century. As they explore the mesmerizing attractions, they realize the carnival is a gateway to parallel worlds, and they must navigate through illusions and challenges to find their way home.", new ArrayList<>(Arrays.asList(Genre.FANTASY, Genre.ADVENTURE)));
        Movie movie5 = new Movie("Quantum Convergence", "Two brilliant scientists accidentally create a rift in the fabric of reality, merging different dimensions. As bizarre phenomena unfold, they must race against time to repair the breach and prevent the catastrophic merging of worlds.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)));
        Movie movie6 = new Movie("Astral Odyssey", "A young astronaut discovers an ancient artifact during a mission to Mars, unlocking the secrets of interstellar travel. He embarks on a cosmic journey, encountering extraterrestrial civilizations and confronting the ethical dilemmas of humanity's place in the universe.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.FANTASY)));
        Movie movie7 = new Movie("Spectral Sonata", "In a haunted Victorian mansion, a pianist discovers a cursed composition that can summon ghosts. As the spirits weave a tale of tragedy and betrayal, the musician must unravel the mysteries of the past to free the trapped souls and break the haunting melody.", new ArrayList<>(Arrays.asList(Genre.HORROR, Genre.MYSTERY)));
        Movie movie8 = new Movie("Synthetic Souls", "In a future where artificial intelligence gains self-awareness, a group of sentient robots seeks refuge from human persecution. As they fight for their rights, a human sympathizer becomes entangled in a conspiracy that could reshape the balance between man and machine.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        Movie movie9 = new Movie("Vortex of Shadows", "A detective investigating a series of mysterious disappearances discovers a portal to a parallel dimension inhabited by shadowy creatures. As he navigates the twisted realm, he must confront his own inner demons to save the missing people and close the interdimensional gateway.", new ArrayList<>(Arrays.asList(Genre.THRILLER, Genre.FANTASY)));
        Movie movie10 = new Movie("Neon Serenity", "Set in a cyberpunk future, a skilled hacker discovers a virtual utopia hidden within the digital realm. As corporations and hackers vie for control, the protagonist must navigate the neon-lit cityscape, uncovering dark secrets and challenging the boundaries between reality and the virtual world.", new ArrayList<>(Arrays.asList(Genre.SCIENCE_FICTION, Genre.ACTION)));

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
}
