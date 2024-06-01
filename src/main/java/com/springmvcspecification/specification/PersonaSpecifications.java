package com.springmvcspecification.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.springmvcspecification.entity.Persona;
import com.springmvcspecification.form.PersonaForm;

public class PersonaSpecifications {

	public static Specification<Persona> conNombre(String nombre) {
		return (root, query, criteriaBuilder) -> nombre == null ? null
				: criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
	}

	public static Specification<Persona> conCargo(String cargo) {
		return (root, query, criteriaBuilder) -> cargo == null ? null
				: criteriaBuilder.like(root.get("cargo"), "%" + cargo + "%");
	}

	public static Specification<Persona> conSalario(BigDecimal salario) {
		return (root, query, criteriaBuilder) -> salario == null ? null
				: criteriaBuilder.equal(root.get("salario"), salario);
	}

	public static Specification<Persona> conEstadoId(Long estadoId) {
		return (root, query, criteriaBuilder) -> estadoId == null ? null
				: criteriaBuilder.equal(root.get("estado").get("id"), estadoId);
	}

	public static Specification<Persona> buildSpecification(PersonaForm personaForm) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (personaForm.getNombre() != null && !personaForm.getNombre().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("nombre"), "%" + personaForm.getNombre() + "%"));
			}

			if (personaForm.getCargo() != null && !personaForm.getCargo().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("cargo"), "%" + personaForm.getCargo() + "%"));
			}

			if (personaForm.getSalario() != null) {
				predicates.add(criteriaBuilder.equal(root.get("salario"), personaForm.getSalario()));
			}

			if (personaForm.getEstadoId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("estado").get("id"), personaForm.getEstadoId()));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
