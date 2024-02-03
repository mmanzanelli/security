package com.ad.equipojugadorsecurity.service;


import java.util.List;

import com.ad.equipojugadorsecurity.dto.EquipoDTO;



public interface EquipoService {
	
	void saveEquipo (EquipoDTO equipoDTO);
	EquipoDTO getEquipoById(Integer id);
	List<EquipoDTO> listAllEquipos();
	void deleteEquipo(Integer id);
	
	

}