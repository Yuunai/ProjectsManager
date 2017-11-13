package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Position;

import java.util.List;

public interface PositionService {

    public List<Position> getPositions();

    public void savePosition(Position position);

    public void deletePosition(int id);

    public Position getPosition(int id);

}
