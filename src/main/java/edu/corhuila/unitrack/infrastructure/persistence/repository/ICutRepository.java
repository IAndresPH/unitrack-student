package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICutRepository extends JpaRepository<CutEntity, Long> {
    List<CutEntity> findAllBySubjects_Id(Long subjectId);
}