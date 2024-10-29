
package com.example.moviestar;
import java.util.Objects;

public class Movies {
    private Integer id_movies;
    private String name_movies;
    private String genre;
    private Integer release_year;
    private String duration;
    private String description;

    // Constructor không tham số
    public Movies() {
    }

    // Constructor đầy đủ tham số
    public Movies(Integer id_movies, String name_movies, String genre, Integer release_year, String duration, String description) {
        this.id_movies = id_movies;
        this.name_movies = name_movies;
        this.genre = genre;
        this.release_year = release_year;
        this.duration = duration;
        this.description = description;
    }

    // Getter và Setter cho các trường
    public Integer getId_movies() {
        return id_movies;
    }

    public void setId_movies(Integer id_movies) {
        this.id_movies = id_movies;
    }

    public String getName_movies() {
        return name_movies;
    }

    public void setName_movies(String name_movies) {
        this.name_movies = name_movies;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(id_movies, movies.id_movies) &&
                Objects.equals(name_movies, movies.name_movies) &&
                Objects.equals(genre, movies.genre) &&
                Objects.equals(release_year, movies.release_year) &&
                Objects.equals(duration, movies.duration) &&
                Objects.equals(description, movies.description);
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id_movies, name_movies, genre, release_year, duration, description);
    }

    // Override toString
    @Override
    public String toString() {
        return "Movies{" +
                "id_movies=" + id_movies +
                ", name_movies='" + name_movies + '\'' +
                ", genre='" + genre + '\'' +
                ", release_year=" + release_year +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
