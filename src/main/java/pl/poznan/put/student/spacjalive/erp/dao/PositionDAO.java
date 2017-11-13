package pl.poznan.put.student.spacjalive.erp.dao;

import pl.poznan.put.student.spacjalive.erp.entity.Position;

import java.util.List;

public interface PositionDAO {

    public List<Position> getPositions();

    public void savePosition(Position position);

    public void deletePosition(int id);

    public Position getPosition(int id);

}
