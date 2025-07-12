package edu.corhuila.unitrack.infrastructure.persistence.repository;

import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICutRepository extends JpaRepository<CutEntity, Long> {
    @Query("SELECT COUNT(c) FROM CutEntity c JOIN c.subjects s WHERE s.id = :subjectId")
    int countBySubjectId(@Param("subjectId") Long subjectId);
}