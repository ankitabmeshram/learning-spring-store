package com.springprep.examples.service;

import com.springprep.examples.entity.Pen;
import com.springprep.examples.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Pen addPen(Pen pen) {

        return storeRepository.save(pen);
    }

    public Pen getPen(int penId) {


        if (storeRepository.findById(penId)
                           .isPresent()) {
            return storeRepository.findById(penId)
                                  .get();
        }
        return null;
    }

    public List<Pen> getPens() {
        return (List<Pen>) storeRepository.findAll();
    }

    public Pen updatePen(int id, Pen penInput) {
        if (storeRepository.existsById(id)) {
            penInput.setId(id);
            return storeRepository.save(penInput);
        }
        return null;
    }

    public boolean deletePen(int id) {
        if (storeRepository.existsById(id)) {

            storeRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
