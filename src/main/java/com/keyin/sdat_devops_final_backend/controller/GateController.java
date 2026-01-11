package com.keyin.sdat_devops_final_backend.controller;

import com.keyin.sdat_devops_final_backend.controller.dto.GateRequest;
import com.keyin.sdat_devops_final_backend.entity.Gate;
import com.keyin.sdat_devops_final_backend.service.GateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gates")
@CrossOrigin(origins = "*")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping
    public List<Gate> getAll() {
        return gateService.getAll();
    }

    @GetMapping("/{id}")
    public Gate getById(@PathVariable Long id) {
        return gateService.getById(id);
    }

    @PostMapping
    public Gate create(@RequestBody GateRequest req) {
        return gateService.create(req.name, req.terminal, req.airportId);
    }

    @PutMapping("/{id}")
    public Gate update(@PathVariable Long id, @RequestBody GateRequest req) {
        return gateService.update(id, req.name, req.terminal, req.airportId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gateService.delete(id);
    }
}
