package com.cybershield.cybershield.controller;

import com.cybershield.cybershield.model.Report;
import com.cybershield.cybershield.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/report")
    public String submitReport(@RequestBody Report report) {
        reportRepository.save(report);
        return "Report submitted successfully!";
    }
}