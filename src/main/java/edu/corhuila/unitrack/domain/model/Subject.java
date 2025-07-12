package edu.corhuila.unitrack.domain.model;

import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import java.util.List;

public class Subject extends Base {
    private String name;
    private Integer credit;
    private Double finalGrade;
    private List<Cut> cuts; // útil

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public List<Cut> getCuts() {
        return cuts;
    }

    public void setCuts(List<Cut> cuts) {
        this.cuts = cuts;
    }

    public static Subject fromEntity(SubjectEntity entity) {
        if (entity == null) return null;

        Subject subject = new Subject();
        subject.setId(entity.getId());
        subject.setName(entity.getName());
        subject.setCredit(entity.getCredit());
        subject.setFinalGrade(entity.getFinalGrade());

        return subject;
    }
}