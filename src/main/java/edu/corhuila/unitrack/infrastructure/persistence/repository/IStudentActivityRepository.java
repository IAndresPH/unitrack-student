package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IStudentActivityRepository extends JpaRepository<StudentActivityEntity, Long> {
    Optional<StudentActivityEntity> findByStudentIdAndActivityId(Long studentId, Long activityId);
}