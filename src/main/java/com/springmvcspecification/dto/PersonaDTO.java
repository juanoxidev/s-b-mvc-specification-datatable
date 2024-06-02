package com.springmvcspecification.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDTO {

	private Long id;
	private String nombre;
	private BigDecimal salario;
	private String cargo;
	private String estadoNombre;
}
