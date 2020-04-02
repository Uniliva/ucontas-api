package br.com.uniliva.ucontas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.business.BillBusiness;
import br.com.uniliva.ucontas.handlers.CustomExceptionHandler;

@RunWith(MockitoJUnitRunner.class)
public class BillControllerTest {

    private static final String URL = "/v1/contas";

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

	

}