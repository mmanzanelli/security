package com.ad.equipojugadorsecurity.dto;

import java.io.Serializable;

import com.ad.equipojugadorsecurity.models.Jugador;

import lombok.Data;

@Data
public class JugadorDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String posicion;
    private Integer equipoId; // ID del equipo al que pertenece el jugador

    public static JugadorDTO convertToDTO(Jugador jugador) {
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setPosicion(jugador.getPosicion());
        if (jugador.getEquipo() != null) {
            jugadorDTO.setEquipoId(jugador.getEquipo().getId());
        }
        return jugadorDTO;
    }

    public static Jugador convertToEntity(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setId(jugadorDTO.getId());
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setPosicion(jugadorDTO.getPosicion());
        // Nota: La asignación de 'Equipo' se deja pendiente y debe ser manejada en el servicio
        return jugador;
    }
}