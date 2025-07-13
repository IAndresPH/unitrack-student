package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICutRepository extends JpaRepository<CutEntity, Long> {
    @Query("SELECT COUNT(c) FROM CutEntity c JOIN c.subjects s WHERE s.id = :subjectId")
    int countBySubjectId(@Param("subjectId") Long subjectId);

    @Query("""
        SELECT DISTINCT c FROM CutEntity c
        JOIN c.subjects s
        JOIN s.enrollmentSubjects es
        JOIN es.enrollment e
        WHERE s.id = :subjectId AND e.student.id = :studentId
        """)
    List<CutEntity> findAllByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

}