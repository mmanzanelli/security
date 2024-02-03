package com.ad.equipojugadorsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad.equipojugadorsecurity.models.Jugador;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface JugadorRepository extends JpaRepository<Jugador, Integer>{

}
