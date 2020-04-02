package br.com.uniliva.ucontas.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uniliva.ucontas.dto.AddressDto;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.service.SearchZipCodeService;

@Component
public class SearchZipCodeBusiness {

	@Autowired
	private SearchZipCodeService service;

	public AddressDto findAddressByZipCode(String zipCode) {
		zipCode = zipCode.replace("-", "");
		if (zipCode.length() > 8)
			throw new BusinessException("The zip code must contain 8 digits");
		return service.findAddressByZipCode(zipCode);
	}
}
