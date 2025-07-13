package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.request.SubjectUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectUpdateResponse;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectMapper {
    public Subject toEntity(SubjectRequest request) {
        if (request == null) return null;

        Subject subject = new Subject();
        subject.setName(request.name());
        subject.setCredit(request.credit());
        return subject;
    }

    public SubjectUpdateResponse toUpdateResponseDto(Subject subject) {
        if (subject == null) return null;

        return new SubjectUpdateResponse(
                subject.getId(),
                subject.getName(),
                subject.getCredit()
        );
    }

    public SubjectResponse toResponseDto(Subject subject) {
        if (subject == null) return null;

        return new SubjectResponse(
                subject.getId(),
                subject.getName(),
                subject.getCredit(),
                0.0
        );
    }

    public List<SubjectResponse> toResponseDtoList(List<Subject> subjects) {
        if (subjects == null) return List.of();

        return subjects.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public void updateFromRequest(Subject subject, SubjectUpdateRequest request) {
        if (subject != null && request != null) {
            subject.setName(request.name());
            subject.setCredit(request.credit());
        }
    }
}