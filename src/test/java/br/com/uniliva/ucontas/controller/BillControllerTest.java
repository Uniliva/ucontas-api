package br.com.uniliva.ucontas.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.business.BillBusiness;
import br.com.uniliva.ucontas.handlers.CustomExceptionHandler;
import br.com.uniliva.ucontas.model.Bill;

@RunWith(MockitoJUnitRunner.class)
public class BillControllerTest {

    private static final String URL = "/v1/bills";

    @InjectMocks
	private BillController controller;

	@Mock
	private BillBusiness business;

	private MockMvc mock;
	
    private ObjectMapper mapper;
    
    @Before
	public void configure() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new CustomExceptionHandler()).build();
		mapper = new ObjectMapper();
	    mapper.registerModule(new ParameterNamesModule());
	    mapper.registerModule(new Jdk8Module());
	    mapper.registerModule(new JavaTimeModule());
	}

	@BeforeClass
	public static void configureFixture() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}

	@Test
	public void shouldReturnAllBills() throws Exception {
		final List<Bill> fixture = Fixture.from(Bill.class).gimme(5, "valid");
		final String jsonResponse = mapper.writeValueAsString(fixture);

		when(business.listAllBills()).thenReturn(fixture);

		mock.perform(get(URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}

}