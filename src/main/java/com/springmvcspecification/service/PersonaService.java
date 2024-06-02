package com.springmvcspecification.service;

import java.util.List;

import com.springmvcspecification.dto.PersonaDTO;
import com.springmvcspecification.entity.Persona;
import com.springmvcspecification.form.PersonaForm;

public interface PersonaService {

	List<PersonaDTO> buscar(PersonaForm personaForm, int start, int length);

	long contar(PersonaForm personaForm);

	public Persona findById(Long id);


}
