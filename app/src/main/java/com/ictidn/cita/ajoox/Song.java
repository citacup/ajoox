package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
public class Song {
    String title, genre, path;
    int id_artist, id_album;

    public Song(String title, String genre, String path, int id_artist, int id_album) {
        this.genre = genre;
        this.id_album = id_album;
        this.id_artist = id_artist;
        this.path = path;
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
