package com.example.redisdemo2.payload.rp;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryWeekRp {

    private double averageSlept;

    private LocalDateTime averageSleptTime;
    private LocalDateTime averageWakeupTime;
}
