package br.com.uniliva.ucontas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniliva.ucontas.model.Bill;
import br.com.uniliva.ucontas.repository.BillRepository;

@Service
public class BillService {
	@Autowired
	private BillRepository repo;

	public List<Bill> listAll() {
		return repo.findAll();
	}

	public Optional<Bill> findById(long id) {
		return repo.findById(id);
	}

	public Bill save(Bill Bill) {
		return repo.save(Bill);		
	}

	public void deleteById(Long id) {
		repo.deleteById(id);;		
	}

}