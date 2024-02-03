package com.ad.equipojugadorsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad.equipojugadorsecurity.models.Equipo;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

}