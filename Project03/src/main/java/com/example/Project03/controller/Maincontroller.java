package com.example.Project03.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project03.model.Mainmodel;
import com.example.Project03.repository.Mainrepository;

@RestController
@RequestMapping("/api")
public class Maincontroller {

    @Autowired
    Mainrepository mainrepository;

    @GetMapping("/show_all")
    public List<Mainmodel> getAllRows(){
        return (List<Mainmodel>) mainrepository.findAll();

    }
    
    @PostMapping("/insert")
    public ResponseEntity<Mainmodel> insertValues(@RequestBody Mainmodel model){
        Mainmodel _model = mainrepository.save(new Mainmodel( model.getName(), model.getAge()));
        return new ResponseEntity<> (_model, HttpStatus.OK);
        
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<Mainmodel> deleteAllRows(){
        mainrepository.deleteAll();
        return new ResponseEntity<Mainmodel>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Mainmodel> updateRow(@PathVariable("id") Long id, @RequestBody Mainmodel model){
        Optional<Mainmodel> _model = mainrepository.findById(id);
        if(_model.isPresent()){
            Mainmodel __model = _model.get();
            __model.setName(model.getName());
            __model.setAge(model.getAge());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/show/{id}")
    public Optional<Mainmodel> findbyid(@PathVariable Long id){
        return (Optional<Mainmodel>) mainrepository.findById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        Optional<Mainmodel> ____model = mainrepository.findById(id);
        if (____model != null) {
            mainrepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}