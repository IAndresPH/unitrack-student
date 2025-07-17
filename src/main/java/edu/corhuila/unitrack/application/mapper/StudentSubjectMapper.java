package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.StudentSubjectRequest;
import edu.corhuila.unitrack.application.dto.response.StudentSubjectResponse;
import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.domain.model.Student;
import edu.corhuila.unitrack.domain.model.StudentSubject;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class StudentSubjectMapper {
    private final SubjectMapper subjectMapper;

    public StudentSubjectMapper(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    public StudentSubject toEntity(StudentSubjectRequest request) {
        StudentSubject ss = new StudentSubject();

        Student student = new Student();
        student.setId(request.studentId());

        Subject subject = new Subject();
        subject.setId(request.subjectId());

        ss.setStudent(student);
        ss.setSubject(subject);
        return ss;
    }

    public StudentSubjectResponse toResponse(StudentSubject ss) {
        return new StudentSubjectResponse(
                ss.getStudent().getId(),
                ss.getSubject().getId(),
                ss.getFinalGrade()
        );
    }

    public SubjectResponse toSubjectResponse(StudentSubject ss) {
        Subject subject = ss.getSubject();

        return new SubjectResponse(
                subject.getId(),
                subject.getName(),
                subject.getCredit()
        );
    }
}