package edu.corhuila.unitrack.infrastructure.adapter;

import edu.corhuila.unitrack.application.port.out.IStudentSubjectPersistencePort;
import edu.corhuila.unitrack.domain.model.StudentSubject;
import edu.corhuila.unitrack.infrastructure.mapper.StudentSubjectEntityMapper;
import edu.corhuila.unitrack.infrastructure.persistence.entity.StudentSubjectEntity;
import edu.corhuila.unitrack.infrastructure.persistence.repository.IStudentSubjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentSubjectPersistenceAdapter implements IStudentSubjectPersistencePort {

    private final IStudentSubjectRepository studentSubjectRepository;

    public StudentSubjectPersistenceAdapter(IStudentSubjectRepository studentSubjectRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
    }

    @Override
    public List<StudentSubject> findAllStudentSubjectsByStudentId(Long studentId) {
        return studentSubjectRepository.findAllByStudentId(studentId)
                .stream()
                .map(StudentSubjectEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Double calculateFinalGradeByStudentAndSubject(Long studentId, Long subjectId) {
        return studentSubjectRepository.findByStudentIdAndSubjectId(studentId, subjectId)
                .map(StudentSubjectEntity::getFinalGrade)
                .orElse(0.0);
    }

    @Override
    public void saveFinalGrade(Long studentId, Long subjectId, Double finalGrade) {
        var entity = studentSubjectRepository.findByStudentIdAndSubjectId(studentId, subjectId)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));

        entity.setFinalGrade(finalGrade);
        studentSubjectRepository.save(entity);
    }
}
