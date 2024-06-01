package com.springmvcspecification.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PersonaForm {

	private Long id;
	private String nombre;
	private String cargo;
	private BigDecimal salario;
	private Long estadoId;
	private int page; // Número de página
	private int pageSize; // Tamaño de la página

	public void clear() {
		this.id = null;
		this.nombre = null;
		this.cargo = null;
		this.salario = null;
		this.estadoId = null;
	}

}
