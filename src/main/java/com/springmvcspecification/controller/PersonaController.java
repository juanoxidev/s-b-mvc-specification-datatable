package com.springmvcspecification.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springmvcspecification.datatable.DataTablesRequestForm;
import com.springmvcspecification.datatable.DataTablesResponse;
import com.springmvcspecification.dto.PersonaDTO;
import com.springmvcspecification.entity.Persona;
import com.springmvcspecification.form.PersonaForm;
import com.springmvcspecification.service.EstadoService;
import com.springmvcspecification.service.PersonaService;

@Controller
@SessionAttributes("personaForm")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private EstadoService estadoService;

	@ModelAttribute("personaForm")
	public PersonaForm setUpPersonaForm() {
		return new PersonaForm();
	}

	@GetMapping("/buscar")
	public String buscar(Model model, HttpSession session) {
		PersonaForm personaForm = (PersonaForm) session.getAttribute("personaForm");
		if (personaForm == null) {
			personaForm = new PersonaForm();
			session.setAttribute("personaForm", personaForm);
		}
		model.addAttribute("estados", estadoService.obtenerListaDeEstados());
		model.addAttribute("personaForm", personaForm);
		return "busquedad";
	}

	@PostMapping("/resultados")
	public @ResponseBody DataTablesResponse<PersonaDTO> resultados(
			@RequestBody DataTablesRequestForm<PersonaForm> dtRequest) {


		int cantidad = dtRequest.getLength();
		int primerResultado = dtRequest.getStart();
		PersonaForm personaForm = dtRequest.getFormBusqueda();

		DataTablesResponse<PersonaDTO> dtResponse = new DataTablesResponse<>();

		// Realizar la búsqueda
		List<PersonaDTO> personas = personaService.buscar(personaForm, primerResultado, cantidad);

		// Obtener el total de personas (para la paginación)
		Long totalPersonas = personaService.contar(personaForm);

		dtResponse.setRecordsTotal(totalPersonas.intValue());
		dtResponse.setRecordsFiltered(totalPersonas.intValue());

		dtResponse.setContent(personas);

		return dtResponse;
	}


	@PostMapping("/limpiarFiltros")
	public String limpiarFiltros(@ModelAttribute("personaForm") PersonaForm personaForm) {
		personaForm.clear(); // Limpiar el formulario
		return "redirect:/buscar";
	}

	@PostMapping("/persona")
	public String verPersona(@ModelAttribute("personaForm") PersonaForm personaForm, Model model) {
		// Obtener el ID de la persona del objeto PersonaForm
		Long id = personaForm.getId();

		// Obtener y agregar la persona al modelo
		Persona persona = personaService.findById(id);
		model.addAttribute("persona", persona);

		return "detallePersona";
	}

	@GetMapping("/volverABusqueda")
	public String volverABusqueda() {
		return "redirect:/buscar";
	}
}