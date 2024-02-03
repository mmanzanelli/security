package com.ad.equipojugadorsecurity.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.equipojugadorsecurity.dto.EquipoDTO;
import com.ad.equipojugadorsecurity.models.Equipo;
import com.ad.equipojugadorsecurity.repository.EquipoRepository;




@Service
public class EquipoServiceImpl implements EquipoService {
	
	@Autowired
	private EquipoRepository equipoRepository;
	
	
	@Override
	public void saveEquipo(EquipoDTO equipoDTO) {
		Equipo equipo = EquipoDTO.ConvertToEntity(equipoDTO);
		equipoRepository.save(equipo);
	
		
		
	}
	
	 @Override
	    public EquipoDTO getEquipoById(Integer id) {
	        Optional<Equipo> equipo = equipoRepository.findById(id);
	        if (equipo.isPresent()) {
	            return EquipoDTO.convertToDTO(equipo.get());
	        } else {
	            return null;
	        }
	    }

	    @Override
	    public List<EquipoDTO> listAllEquipos() {
	        List<Equipo> lista = equipoRepository.findAll();
	        List<EquipoDTO> listaResultado = new ArrayList<>();
	        for (Equipo equipo : lista) {
	            listaResultado.add(EquipoDTO.convertToDTO(equipo));
	        }
	        return listaResultado;
	    }

		@Override
		public void deleteEquipo(Integer id) {
			equipoRepository.deleteById(id);		
		}

}
