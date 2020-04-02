package br.com.uniliva.ucontas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniliva.ucontas.business.BillBusiness;
import br.com.uniliva.ucontas.model.Bill;

@RestController
@RequestMapping("v1/bills")
public class BillController {

    @Autowired
	private BillBusiness business;

	@GetMapping
	public ResponseEntity<List<Bill>> listAll() {
		return ResponseEntity.ok(business.listAllBills());
	}

}