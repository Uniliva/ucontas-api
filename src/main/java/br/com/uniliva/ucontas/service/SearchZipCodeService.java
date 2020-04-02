package br.com.uniliva.ucontas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.uniliva.ucontas.dto.AddressDto;
import br.com.uniliva.ucontas.exception.BusinessException;

@Service
public class SearchZipCodeService {

	@Autowired
	private RestTemplate restTemplate;

	public AddressDto findAddressByZipCode(String zipCode) {

		String url = "https://viacep.com.br/ws/" + zipCode + "/json";

		try {

			ResponseEntity<AddressDto> response = restTemplate.getForEntity(url, AddressDto.class);

			AddressDto add = response.getBody();

			if (add.getLocality() == null) {
				throw new BusinessException("Zip code invalid!!");
			}

			return add;

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new BusinessException("Error consulting third-party service" + e.getMessage());
		}

	}

}
