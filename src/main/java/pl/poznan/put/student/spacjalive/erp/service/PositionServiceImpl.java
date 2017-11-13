package pl.poznan.put.student.spacjalive.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.student.spacjalive.erp.dao.PositionDAO;
import pl.poznan.put.student.spacjalive.erp.entity.Position;

import java.util.List;

@Service("positionService")
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionDAO positionDAO;

    @Override
    public List<Position> getPositions() {
        return positionDAO.getPositions();
    }

    @Override
    public void savePosition(Position position) {
        positionDAO.savePosition(position);
    }

    @Override
    public void deletePosition(int id) {
        positionDAO.deletePosition(id);
    }

    @Override
    public Position getPosition(int id) {
        return positionDAO.getPosition(id);
    }
}
