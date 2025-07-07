package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.SubjectRequest;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectUpdateResponse;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.Subject;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ISubjectMapper {
    Subject toEntity(SubjectRequest request);
    SubjectUpdateResponse toUpdateResponseDto(Subject subject);

    // Este lo haces tú manualmente
    default SubjectResponse toResponseDto(Subject subject) {
        if (subject == null) return null;

        return new SubjectResponse(
                subject.getId(),
                subject.getName(),
                subject.getCredit(),
                subject.getFinalGrade(),
                subject.getStudent() != null ? subject.getStudent().getId() : null
        );
    }

    // También tú lo haces manualmente usando el de arriba
    default List<SubjectResponse> toResponseDtoList(List<Subject> subjects) {
        return subjects.stream()
                .map(this::toResponseDto)
                .toList();
    }
}