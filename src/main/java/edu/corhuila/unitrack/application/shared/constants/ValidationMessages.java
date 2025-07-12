package edu.corhuila.unitrack.application.shared.constants;

public class ValidationMessages {
    public static final String FIRST_NAME_REQUIRED = "El nombre es obligatorio.";
    public static final String LAST_NAME_REQUIRED = "El apellido es obligatorio.";
    public static final String EMAIL_REQUIRED = "El correo es obligatorio.";
    public static final String EMAIL_INVALID = "El formato del correo es inválido.";
    public static final String STUDENT_CODE_REQUIRED = "El código del estudiante es obligatorio.";
    public static final String PROGRAM_REQUIRED = "El programa académico es obligatorio.";
    public static final String SEMESTER_REQUIRED = "El semestre es obligatorio.";
    public static final String SEMESTER_RANGE = "El semestre debe estar entre 1 y 10.";
    public static final String STUDENT_ID_INVALID = "El ID del estudiante no existe.";
    public static final String STUDENT_EMAIL_INVALID = "Correo ya registrado.";
    public static final String STUDENT_CODE_INVALID = "Código de estudiante ya registrado.";

    // Subject
    public static final String SUBJECT_NAME_REQUIRED = "El nombre de la materia es obligatorio.";
    public static final String CREDIT_REQUIRED = "El número de créditos es obligatorio.";
    public static final String CREDIT_RANGE = "El número de créditos debe estar entre 1 y 13.";
    public static final String STUDENT_ID_REQUIRED = "El ID del estudiante es obligatorio.";
    public static final String SUBJECT_ID_INVALID = "El ID de la materia no existe.";
    public static final String SUBJECT_ID_REQUIRED = "El ID de la materia es obligatorio.";

    // Longitudes máximas
    public static final String NAME_SIZE = "El nombre debe tener entre 1 y 30 caracteres.";
    public static final String LAST_NAME_SIZE = "El apellido debe tener entre 1 y 30 caracteres.";
    public static final String EMAIL_SIZE = "El correo debe tener como máximo 50 caracteres.";
    public static final String STUDENT_CODE_SIZE = "El código debe tener como máximo 20 caracteres.";
    public static final String PROGRAM_SIZE = "El programa debe tener como máximo 30 caracteres.";
    public static final String SUBJECT_NAME_SIZE = "El nombre de la materia debe tener entre 1 y 30 caracteres.";

    // Cut
    public static final String CUT_NAME_REQUIRED = "El nombre del corte es obligatorio.";
    public static final String CUT_PERCENTAGE_REQUIRED = "El porcentaje del corte es obligatorio.";
    public static final String CUT_PERCENTAGE_RANGE = "El porcentaje debe estar entre 0.1 y 100.";
    public static final String SUBJECT_LIST_REQUIRED = "Debe incluir al menos una materia.";
    public static final String CUT_ID_INVALID = "El ID del corte no existe.";
    public static final String CUT_LIMIT_EXCEEDED = "La materia ya tiene 3 cortes asociados";

    // Activity
    public static final String ACTIVITY_NAME_REQUIRED = "El nombre de la actividad es obligatorio.";
    public static final String ACTIVITY_NAME_SIZE = "El nombre de la actividad debe tener entre 1 y 30 caracteres.";
    public static final String ACTIVITY_PERCENTAGE_REQUIRED = "El porcentaje de la actividad es obligatorio.";
    public static final String ACTIVITY_PERCENTAGE_RANGE = "El porcentaje debe estar entre 0.1 y 100.";
    public static final String CUT_ID_REQUIRED = "El ID del corte es obligatorio.";
    public static final String ACTIVITY_ID_INVALID = "El ID de la actividad no existe.";
    public static final String ACTIVITY_PERCENTAGE_EXCEEDED = "La suma de los porcentajes no puede superar el 100%";

    // Enrollment
    public static final String ENROLLMENT_ID_INVALID = "La matrícula con el ID proporcionado no existe.";
}