package edu.corhuila.unitrack.application.mapper;

import edu.corhuila.unitrack.application.dto.request.StudentRequest;
import edu.corhuila.unitrack.application.dto.request.StudentUpdateRequest;
import edu.corhuila.unitrack.application.dto.response.StudentResponse;
import edu.corhuila.unitrack.application.dto.response.StudentUpdateResponse;
import edu.corhuila.unitrack.domain.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequest request) {
        if (request == null) return null;

        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setStudentCode(request.studentCode());
        student.setProgram(request.program());
        student.setSemester(request.semester());

        return student;
    }

    public StudentResponse toResponseDto(Student student) {
        if (student == null) return null;

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getStudentCode(),
                student.getProgram(),
                student.getSemester(),
                student.getAverageGrade()
        );
    }

    public StudentUpdateResponse toUpdateResponseDto(Student student) {
        if (student == null) return null;

        return new StudentUpdateResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getProgram(),
                student.getSemester()
        );
    }

    public void updateFromRequest(Student student, StudentUpdateRequest request) {
        // Método auxiliar para actualizaciones
        if (student != null && request != null) {
            student.setFirstName(request.firstName());
            student.setLastName(request.lastName());
            student.setProgram(request.program());
            student.setSemester(request.semester());
        }
    }
}