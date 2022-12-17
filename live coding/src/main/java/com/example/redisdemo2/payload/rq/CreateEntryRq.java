package com.example.redisdemo2.payload.rq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEntryRq {

    private Integer id;
    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sleepTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime wakeUpTime;

    private double totalSleptHours;
}
