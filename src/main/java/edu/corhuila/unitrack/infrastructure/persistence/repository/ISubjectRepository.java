package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
    List<SubjectEntity> findAllByStudentId(Long studentId);
}