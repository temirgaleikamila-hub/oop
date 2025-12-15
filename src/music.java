// Song class
class Song {
    private String title;
    private Artist artist;
    private double duration; // in minutes

    // Constructor
    public Song(String title, Artist artist, double duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }

    // Method to "play" the song
    public void play() {
        System.out.println("Playing: " + title + " by " + artist.getName());
    }

    // Compare songs by title
    public boolean equals(Song other) {
        return this.title.equalsIgnoreCase(other.title);
    }

    public String toString() {
        return "Song: " + title + ", Artist: " + artist.getName() + ", Duration: " + duration + " min";
    }
}

// Artist class
class Artist {
    private String name;
    private String genre;

    // Constructor
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    // Method to show artist info
    public void showInfo() {
        System.out.println("Artist: " + name + " | Genre: " + genre);
    }


    public String toString() {
        return "Artist: " + name + " (" + genre + ")";
    }
}

// Playlist class using an array
class Playlist {
    private String name;
    private Song[] songs;
    private int songCount; // tracks how many songs are added

    // Constructor
    public Playlist(String name, int maxSongs) {
        this.name = name;
        this.songs = new Song[maxSongs]; // fixed-size array
        this.songCount = 0;
    }

    // Getter and setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Add song to playlist
    public void addSong(Song song) {
        if (songCount < songs.length) {
            songs[songCount] = song;
            songCount++;
        } else {
            System.out.println("Playlist is full! Cannot add more songs.");
        }
    }

    // Show playlist
    public void showPlaylist() {
        System.out.println("Playlist: " + name);
        for (int i = 0; i < songCount; i++) {
            System.out.println(" - " + songs[i]);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create artists
        Artist a1 = new Artist("Billie Eilish", "Pop");
        Artist a2 = new Artist("Eminem", "Rap");

        // Create songs
        Song s1 = new Song("Bad Guy", a1, 3.14);
        Song s2 = new Song("Happier Than Ever", a1, 4.59);
        Song s3 = new Song("Lose Yourself", a2, 5.20);
        Song s4 = new Song("Bad Guy", a1, 3.14); // same title as s1

        // Play a song
        s1.play();

        // Compare songs
        System.out.println("s1 equals s4? " + s1.equals(s4)); // true

        // Create playlist (max 5 songs)
        Playlist playlist = new Playlist("My Favorite Songs", 5);
        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist.addSong(s3);

        // Show playlist
        playlist.showPlaylist();
    }
}

