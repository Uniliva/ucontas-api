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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/zip-code")
@Api(value = "API for search zip code")
@CrossOrigin(origins = "*")
public class SearchZipCodeController {

	@Autowired
	private SearchZipCodeBusiness business;

	@GetMapping("/{zipCode}")
	@ApiOperation(value = "Search for an address using a zip code")
	public ResponseEntity<AddressDto> findAddressByZipCode(@PathVariable String zipCode) {
		return ResponseEntity.ok().body(business.findAddressByZipCode(zipCode));
	}

}
