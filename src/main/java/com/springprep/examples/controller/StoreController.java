package com.springprep.examples.controller;

import com.springprep.examples.entity.Pen;
import com.springprep.examples.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity addPen(@RequestBody Pen pen){

        if(pen==null)
        return ResponseEntity.badRequest().build();

        Pen penAdded = storeService.addPen(pen);
        URI location=URI.create("/api/store/"+penAdded.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPen(@PathVariable int id){

        Pen pen = storeService.getPen(id);
        if(pen!=null){
            return ResponseEntity.ok(pen);
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity getPen(){

        List<Pen> pens = storeService.getPens();
        if(!pens.isEmpty()){
            return ResponseEntity.ok(pens);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity addPen(@PathVariable int id,@RequestBody Pen pen){

        if(pen==null || id==0)
            return ResponseEntity.badRequest().build();

        Pen penAdded = storeService.updatePen(id, pen);
        return ResponseEntity.ok(penAdded);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePen(@PathVariable int id){


        if(!storeService.deletePen(id)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
