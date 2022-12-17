package com.example.redisdemo2.controller;

import com.example.redisdemo2.payload.rp.CreateEntryRp;
import com.example.redisdemo2.payload.rp.InquiryWeekRp;
import com.example.redisdemo2.payload.rq.CreateEntryRq;
import com.example.redisdemo2.payload.rq.InquiryWeekRq;
import com.example.redisdemo2.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping("/entries/create")
    public CreateEntryRp createEntry(@RequestBody CreateEntryRq rq) {
        return entryService.createEntry(rq);
    }


    @PostMapping("/entries/edit")
    public CreateEntryRp editEntry(@RequestBody CreateEntryRq rq) {
        return entryService.editEntry(rq);
    }

    @PostMapping("/entries/delete")
    public CreateEntryRp deleteEntry(@RequestBody CreateEntryRq rq) {
        return entryService.delete(rq);
    }

    @PostMapping("/entries/inquiry")
    public InquiryWeekRp inquiryEntry(@RequestBody InquiryWeekRq rq) {
        return entryService.inquiry(rq);
    }
}
