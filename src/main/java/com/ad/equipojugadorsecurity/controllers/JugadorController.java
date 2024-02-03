package com.ad.equipojugadorsecurity.controllers;



import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ad.equipojugadorsecurity.dto.JugadorDTO;
import com.ad.equipojugadorsecurity.service.JugadorService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JugadorController {

    private static final Logger myLog = LoggerFactory.getLogger(JugadorController.class);

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private HttpServletRequest context;

    @GetMapping("/jugadores")
    public List<JugadorDTO> listJugadores() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return jugadorService.listAllJugadores();
    }

    @GetMapping("/jugadores/{idJugador}")
    public ResponseEntity<JugadorDTO> showJugadorById(@PathVariable Integer idJugador) {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        JugadorDTO elJugador = jugadorService.getJugadorById(idJugador);
        if (elJugador == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(elJugador, HttpStatus.OK);
    }

    @PostMapping("/jugadoresadd")
    public ResponseEntity<JugadorDTO> addJugador(@RequestBody JugadorDTO nuevoJugador) {
        myLog.info(context.getMethod() + context.getRequestURI());
        if (nuevoJugador == null) {
            return ResponseEntity.badRequest().body(null);
        }
        jugadorService.saveJugador(nuevoJugador);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }

    @PutMapping("/jugadoresup")
    public ResponseEntity<JugadorDTO> updateJugador(@RequestBody JugadorDTO updJugador) {
        myLog.info(context.getMethod() + context.getRequestURI());
        JugadorDTO elJugador = jugadorService.getJugadorById(updJugador.getId());  
        if (elJugador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            jugadorService.saveJugador(updJugador);
            return new ResponseEntity<>(updJugador, HttpStatus.OK);
        }
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<String> handleError(ResponseStatusException e) {
        myLog.error("Error en JugadorController: " + e.getMessage());
        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }

}