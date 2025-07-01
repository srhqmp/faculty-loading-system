package edu.university.facultyloading.model;

public class Subject {

    private int subjectId;
    private String name;
    private String description;
    private String recommendedMajor;
    private int complexityLevel;

    public Subject() {

    };

    public Subject(int subjectId, String name, String description, String recommendedMajor, int complexityLevel) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.recommendedMajor = recommendedMajor;
        this.complexityLevel = complexityLevel;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
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

    public String getRecommendedMajor() {
        return recommendedMajor;
    }

    public void setRecommendedMajor(String recommendedMajor) {
        this.recommendedMajor = recommendedMajor;
    }

    public int getComplexityLevel() {
        return complexityLevel;
    }

    public void setComplexityLevel(int complexityLevel) {
        this.complexityLevel = complexityLevel;
    }

    @Override
    public String toString() {
        return "Subject [subjectId=" + subjectId + ", name=" + name + ", description=" + description
                + ", recommendedMajor=" + recommendedMajor + ", complexityLevel=" + complexityLevel + "]";
    }

}
