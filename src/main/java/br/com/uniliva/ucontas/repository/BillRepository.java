package br.com.uniliva.ucontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uniliva.ucontas.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {}