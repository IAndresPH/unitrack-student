package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import edu.corhuila.unitrack.application.mapper.CutMapper;
import edu.corhuila.unitrack.application.port.in.ICutService;
import edu.corhuila.unitrack.application.port.out.ICutPersistencePort;
import edu.corhuila.unitrack.application.shared.exception.TooManyCutsException;
import edu.corhuila.unitrack.domain.model.Cut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.CUT_LIMIT_EXCEEDED;

@Service
public class CutService implements ICutService {

    private final CutMapper cutMapper;
    private final ICutPersistencePort cutPersistencePort;

    public CutService(CutMapper cutMapper, ICutPersistencePort cutPersistencePort) {
        this.cutMapper = cutMapper;
        this.cutPersistencePort = cutPersistencePort;
    }

    @Override
    @Transactional
    public CutResponse create(CutRequest request) {

        for (Long subjectId : request.subjectIds()) {
            int totalCortes = cutPersistencePort.countCutsBySubjectId(subjectId);
            if (totalCortes >= 3) {
                throw new TooManyCutsException(CUT_LIMIT_EXCEEDED);
            }
        }

        Cut cut = cutMapper.toEntity(request);
        Cut saved = cutPersistencePort.save(cut);
        return cutMapper.toResponseDto(saved);
    }
}
