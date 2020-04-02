package br.com.uniliva.ucontas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uniliva.ucontas.exception.NotFoundException;
import br.com.uniliva.ucontas.model.Bill;
import br.com.uniliva.ucontas.service.BillService;

@Component
public class BillBusiness {

	@Autowired
	private BillService service;

	public List<Bill> listAll() {
		return service.listAll();
	}

	public Bill findById(Long id) {
		return service.findById(id).orElseThrow(() -> new NotFoundException("Bill not found"));
	}

	public Bill save(Bill Bill) {
		return service.save(Bill);
	}

	public void delete(Long id) {
		Bill Bill = findById(id);
		service.deleteById(Bill.getId());		
	}

	public Bill update(Bill Bill) {
		findById(Bill.getId());
		return service.save(Bill);
	}


}