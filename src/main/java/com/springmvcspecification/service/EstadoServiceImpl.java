package com.springmvcspecification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvcspecification.entity.Estado;
import com.springmvcspecification.repository.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> obtenerListaDeEstados() {
		return estadoRepository.findAll();
	}
}