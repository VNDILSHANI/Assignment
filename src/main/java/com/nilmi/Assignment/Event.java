package com.nilmi.Assignment;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private int eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;


    public Event(UUID eventId, String transId, String transTms, String rcNum, String clientId, int eventCnt, String locationCd,String locationId1,String locationId2, String addrNbr) {
        this.eventId = eventId;
        this.transId = transId;
        this.transTms = transTms;
        this.rcNum = rcNum;
        this.clientId = clientId;
        this.eventCnt = eventCnt;
        this.locationCd = locationCd;
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
        this.addrNbr = addrNbr;
    }

    public Event() {

    }
}
