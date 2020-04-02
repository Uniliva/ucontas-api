package br.com.uniliva.ucontas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniliva.ucontas.business.SearchZipCodeBusiness;
import br.com.uniliva.ucontas.dto.AddressDto;

@RestController
@RequestMapping("v1/zip-code")
@CrossOrigin(origins = "*")
public class SearchZipCodeController {

	@Autowired
	private SearchZipCodeBusiness business;

	@GetMapping("/{zipCode}")
	public ResponseEntity<AddressDto> findAddressByZipCode(@PathVariable String zipCode) {
		return ResponseEntity.ok().body(business.findAddressByZipCode(zipCode));
	}

}
