package br.com.uniliva.ucontas.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.dto.AddressDto;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.service.SearchZipCodeService;

@RunWith(MockitoJUnitRunner.class)
public class SearchZipCodeBusinessTest {

	@InjectMocks
	private SearchZipCodeBusiness business;

	@Mock
	private SearchZipCodeService service;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void configure() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}

	@Test
	public void shouldFindAddressByZipCode() {

		final AddressDto fixture = Fixture.from(AddressDto.class).gimme("valid");

		when(service.findAddressByZipCode(anyString())).thenReturn(fixture);

		AddressDto address = business.findAddressByZipCode("06250310");

		assertNotNull(address);
		assertEquals(fixture, address);

	}
	
	@Test
	public void shouldReturnExceptionWhenFindAddressWithZipCodeInvalid() {
		
		expectedException.expect(BusinessException.class);
		expectedException.expectMessage("The zip code must contain 8 digits");

		business.findAddressByZipCode("06250310000");
	}
}
