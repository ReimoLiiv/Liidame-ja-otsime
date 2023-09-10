package com.politsei.kodutoo.controller;

import com.politsei.kodutoo.model.Addition;
import com.politsei.kodutoo.service.AdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdditionController {

    @Autowired
    private AdditionService additionService;

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        if (a == null || b == null || a < 0 || a > 100 || b < 0 || b > 100) {
            return ResponseEntity.badRequest().body("Palun anna mulle kaks täisarvu vahemikus 0-100 (kaasa arvatud).");
        }

        Addition addition = new Addition(a, b);
        additionService.addAddition(addition);
        return ResponseEntity.ok(addition);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) Integer value,
                                                 @RequestParam("order") String order) {

        if (order == null || (!"asc".equalsIgnoreCase(order) && !"desc".equalsIgnoreCase(order))) {
            return ResponseEntity.badRequest().body("Mul on vaja teada kas kuvan vastuseid kasvavas (asc) või kahanevas (desc) järjekorras.");
        }

        if (value != null && (value < 0 || value > 100)) {
            return ResponseEntity.badRequest().body("Kui lisad otsitava arvu, anna ta palun vahemikus 0-100 (kaasa arvatud).");
        }

        List<Addition> results = additionService.searchAdditions(value, "asc".equalsIgnoreCase(order));
        return ResponseEntity.ok(results);
    }
}

