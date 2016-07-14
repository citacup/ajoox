package com.ictidn.cita.ajoox;

/**
 * Created by ajou on 7/14/2016.
 */
public class Artist {

    String artist_name, sex;

    public Artist(String artist_name, String sex) {
        this.artist_name = artist_name;
        this.sex = sex;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
