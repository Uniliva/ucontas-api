package br.com.uniliva.ucontas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.dto.AddressDto;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.service.SearchZipCodeService;

@RunWith(MockitoJUnitRunner.class)
public class SearchZipCodeServiceTest {
	
	@InjectMocks
	private SearchZipCodeService service;

	@Mock
	private RestTemplate restTemplate;
	

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void configure() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}

	@Test
	public void shouldFindAddressByZipCode() {

		final AddressDto fixture = Fixture.from(AddressDto.class).gimme("valid");

		ResponseEntity<AddressDto> response = new ResponseEntity<AddressDto>(fixture, HttpStatus.OK);

		when(restTemplate.getForEntity(anyString(), eq(AddressDto.class))).thenReturn(response);

		AddressDto address = service.findAddressByZipCode("06251020");
		
		assertNotNull(address);
		assertEquals(fixture, address);

	}
	
	@Test
	public void shouldReturnExceptionWhenFindAddressWithZipCodeInvalid() {

		final AddressDto fixture = Fixture.from(AddressDto.class).gimme("invalid");
		
		expectedException.expect(BusinessException.class);
		expectedException.expectMessage("Zip code invalid!!");

		ResponseEntity<AddressDto> response = new ResponseEntity<AddressDto>(fixture, HttpStatus.OK);

		when(restTemplate.getForEntity(anyString(), eq(AddressDto.class))).thenReturn(response);

		service.findAddressByZipCode("06251020");

	}
	
	@Test
	public void shouldReturnHttpServerErrorExceptionWhenFindAddressAndServiceOffiline() {
		
		expectedException.expect(BusinessException.class);

		when(restTemplate.getForEntity(anyString(), eq(AddressDto.class))).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error consulting third-party service"));

		service.findAddressByZipCode("06251020");

	}
	
	@Test
	public void  shouldReturnHttpClientErrorExceptionWhenFindAddressWithRequestInvalid() {
		
		expectedException.expect(BusinessException.class);

		when(restTemplate.getForEntity(anyString(), eq(AddressDto.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Request invalid"));

		service.findAddressByZipCode("06251020");

	}

}
