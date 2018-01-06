package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Event;

import java.util.List;

public interface EventService {

    public List<Event> getEvents();

    public List<Event> getEvents(int archived);

    public void saveEvent(Event event);

    public void deleteEvent(int id);

    public Event getEvent(int id);


}
