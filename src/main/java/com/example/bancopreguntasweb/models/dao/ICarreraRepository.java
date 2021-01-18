package com.example.bancopreguntasweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bancopreguntasweb.models.entities.Carrera;


@Repository("carreraRepository")
public interface ICarreraRepository extends JpaRepository<Carrera, Integer> {

}
