package br.com.uniliva.ucontas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.uniliva.ucontas.business.BillBusiness;
import br.com.uniliva.ucontas.model.Bill;

@RestController
@RequestMapping("v1/bills")
@CrossOrigin(origins = "*")
public class BillController {

    @Autowired
	private BillBusiness business;

	@GetMapping
	public ResponseEntity<List<Bill>> listAll() {
		return ResponseEntity.ok(business.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bill> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(business.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Bill> save(@Valid @RequestBody Bill Bill) {
		Bill obj = business.save(Bill);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Bill> update(@Valid @RequestBody Bill Bill) {
		return ResponseEntity.ok(business.update(Bill));
	}

}