package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CutMapper {

    public Cut toEntity(CutRequest request) {
        Cut cut = new Cut();
        cut.setName(request.name());
        cut.setPercentage(request.percentage());

        // Asociamos los IDs de subject en forma de objetos "vacíos" con solo ID
        List<Subject> subjects = request.subjectIds().stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .toList();

        cut.setSubject(subjects);
        return cut;
    }

    // Cut → CutResponse
    public CutResponse toResponseDto(Cut cut) {
        List<Long> subjectIds = cut.getSubject().stream()
                .map(Subject::getId)
                .toList();

        return new CutResponse(
                cut.getId(),
                cut.getName(),
                cut.getPercentage(),
                cut.getFinalGrade(),
                subjectIds
        );
    }

    // Para update si usas CutRequest también
    public void updateFromRequest(Cut cut, CutRequest request) {
        cut.setName(request.name());
        cut.setPercentage(request.percentage());

        List<Subject> subjects = request.subjectIds().stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .toList();

        cut.setSubject(subjects);
    }
}