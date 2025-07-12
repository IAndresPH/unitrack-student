package edu.corhuila.unitrack.application.service;

import edu.corhuila.unitrack.application.dto.request.ActivityRequest;
import edu.corhuila.unitrack.application.dto.response.ActivityResponse;
import edu.corhuila.unitrack.application.mapper.ActivityMapper;
import edu.corhuila.unitrack.application.port.in.IActivityService;
import edu.corhuila.unitrack.application.port.out.IActivityPersistencePort;
import edu.corhuila.unitrack.application.shared.exception.PercentageExceededException;
import edu.corhuila.unitrack.domain.model.Activity;
import edu.corhuila.unitrack.domain.model.Cut;
import edu.corhuila.unitrack.domain.model.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.ACTIVITY_PERCENTAGE_EXCEEDED;

@Service
public class ActivityService implements IActivityService {

    private final ActivityMapper activityMapper;
    private final IActivityPersistencePort activityPersistencePort;

    public ActivityService(ActivityMapper activityMapper, IActivityPersistencePort activityPersistencePort) {
        this.activityMapper = activityMapper;
        this.activityPersistencePort = activityPersistencePort;
    }

    @Override
    @Transactional
    public ActivityResponse create(ActivityRequest request) {
        Double nuevoPorcentaje = request.percentage();

        // Paso 1: Obtener todas las actividades existentes para el mismo corte y materia
        List<Activity> actividadesExistentes = activityPersistencePort.findAllByCutIdAndSubjectId(
                request.cutId(), request.subjectId()
        );

        // Paso 2: Calcular el porcentaje total actual
        double porcentajeActual = actividadesExistentes.stream()
                .mapToDouble(a -> a.getPercentage() != null ? a.getPercentage() : 0.0)
                .sum();

        // Paso 3: Validar que la suma total no exceda 100%
        if (porcentajeActual + nuevoPorcentaje > 100.0) {
            throw new PercentageExceededException(ACTIVITY_PERCENTAGE_EXCEEDED);
        }

        Activity activity = activityMapper.toEntity(request);

        Cut cut = new Cut();
        cut.setId(request.cutId());
        activity.setCut(cut);

        Subject subject = new Subject();
        subject.setId(request.subjectId());
        activity.setSubject(subject);

        Activity saved = activityPersistencePort.save(activity);
        return activityMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activityPersistencePort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateFinalGradeBySubjectId(Long subjectId) {
        List<Activity> activities = activityPersistencePort.findAllBySubjectId(subjectId);

        return activities.stream()
                .mapToDouble(a -> {
                    double grade = a.getGrade() != null ? a.getGrade() : 0.0;
                    double percentage = a.getPercentage() != null ? a.getPercentage() : 0.0;
                    return grade * (percentage / 100.0);
                })
                .sum();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityResponse> getAllByStudentSubjectAndCut(Long studentId, Long subjectId, Long cutId) {
        return activityPersistencePort
                .findAllByStudentIdAndSubjectIdAndCutId(studentId, subjectId, cutId)
                .stream()
                .map(activityMapper::toResponseDto)
                .toList();
    }
}