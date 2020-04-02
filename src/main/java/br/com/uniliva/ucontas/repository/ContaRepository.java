package br.com.uniliva.ucontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uniliva.ucontas.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {}