package com.Assignment.Assignmnet;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class payLoadTest {

    @SpringBootTest
    public class EventRouteTest{

        @Autowired
        private EventRepository eventRepository;

        @EndpointInject("direct:saveEvents")
        private ProducerTemplate producerTemplate;

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new EventRout(eventRepository);
        }

        @Test
        public void testEventRoute() throws Exception {

            String payload = "{\"batchId\":\"0310abf6-d1f5-a1b3-8fb0-36fe934b1f28\"," +
                    "\"records\":" +
                    "[{\"transId\":\"0000abf8-d1f5-4536-8fb0-36fe934b1f28\"," +
                    "\"transTms\":\"20151022102011927EDT\"," +
                    "\"rcNum\":\"10002\"," +
                    "\"clientId\":\"RPS-00001\"," +
                    "\"event\":[{\"eventCnt\":1," +
                                "\"locationCd\":\"DESTINATION\"," +
                                "\"locationId1\":\"T8C\"," +
                                "\"locationId2\":\"1J7\"" +
                                ",\"addrNbr\":\"0000000001\"}," +
                    "{\"eventCnt\":1," +
                    "\"locationCd\":" +
                    "\"CUSTOMER NUMBER\"," +
                    "\"locationId1\":\"0007316971\"}," +
                    "{\"eventCnt\":1," +
                    "\"locationCd\":\"" +
                    "OUTLET ID\"," +
                    "\"locationId1\":\"I029\"}]}" +
                    ",{\"transId\":\"0000abf8-d1f5-4536-8fb0-36fe934b1f28\"," +
                    "\"transTms\":\"20151022102011927EDT\",\"" +
                    "rcNum\":\"10002\"," +
                    "\"clientId\":\"RPS-00001\"," +
                    "\"event\":[{\"eventCnt\":1," +
                    "\"locationCd\":\"DESTINATION\"," +
                    "\"locationId1\":\"T8C\"," +
                    "\"locationId2\":\"1J7\",\"addrNbr\":\"0000000001\"}]}]}";

        }
    }
}
