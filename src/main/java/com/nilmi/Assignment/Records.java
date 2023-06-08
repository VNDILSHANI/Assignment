package com.nilmi.Assignment;

import lombok.Data;

import java.util.List;

@Data

public class Records {
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private List<EventData> event;
}
