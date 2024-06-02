package com.springmvcspecification.proyections;

import java.math.BigDecimal;

public interface PersonaProyeccion {
	Long getId();

	String getNombre();

	BigDecimal getSalario();

	String getCargo();

	String getEstadoNombre();

}