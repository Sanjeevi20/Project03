package com.example.Project03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project03.model.Mainmodel;

public interface Mainrepository extends JpaRepository<Mainmodel,Long>{
    
}