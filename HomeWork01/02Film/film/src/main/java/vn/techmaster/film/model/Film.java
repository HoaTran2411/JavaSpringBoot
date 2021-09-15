package vn.techmaster.film.model;

public class Film {
    
    private String title;
    private String director;
    private int publishingYear;

    public Film(String title, String director, int publishingYear) {
        this.title = title;
        this.director = director;
        this.publishingYear = publishingYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

}
