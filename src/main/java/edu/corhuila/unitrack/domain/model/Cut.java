package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;

import java.util.ArrayList;
import java.util.List;

public class Cut extends Base{
    private String name;
    private Double percentage;
    private Double finalGrade = 0.0;
    private List<Subject> subject = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public static Cut fromEntity(CutEntity entity) {
        if (entity == null) return null;

        Cut cut = new Cut();
        cut.setId(entity.getId());
        cut.setName(entity.getName());
        cut.setPercentage(entity.getPercentage());
        cut.setFinalGrade(entity.getFinalGrade());

        // Mapear solo los IDs de Subject para evitar recursión o carga pesada
        if (entity.getSubjects() != null) {
            List<Subject> subjects = entity.getSubjects().stream()
                    .map(subjectEntity -> {
                        Subject subject = new Subject();
                        subject.setId(subjectEntity.getId());
                        return subject;
                    })
                    .toList();
            cut.setSubject(subjects);
        }

        // Mapear solo los IDs de Activity para evitar cargar campos innecesarios
        if (entity.getActivities() != null) {
            List<Activity> activities = entity.getActivities().stream()
                    .map(activityEntity -> {
                        Activity activity = new Activity();
                        activity.setId(activityEntity.getId());
                        return activity;
                    })
                    .toList();
            cut.setActivities(activities);
        }

        return cut;
    }
}