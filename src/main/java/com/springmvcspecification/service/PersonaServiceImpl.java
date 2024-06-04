package com.springmvcspecification.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springmvcspecification.dto.PersonaDTO;
import com.springmvcspecification.entity.Persona;
import com.springmvcspecification.entity.QPersona;
import com.springmvcspecification.form.PersonaForm;
import com.springmvcspecification.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private JPAQueryFactory queryFactory;

	@Autowired
	private PersonaRepository personaRepository;




	@Override
	public Persona findById(Long id) {
		return personaRepository.getReferenceById(id);
	}

	@Override
	public List<PersonaDTO> buscar(PersonaForm personaForm, int start, int length) {
		QPersona qPersona = QPersona.persona;
		BooleanExpression predicate = buildPredicate(personaForm);
		Pageable pageable = PageRequest.of(start / length, length);

		return queryFactory
				.select(Projections.bean(PersonaDTO.class, 
						qPersona.nombre.coalesce("NO DISPONIBLE").as("nombre"),																																							// DISPONIBLE"
						qPersona.salario.coalesce(BigDecimal.ZERO).as("salario"), 													
						qPersona.cargo.coalesce("NO DISPONIBLE").as("cargo"), 
						qPersona.estado.detalle.coalesce("NO DISPONIBLE").as("estadoNombre")))
				.from(qPersona)
				.where(predicate)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize()).fetch();
	}


	/*
	 * Consideraciones adicionales Paginación y Ordenación: Si también deseas
	 * incluir la ordenación (sorting) en la consulta, puedes agregar condiciones de
	 * ordenación basadas en el objeto Pageable: java Copiar código if
	 * (pageable.getSort().isSorted()) { for (Sort.Order order : pageable.getSort())
	 * { PathBuilder<Object> path = new PathBuilder<>(qPersona.getType(),
	 * qPersona.getMetadata()); query.orderBy(new
	 * OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC,
	 * path.get(order.getProperty()))); } }
	 */



	@Override
	public long contar(PersonaForm personaForm) {
		QPersona qPersona = QPersona.persona;
		BooleanExpression predicate = buildPredicate(personaForm);

		return queryFactory.select(qPersona.count()).from(qPersona).where(predicate).fetchOne();
	}

	// Especification Predicate
	private BooleanExpression buildPredicate(PersonaForm personaForm) {

		QPersona qPersona = QPersona.persona;
		BooleanExpression predicate = null; // Iniciar como null

		String nombre = personaForm.getNombre();
		String cargo = personaForm.getCargo();
		BigDecimal salario = personaForm.getSalario();
		Long estadoId = personaForm.getEstadoId();

		if (nombre != null && !nombre.isEmpty()) {
			System.out.println("Nombre: " + nombre);
			predicate = qPersona.nombre.containsIgnoreCase(nombre);
		}
		if (cargo != null && !cargo.isEmpty()) {
			System.out.println("Cargo: " + cargo); // Aquí corregí "nombre" a "cargo"
			predicate = (predicate != null) ? predicate.and(qPersona.cargo.containsIgnoreCase(cargo))
					: qPersona.cargo.containsIgnoreCase(cargo);
		}
		if (salario != null) {
			System.out.println("Salario: " + salario); // Aquí corregí "nombre" a "salario"
			predicate = (predicate != null) ? predicate.and(qPersona.salario.eq(salario))
					: qPersona.salario.eq(salario);
		}
		if (estadoId != null) {
			System.out.println("Estado ID: " + estadoId); // Aquí corregí "nombre" a "estadoId"
			predicate = (predicate != null) ? predicate.and(qPersona.estado.id.eq(estadoId))
					: qPersona.estado.id.eq(estadoId);
		}

		/*
		 * predicate.and(qPersona.estado.id.eq(estadoId))
		 * .and(qPersona.estado.id.ne(2L)) // Excluye el estado con ID 2 :
		 * qPersona.estado.id.ne(2L); // Excluye el estado con ID 2 directamente
		 */

		// Agregar una condición adicional si deseas filtrar por personas cuyo nombre no
		// sea nulo
		// Si no deseas esta condición, simplemente elimina este bloque de código
		predicate = (predicate != null) ? predicate.and(qPersona.nombre.isNotNull()) : qPersona.nombre.isNotNull();

		return predicate;
	}

}
