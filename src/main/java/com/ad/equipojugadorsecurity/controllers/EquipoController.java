package com.ad.equipojugadorsecurity.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ad.equipojugadorsecurity.dto.EquipoDTO;
import com.ad.equipojugadorsecurity.service.EquipoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EquipoController {

	private static final Logger myLog = LoggerFactory.getLogger(EquipoController.class);

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private HttpServletRequest context;
	
	@PostMapping(value = "equipos")
	public List<EquipoDTO> listEquipos() {
		myLog.info(context.getMethod() + " from " + context.getRemoteHost());
		List<EquipoDTO> losEquipos = equipoService.listAllEquipos();
		return losEquipos;

	}
	
	

	@GetMapping("/equipos/{idEquipo}")
	public ResponseEntity<EquipoDTO> showEquipoById(@PathVariable Integer idEquipo) {
		myLog.info(context.getMethod() + " from " + context.getRemoteHost());
		EquipoDTO elEquipo = equipoService.getEquipoById(idEquipo);
		if (elEquipo == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(elEquipo, HttpStatus.OK);
	}

	@PostMapping("/equiposadd")
	public ResponseEntity<EquipoDTO> addEquipo(@RequestBody EquipoDTO nuevoEquipo) {
		myLog.info(context.getMethod() + context.getRequestURI());

		if (nuevoEquipo == null) {
			// Retorna un Bad Request si el nuevoEquipo es null
			return ResponseEntity.badRequest().body(null); // Cuerpo null con estado BAD_REQUEST
		}
		equipoService.saveEquipo(nuevoEquipo);
		return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
	}

	@PutMapping("/equiposup")
	public ResponseEntity<EquipoDTO> updateEquipo(@RequestBody EquipoDTO updEquipo) {
		myLog.info(context.getMethod() + context.getRequestURI());
		// Buscamos si existe previamente
		EquipoDTO elEquipo = equipoService.getEquipoById(updEquipo.getId());
		if (elEquipo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			// Como ya sabemos que existe, save actualiza
			equipoService.saveEquipo(updEquipo);
			return new ResponseEntity<>(updEquipo, HttpStatus.OK);
		}
	}

	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<String> handleError(ResponseStatusException e) {
		myLog.error("Error en EquipoController: " + e.getMessage());
		return new ResponseEntity<>(e.getReason(), e.getStatusCode());
	}

}
