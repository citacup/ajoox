package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
public class Album {

    String album_name, release_year, label;
    int id_artist;

    public Album(String album_name, String release_year, String label, int id_artist) {
        this.album_name = album_name;
        this.label = label;
        this.release_year = release_year;
        this.id_artist = id_artist;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }
}
