package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;

public class Activity extends Base{
    private String name;
    private Double percentage;
    private Double grade = 0.0;
    private Cut cut;
    private Subject subject;

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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Cut getCut() {
        return cut;
    }

    public void setCut(Cut cut) {
        this.cut = cut;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public static Activity fromEntity(ActivityEntity entity) {
        if (entity == null) return null;

        Activity activity = new Activity();
        activity.setId(entity.getId());
        activity.setName(entity.getName());
        activity.setPercentage(entity.getPercentage());
        activity.setGrade(entity.getGrade());

        if (entity.getCut() != null) {
            Cut cut = new Cut();
            cut.setId(entity.getCut().getId());
            activity.setCut(cut);
        }

        if (entity.getSubject() != null) {
            Subject subject = new Subject();
            subject.setId(entity.getSubject().getId());
            activity.setSubject(subject);
        }

        return activity;
    }
}