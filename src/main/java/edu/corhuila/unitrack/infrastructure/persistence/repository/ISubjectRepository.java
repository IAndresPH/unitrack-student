package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
    @Query("""
    SELECT ss.subject FROM StudentSubjectEntity ss
    WHERE ss.student.id = :studentId
    """)
    List<SubjectEntity> findAllByStudentId(@Param("studentId") Long studentId);
}