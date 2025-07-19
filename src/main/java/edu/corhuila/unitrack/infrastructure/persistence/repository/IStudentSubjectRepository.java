package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentSubjectRepository extends JpaRepository<StudentSubjectEntity, Long> {
    List<StudentSubjectEntity> findAllByStudentId(Long studentId);
    Optional<StudentSubjectEntity> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}