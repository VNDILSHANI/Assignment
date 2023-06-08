package com.nilmi.Assignment;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventRout extends RouteBuilder {

    private EventService eventService;

    private final EventRepository eventRepository;

    @Autowired
    public EventRout(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void configure() throws Exception {

        restConfiguration()

                .component("servlet")
                .port(9090).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest("/event")

                .get("/").outType(Event.class)
                .to("direct:getAllEvents")
                .get("/{id}")
                .to("direct:getEvent")
                .post("/").type(Event.class)
                .to("direct:createEvent")
                .put("/{eventId}")
                .type(Event.class)
                .to("direct:updateEvent")
                .delete("/{id}")
                .to("direct:deleteEvent");


        from("direct:getAllEvents")
                .bean(EventService.class, "findallEvents");

        from("direct:getEvent")
                .bean(EventService.class, "getEvent(${header.id})");

        from("direct:createEvent")
                .bean(EventService.class,"createEvent(${body})");
        from("direct:updateEvent")

                .bean(EventService.class, "updateEvent");

        from("direct:deleteEvent")
                .bean(EventService.class, "deleteEvent(${header.id})");




        //Assignment Part 2

        from("direct:saveEvents")
                .split().body().streaming()
                .unmarshal().json()
                .split().jsonpath("$.records")
                .process(exchange -> {
                    Records record = exchange.getIn().getBody(Records.class);
                    for (EventData eventData : record.getEvent()) {
                        Event event = new Event();
                        event.setTransId(record.getTransId());
                        event.setTransTms(record.getTransTms());
                        event.setRcNum(record.getRcNum());
                        event.setClientId(record.getClientId());
                        event.setEventCnt(eventData.getEventCnt());
                        event.setLocationCd(eventData.getLocationCd());
                        event.setLocationId1(eventData.getLocationId1());
                        event.setLocationId2(eventData.getLocationId2());
                        event.setAddrNbr(eventData.getAddrNbr());

                        eventRepository.save(event);
                    }
                })
                .end();
    }

}
