package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findAllByCut_IdAndSubject_Id(Long cutId, Long subjectId);
    List<ActivityEntity> findAllBySubject_Id(Long subjectId);

    @Query("""
        SELECT a FROM ActivityEntity a
        JOIN a.subject s
        JOIN s.enrollmentSubjects es
        JOIN es.enrollment e
        WHERE e.student.id = :studentId
        AND s.id = :subjectId
        AND a.cut.id = :cutId
        """)
    List<ActivityEntity> findAllByStudentIdAndSubjectIdAndCutId(
            @Param("studentId") Long studentId,
            @Param("subjectId") Long subjectId,
            @Param("cutId") Long cutId
    );
}