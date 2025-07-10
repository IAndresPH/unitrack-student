package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findAllByCut_IdAndSubject_Id(Long cutId, Long subjectId);
    List<ActivityEntity> findAllBySubject_Id(Long subjectId);
}