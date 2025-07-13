package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;

public class Activity extends Base{
    private String name;
    private Double percentage;
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