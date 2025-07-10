package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.ICutPersistencePort;
import edu.corhuila.unitrack.application.shared.exception.NotFoundException;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.infrastructure.mapper.CutEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.CutEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.ICutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.CUT_ID_INVALID;

@Service
public class CutPersistenceAdapter implements ICutPersistencePort {

    private final ICutRepository cutRepository;

    public CutPersistenceAdapter(ICutRepository cutRepository) {
        this.cutRepository = cutRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cut findById(Long id) {
        CutEntity entity = cutRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CUT_ID_INVALID));
        return CutEntityMapper.toDomain(entity);
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
    @Transactional(readOnly = true)
    public List<Cut> findAllBySubjectId(Long subjectId) {
        List<CutEntity> entities = cutRepository.findAllBySubjects_Id(subjectId);
        return entities.stream()
                .map(CutEntityMapper::toDomain)
                .toList();
    }
}