package com.example.redisdemo2.service;

import com.example.redisdemo2.entity.Entry;
import com.example.redisdemo2.entity.User;
import com.example.redisdemo2.payload.rp.CreateEntryRp;
import com.example.redisdemo2.payload.rp.InquiryWeekRp;
import com.example.redisdemo2.payload.rq.CreateEntryRq;
import com.example.redisdemo2.payload.rq.InquiryWeekRq;
import com.example.redisdemo2.repository.EntryRepository;
import com.example.redisdemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final UserRepository userRepo;
    private final UserService userService;
    private final EntryRepository entryRepo;

    public CreateEntryRp createEntry(CreateEntryRq rq) {
        CreateEntryRp rp = new CreateEntryRp();
        Optional<User> userOpt = userRepo.findById(rq.getUserId());
        if (userOpt.isEmpty()) {
            rp.setStatus("invalid input");
        }
        Entry entry = new Entry();
        entry.setUserId(rq.getUserId());
        entry.setDate(rq.getDate());
        entry.setSleepTime(rq.getSleepTime());
        entry.setWakeUpTime(rq.getWakeUpTime());
        entryRepo.save(entry);
        rp.setId(entry.getId());
        rp.setSleepTime(entry.getSleepTime());
        rp.setDate(entry.getDate());
        rp.setUserId(entry.getUserId());
        rp.setWakeUpTime(entry.getWakeUpTime());
        return rp;
    }

    public CreateEntryRp editEntry(CreateEntryRq rq) {
        CreateEntryRp rp = new CreateEntryRp();
        Optional<User> userOpt = userRepo.findById(rq.getUserId());
        if (userOpt.isEmpty()) {
            rp.setStatus("invalid input");
        }
        Entry entry = entryRepo.findById(rq.getId()).get();
        entry.setUserId(rq.getUserId());
        entry.setDate(rq.getDate());
        entry.setSleepTime(rq.getSleepTime());
        entry.setWakeUpTime(rq.getWakeUpTime());
        entryRepo.save(entry);
        rp.setId(entry.getId());
        rp.setSleepTime(entry.getSleepTime());
        rp.setDate(entry.getDate());
        rp.setUserId(entry.getUserId());
        rp.setWakeUpTime(entry.getWakeUpTime());
        return rp;
    }

    public CreateEntryRp delete(CreateEntryRq rq) {
        userRepo.deleteById(rq.getId());
        CreateEntryRp rp = new CreateEntryRp();
        rp.setStatus("successfully");
        return rp;
    }

    public InquiryWeekRp inquiry(InquiryWeekRq rq) {
        List<Entry> entries = entryRepo.findByUserId(rq.getUserId());
        List<Entry> filterEntries = entries.stream().filter(t -> {
            LocalDate date = t.getDate();
            return (date.isBefore(rq.getToDate()) && date.isAfter(rq.getFromDate()));
        }).collect(Collectors.toList());

        double total = 0;
        long sleepingTime = 0l;
        long wakeUpTime = 0L;
        for (Entry entry: filterEntries) {
            total += entry.getTotalSleptHours();
            sleepingTime += entry.getSleepTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            wakeUpTime += entry.getWakeUpTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        InquiryWeekRp rp = new InquiryWeekRp();
        rp.setAverageSlept(total/filterEntries.size());
        rp.setAverageSleptTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(sleepingTime/filterEntries.size()), TimeZone.getDefault().toZoneId()));
        rp.setAverageWakeupTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(wakeUpTime/filterEntries.size()), TimeZone.getDefault().toZoneId()));
        return rp;
    }
}
