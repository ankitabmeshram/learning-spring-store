package com.springprep.examples.controller;

import com.springprep.examples.entity.Flower;
import com.springprep.examples.service.CandyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/candy")
public class CandyController {


    private CandyService candyService;

    public CandyController(CandyService candyService) {
        this.candyService = candyService;
    }

    @GetMapping
    public ResponseEntity getCandies(@RequestHeader("children") List<Integer> children) {

        if (children.isEmpty()) {
            return ResponseEntity.badRequest()
                                 .build();
        }
        Integer count = candyService.getCandies(children);
        return ResponseEntity.ok()
                             .header("Count", count.toString())
                             .build();

    }

    @PostMapping
    public ResponseEntity addCandy(@RequestBody Flower flower) {
        Flower flower1 = candyService.addFlower(flower);
        URI uri = URI.create("/api/candy/" + flower1.getId());
        return ResponseEntity.created(uri)
                             .build();
    }

}
