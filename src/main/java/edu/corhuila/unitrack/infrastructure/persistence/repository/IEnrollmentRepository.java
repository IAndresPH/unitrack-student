package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    List<EnrollmentEntity> findAllByStudent_Id(Long studentId);
}