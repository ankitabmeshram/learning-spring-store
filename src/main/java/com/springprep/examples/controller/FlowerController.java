package com.springprep.examples.controller;

import com.springprep.examples.entity.Flower;
import com.springprep.examples.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/flower")
public class FlowerController {

    private FlowerService flowerService;

    @Autowired
    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getFlower(@PathVariable int id) {
        Flower flower = flowerService.getFlower(id);
        return ResponseEntity.ok(flower);

    }

    @GetMapping
    public ResponseEntity getFlowerFromRequestParams(@RequestParam(required = false) Integer flowerId, @RequestHeader(value = "FlowerName", required = false) String name) {

        List<Flower> flowers = new ArrayList<Flower>();
        if (flowerId == null && name == null) {
            flowers = flowerService.getFlowers();
            return ResponseEntity.ok(flowers);
        }

        if (flowerId != null) {


            Flower flower = flowerService.getFlower(flowerId);
            flowers.add(flower);
        }
        if (name != null) {
            flowers.addAll(flowerService.getFlowerByName(name));
        }
        return ResponseEntity.ok(flowers);

    }

    @PostMapping
    public ResponseEntity addFlower(@RequestBody Flower flower) {

        Flower flowerAdded = flowerService.addFlower(flower);
        URI location = URI.create("/api/flower/" + flowerAdded.getId());
        return ResponseEntity.created(location)
                             .build();

    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteFlower(@PathVariable int id) {
        if(flowerService.delete(id)) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping()
    public  ResponseEntity updateFlower(@RequestBody Flower flower) {

        return ResponseEntity.ok(flowerService.update(flower));
    }

}
