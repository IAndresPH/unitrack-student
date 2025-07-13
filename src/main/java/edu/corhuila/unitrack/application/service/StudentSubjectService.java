package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.response.SubjectResponse;
import edu.corhuila.unitrack.application.mapper.StudentSubjectMapper;
import edu.corhuila.unitrack.application.port.in.IStudentSubjectService;
import edu.corhuila.unitrack.application.port.out.IStudentSubjectPersistencePort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentSubjectService implements IStudentSubjectService {

    private final IStudentSubjectPersistencePort studentSubjectPersistencePort;
    private final StudentSubjectMapper studentSubjectMapper;

    public StudentSubjectService(IStudentSubjectPersistencePort studentSubjectPersistencePort, StudentSubjectMapper studentSubjectMapper) {
        this.studentSubjectPersistencePort = studentSubjectPersistencePort;
        this.studentSubjectMapper = studentSubjectMapper;
    }

    @Override
    public List<SubjectResponse> getAllSubjectsWithFinalGradeByStudentId(Long studentId) {
        return studentSubjectPersistencePort
                .findAllStudentSubjectsByStudentId(studentId)
                .stream()
                .map(studentSubjectMapper::toSubjectResponse)
                .toList();
    }

    @Override
    public Double calculateFinalGradeByStudentAndSubject(Long studentId, Long subjectId) {
        return studentSubjectPersistencePort
                .calculateFinalGradeByStudentAndSubject(studentId, subjectId);
    }
}