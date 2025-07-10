package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import edu.corhuila.unitrack.application.mapper.CutMapper;
import edu.corhuila.unitrack.application.port.in.ICutService;
import edu.corhuila.unitrack.application.port.out.ICutPersistencePort;
import edu.corhuila.unitrack.domain.model.Cut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CutService implements ICutService {

    private final CutMapper cutMapper;
    private final ICutPersistencePort cutPersistencePort;

    public CutService(CutMapper cutMapper, ICutPersistencePort cutPersistencePort) {
        this.cutMapper = cutMapper;
        this.cutPersistencePort = cutPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public CutResponse getById(Long id) {
        Cut cut = cutPersistencePort.findById(id);
        return cutMapper.toResponseDto(cut);
    }

    @Override
    @Transactional
    public CutResponse create(CutRequest request) {
        Cut cut = cutMapper.toEntity(request);
        Cut saved = cutPersistencePort.save(cut);
        return cutMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public CutResponse update(Long id, CutRequest request) {
        Cut cut = cutPersistencePort.findById(id);
        cutMapper.updateFromRequest(cut, request);
        Cut updated = cutPersistencePort.save(cut);
        return cutMapper.toResponseDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateFinalGradeBySubjectId(Long subjectId) {
        List<Cut> cuts = cutPersistencePort.findAllBySubjectId(subjectId);

        // Final grade es suma de (nota_final * porcentaje) de cada corte
        return cuts.stream()
                .mapToDouble(cut -> {
                    double finalGrade = cut.getFinalGrade() != null ? cut.getFinalGrade() : 0.0;
                    double percentage = cut.getPercentage() != null ? cut.getPercentage() : 0.0;
                    return finalGrade * (percentage / 100.0);
                })
                .sum();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CutResponse> getAllBySubjectId(Long subjectId) {
        List<Cut> cuts = cutPersistencePort.findAllBySubjectId(subjectId);
        return cuts.stream()
                .map(cutMapper::toResponseDto)
                .toList();
    }
}
