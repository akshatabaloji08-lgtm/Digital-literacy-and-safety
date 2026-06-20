package com.cybershield.cybershield.controller;

import com.cybershield.cybershield.model.Admin;
import com.cybershield.cybershield.model.Report;
import com.cybershield.cybershield.repository.AdminRepository;
import com.cybershield.cybershield.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ReportRepository reportRepository;

    // Show admin login page
    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    // Handle admin login
    @PostMapping("/admin/login")
    public String adminLoginPost(@RequestParam String email,
                                  @RequestParam String password,
                                  Model model) {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin != null) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "admin_login";
        }
    }

    // Show admin dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);
        return "admin_dashboard";
    }

    // Update report status
    @PostMapping("/admin/update/{id}")
    public String updateStatus(@PathVariable int id,
                               @RequestParam String status) {
        Report report = reportRepository.findById(id).orElse(null);
        if (report != null) {
            report.setStatus(status);
            reportRepository.save(report);
        }
        return "redirect:/admin/dashboard";
    }

    // Delete report
    @PostMapping("/admin/delete/{id}")
    public String deleteReport(@PathVariable int id) {
        reportRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}