package com.example.redisdemo2.payload.rp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEntryRp {

    private Integer id;
    private Integer userId;
    private LocalDate date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sleepTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime wakeUpTime;

    private double totalSleptHours;
    private String status;
}
