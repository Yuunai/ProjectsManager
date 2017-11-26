package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.Position;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;


public class StringPositionIdToPositionConverter implements Converter<String, Position> {

    private final PositionService positionService;

    public StringPositionIdToPositionConverter(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public Position convert(String s) {
        int positionId = Integer.valueOf(s);

        Position position = positionService.getPosition(positionId);

        return position;
    }
}
