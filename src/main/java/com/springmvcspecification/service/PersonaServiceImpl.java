package com.springmvcspecification.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springmvcspecification.entity.Persona;
import com.springmvcspecification.form.PersonaForm;
import com.springmvcspecification.repository.PersonaRepository;
import com.springmvcspecification.specification.PersonaSpecifications;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private EntityManager entityManager;



	@Override
	public Persona findById(Long id) {
		return personaRepository.getReferenceById(id);
	}

	@Override
	public List<Persona> buscar(PersonaForm personaForm, int start, int length) {
		if (length == 0) {
			return Collections.emptyList(); // Retorna una lista vacía si length es cero
		}
		Pageable pageable = PageRequest.of(start / length, length);
		Page<Persona> paginaPersonas = personaRepository.findAll(PersonaSpecifications.buildSpecification(personaForm),
				pageable);
		return paginaPersonas.getContent();
	}

	@Override
	public long contar(PersonaForm personaForm) {
		return personaRepository.count(PersonaSpecifications.buildSpecification(personaForm));
	}

}
