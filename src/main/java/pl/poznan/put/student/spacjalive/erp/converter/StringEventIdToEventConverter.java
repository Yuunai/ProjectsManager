package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.service.EventService;

public class StringEventIdToEventConverter implements Converter<String, Event> {

    private final EventService eventService;

    public StringEventIdToEventConverter(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Event convert(String s) {
        Event event;
        event = eventService.getEvent(Integer.valueOf(s));

        return event;
    }
}
