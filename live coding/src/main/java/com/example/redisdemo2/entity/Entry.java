package com.example.redisdemo2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "sleep_day")
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private double sleptHours; // thời gian ngủ đã ngủ 1 ngày
    private double totalSleptHours; // thời gian ngủ tối đa 1 ngày theo như cấu hình
    private LocalDateTime sleepTime; // thời gian bắt đầu đi ngủ
    private LocalDateTime wakeUpTime; // thời gian thức dậy
    private LocalDate date; // ngày lựa chọn
}
