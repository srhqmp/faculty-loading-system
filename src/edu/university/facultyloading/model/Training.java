package edu.university.facultyloading.model;

public class Training {

    private int id;
    private String title;
    private String description;

    public Training() {
    }

    public Training(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Training{" + "id=" + id + ", title=" + title + ", description=" + description + '}';
    }

}
