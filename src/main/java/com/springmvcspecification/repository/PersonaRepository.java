package com.springmvcspecification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.springmvcspecification.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>, QuerydslPredicateExecutor<Persona> {

}