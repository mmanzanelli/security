package com.ad.equipojugadorsecurity.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.equipojugadorsecurity.dto.JugadorDTO;
import com.ad.equipojugadorsecurity.models.Equipo;
import com.ad.equipojugadorsecurity.models.Jugador;
import com.ad.equipojugadorsecurity.repository.EquipoRepository;
import com.ad.equipojugadorsecurity.repository.JugadorRepository;


@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public void saveJugador(JugadorDTO jugadorDTO) {
        Jugador jugador = convertToEntity(jugadorDTO);
        jugadorRepository.save(jugador);
    }

    @Override
    public JugadorDTO getJugadorById(Integer id) {
        Optional<Jugador> jugador = jugadorRepository.findById(id);
        return jugador.map(JugadorDTO::convertToDTO).orElse(null);
    }

    @Override
    public List<JugadorDTO> listAllJugadores() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        List<JugadorDTO> jugadorDTOs = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            jugadorDTOs.add(JugadorDTO.convertToDTO(jugador));
        }
        return jugadorDTOs;
    }

    @Override
    public void deleteJugador(Integer id) {
        jugadorRepository.deleteById(id);
    }

    private Jugador convertToEntity(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setId(jugadorDTO.getId());
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setPosicion(jugadorDTO.getPosicion());
        if (jugadorDTO.getEquipoId() != null) {
            Equipo equipo = equipoRepository.findById(jugadorDTO.getEquipoId())
                                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            jugador.setEquipo(equipo);
        }
        return jugador;
    }
}
