package com.example.demo.controller;

import com.example.demo.model.VehicleCharge;
import com.example.demo.service.VehicleChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicle_charge")
public class VehicleChargeController {
    @Autowired
    private VehicleChargeService service;

    @GetMapping
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        List<VehicleCharge> list = service.getAll(page, size);
        int total = service.count();
        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) total / size));
        return "vehicle_charge/list";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            VehicleCharge vehicleCharge = service.getAll(1, 1).stream()
                    .filter(vc -> vc.getId() == id)
                    .findFirst()
                    .orElse(null);
            model.addAttribute("vehicleCharge", vehicleCharge);
        } else {
            model.addAttribute("vehicleCharge", new VehicleCharge());
        }
        return "vehicle_charge/form";
    }

    @PostMapping("/save")
    public String save(VehicleCharge vehicleCharge) {
        if (vehicleCharge.getId() > 0) {
            service.update(vehicleCharge);
        } else {
            service.add(vehicleCharge);
        }
        return "redirect:/vehicle_charge";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        service.delete(id);
        return "redirect:/vehicle_charge";
    }
}
