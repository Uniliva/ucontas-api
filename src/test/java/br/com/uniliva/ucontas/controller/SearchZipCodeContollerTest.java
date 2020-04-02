package br.com.uniliva.ucontas.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.business.SearchZipCodeBusiness;
import br.com.uniliva.ucontas.dto.AddressDto;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.handlers.CustomExceptionHandler;
import br.com.uniliva.ucontas.handlers.StandardError;

@RunWith(MockitoJUnitRunner.class)
public class SearchZipCodeContollerTest {

	private static final String URL = "/v1/zip-code";

	@InjectMocks
	private SearchZipCodeController controller;

	@Mock
	private SearchZipCodeBusiness business;

	private MockMvc mock;

	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new CustomExceptionHandler()).build();
	}

	@BeforeClass
	public static void configurar() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}


	@Test
	public void shouldFindAddressByZipCode() throws Exception {
		final AddressDto fixture = Fixture.from(AddressDto.class).gimme("valid");
		final String jsonResponse = new ObjectMapper().writeValueAsString(fixture);

		when(business.findAddressByZipCode(anyString())).thenReturn(fixture);

		mock.perform(get(URL + "/06250310").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}

	@Test
	public void shouldResturnBadRequestWhenFindZipCodeNonExistBuscarProdutoInexistent() throws Exception {
		final StandardError standardError = new StandardError(400, "Zip code invalid!!");
		final String jsonResponse = new ObjectMapper().writeValueAsString(standardError);

		when(business.findAddressByZipCode(anyString())).thenThrow(new BusinessException("Zip code invalid!!"));

		mock.perform(get(URL + "/10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}
}