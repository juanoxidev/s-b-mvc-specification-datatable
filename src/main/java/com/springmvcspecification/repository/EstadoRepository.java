package com.springmvcspecification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvcspecification.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
