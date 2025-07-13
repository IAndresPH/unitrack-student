package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.EnrollmentRequest;
import edu.corhuila.unitrack.application.dto.response.EnrollmentGetResponse;
import edu.corhuila.unitrack.application.dto.response.EnrollmentPostResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectMiniResponse;
import edu.corhuila.unitrack.domain.model.Enrollment;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequest request) {
        if (request == null) return null;

        Enrollment enrollment = new Enrollment();
        enrollment.setSemester(request.semester());
        enrollment.setEnrollmentDate(LocalDate.now());

        // Asociar estudiante por ID
        var student = new edu.corhuila.unitrack.domain.model.Student();
        student.setId(request.studentId());
        enrollment.setStudent(student);

        // Asociar materias solo por ID
        List<Subject> subjects = request.subjectIds().stream()
                .map(id -> {
                    Subject s = new Subject();
                    s.setId(id);
                    return s;
                })
                .collect(Collectors.toList());

        enrollment.setSubjects(subjects);

        return enrollment;
    }

    // Para respuesta GET (enriquecida con detalles de materias)
    public EnrollmentGetResponse toGetResponseDto(Enrollment enrollment) {
        if (enrollment == null) return null;

        List<SubjectMiniResponse> subjects = enrollment.getSubjects() != null
                ? enrollment.getSubjects().stream()
                .map(subject -> new SubjectMiniResponse(
                        subject.getId(),
                        subject.getName(),
                        subject.getCredit()))
                .toList()
                : List.of();

        return new EnrollmentGetResponse(
                enrollment.getId(),
                enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
                subjects,
                enrollment.getSemester()
        );
    }

    // Para respuesta POST (solo IDs de materias)
    public EnrollmentPostResponse toPostResponseDto(Enrollment enrollment) {
        if (enrollment == null) return null;

        List<Long> subjectIds = enrollment.getSubjects() != null
                ? enrollment.getSubjects().stream()
                .map(Subject::getId)
                .toList()
                : List.of();

        return new EnrollmentPostResponse(
                enrollment.getId(),
                enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
                subjectIds,
                enrollment.getSemester()
        );
    }
}