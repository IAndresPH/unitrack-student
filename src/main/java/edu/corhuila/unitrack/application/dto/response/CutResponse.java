package edu.corhuila.unitrack.application.dto.response;

import java.util.List;

public record CutResponse(
    Long id,
    String name,
    Double percentage,
    Double finalGrade,
    List<Long> subjectIds
) {}