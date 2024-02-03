package com.ad.equipojugadorsecurity.service;


import java.util.List;

import com.ad.equipojugadorsecurity.dto.JugadorDTO;

public interface JugadorService {

    void saveJugador(JugadorDTO jugadorDTO);
    JugadorDTO getJugadorById(Integer id);
    void deleteJugador(Integer id);
	List<JugadorDTO> listAllJugadores();
}