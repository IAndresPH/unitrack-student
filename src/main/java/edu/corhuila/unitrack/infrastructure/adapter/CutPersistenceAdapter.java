package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.ICutPersistencePort;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.infrastructure.mapper.CutEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.ICutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CutPersistenceAdapter implements ICutPersistencePort {

    private final ICutRepository cutRepository;

    public CutPersistenceAdapter(ICutRepository cutRepository) {
        this.cutRepository = cutRepository;
    }

    @Override
    @Transactional
    public Cut save(Cut cut) {
        // Mapear a entidad y guardar
        CutEntity entity = CutEntityMapper.toEntity(cut);

        // Si es actualización, preservar relaciones existentes
        if (cut.getId() != null) {
            CutEntity existing = cutRepository.findById(cut.getId()).orElse(null);
            if (existing != null) {
                entity.setActivities(existing.getActivities());
            }
        }

        CutEntity saved = cutRepository.save(entity);
        return CutEntityMapper.toDomain(saved);
    }

    @Override
    public int countCutsBySubjectId(Long subjectId) {
        return cutRepository.countBySubjectId(subjectId);
    }

    @Override
    public List<Cut> findAllByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        List<CutEntity> entities = cutRepository.findAllByStudentIdAndSubjectId(studentId, subjectId);
        return entities.stream()
                .map(CutEntityMapper::toDomain)
                .toList();
    }
}