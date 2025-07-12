package edu.corhuila.unitrack.application.port.in;

import edu.corhuila.unitrack.application.dto.request.CutRequest;
import edu.corhuila.unitrack.application.dto.response.CutResponse;

public interface ICutService {
    CutResponse create(CutRequest request);
}