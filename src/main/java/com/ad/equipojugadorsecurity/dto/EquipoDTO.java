package com.ad.equipojugadorsecurity.dto;

import java.io.Serializable;

import com.ad.equipojugadorsecurity.models.Equipo;

import lombok.Data;

@Data
public class EquipoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 private Integer id;

	 private String nombre;
	 private String ciudad;
	 
	 public static EquipoDTO convertToDTO(Equipo equipo) {
		 EquipoDTO equipoDTO = new EquipoDTO();
		 equipoDTO.setId(equipo.getId());
		 equipoDTO.setNombre(equipo.getNombre());
		 equipoDTO.setCiudad(equipo.getCiudad());
		 
		 return equipoDTO;
		 
		 
		 
	 }
	 
	 
	 
	 public static Equipo ConvertToEntity(EquipoDTO equipoDTO) {
		 Equipo equipo = new Equipo();
		 equipo.setId(equipoDTO.getId());
		 equipo.setNombre(equipoDTO.getNombre());
		 equipo.setCiudad(equipoDTO.getCiudad());
		 
		 return equipo;
		 
		 
	 }
	 


}
