package edu.university.facultyloading.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private int id;
    private String name;
    private String description;
    private String recommendedMajor;
    private List<String> previousTeachers;
    private int complexityLevel; // Optional: range 1–5

    public Subject() {
    }

    public Subject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    Subject(int id, String name, String description, int complexityLevel, List<String> previousTeachers,
            String recommendedMajor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.complexityLevel = 1;
        this.previousTeachers = new ArrayList<>();
        this.recommendedMajor = recommendedMajor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getComplexityLevel() {
        return complexityLevel;
    }

    public List<String> getPreviousTeachers() {
        return previousTeachers;
    }

    public String getRecommendedMajor() {
        return recommendedMajor;
    }

    public void setComplexityLevel(int complexityLevel) {
        this.complexityLevel = complexityLevel;
    }

    public void setPreviousTeachers(List<String> previousTeachers) {
        this.previousTeachers = previousTeachers;
    }

    public void setRecommendedMajor(String recommendedMajor) {
        this.recommendedMajor = recommendedMajor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
