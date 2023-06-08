package com.Assignment.Assignmnet;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EventTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @MockBean
    private EventService eventService;

    private Event event;

    @BeforeEach
    public void setup() {
        event = new Event();
        event.setEventId(UUID.randomUUID());
        event.setTransId("0000abf8-d1f5-4536-8fb0-36fe934b1f28");
        event.setTransTms("20151022102011927EDT");
        event.setRcNum("10002");
        event.setClientId("RPS-00001");
        event.setEventCnt(1);
        event.setLocationCd("DESTINATION");
        event.setLocationId1("T8C");
        event.setLocationId2("1J7");
        event.setAddrNbr("0000000001");

    }

    @Test
    public void testAllEvents() {
        when(eventService.findallEvents()).thenReturn(List.of(event));
        Exchange result = producerTemplate.request("direct:getAllEvents", null);

        verify(eventService).findallEvents();
        assert result != null;
        assert result.getMessage().getBody(List.class).size() == 1;
    }

    @Test
    public void testGetEvent() {
        when(eventService.getEvent(event.getEventId())).thenReturn(event);

        Exchange result = producerTemplate.request("direct:getEvent", exchange -> {
            exchange.getIn().setHeader("eventId", event.getEventId());
        });

        verify(eventService).getEvent(event.getEventId());
        assert result != null;
        assert result.getMessage().getBody(Event.class) == event;
    }


    @Test
    public void testDeleteEvent() {
        Exchange result = producerTemplate.request("direct:deleteEvent", exchange -> {
            exchange.getIn().setHeader("eventId", event.getEventId());
        });

        verify(eventService).deleteEvent(event.getEventId());

        assert result == null;
    }
}
