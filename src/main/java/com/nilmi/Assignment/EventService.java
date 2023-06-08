package com.nilmi.Assignment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private EventRepository eventRepository;

    private List<Event> events;

    public EventService() {
        this.events = new ArrayList<>();
    }

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findallEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(UUID eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public Event createEvent(Event event) {
      return eventRepository.save(event);
    }


    public Event updateEvent(UUID eventId, Event updatedEvent) {

        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                event.setTransId(updatedEvent.getTransId());
                event.setTransTms(updatedEvent.getTransTms());
                event.setRcNum(updatedEvent.getRcNum());
                event.setClientId(updatedEvent.getClientId());
                event.setEventCnt(updatedEvent.getEventCnt());
                event.setLocationCd(updatedEvent.getLocationCd());
                event.setLocationId1(updatedEvent.getLocationId1());
                event.setLocationId2(updatedEvent.getLocationId2());
                event.setAddrNbr(updatedEvent.getAddrNbr());
                return event;
            }
        }

            return null;

    }


    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }
}
