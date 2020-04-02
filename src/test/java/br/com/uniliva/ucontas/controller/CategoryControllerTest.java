package br.com.uniliva.ucontas.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.business.CategoryBusiness;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.exception.NotFoundException;
import br.com.uniliva.ucontas.handlers.CustomExceptionHandler;
import br.com.uniliva.ucontas.handlers.StandardError;
import br.com.uniliva.ucontas.model.Category;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

	private static final String URL = "/v1/categories";

	@InjectMocks
	private CategoryController controller;

	@Mock
	private CategoryBusiness business;

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
	public void shouldReturnAllCategorys() throws Exception {
		final List<Category> fixture = Fixture.from(Category.class).gimme(5, "valid");
		final String jsonResponse = mapper.writeValueAsString(fixture);

		when(business.listAll()).thenReturn(fixture);

		mock.perform(get(URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}

	@Test
	public void shouldReturnCategoryWhenFindById() throws Exception {
		final Category fixture = Fixture.from(Category.class).gimme("valid");
		final String jsonResponse = mapper.writeValueAsString(fixture);

		when(business.findById(any(Long.class))).thenReturn(fixture);

		mock.perform(get(URL + "/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}

	@Test
	public void shouldReturnBadRequestWhenFindCategoryNotExist() throws Exception {
		final StandardError standardError = new StandardError(400, "Category not Found");
		final String jsonResponse = mapper.writeValueAsString(standardError);

		when(business.findById(any(Long.class))).thenThrow(new NotFoundException("Category not Found"));

		mock.perform(get(URL + "/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andDo(print()).andExpect(MockMvcResultMatchers.content().string(jsonResponse));
	}

	@Test
	public void shouldSaveCategory() throws Exception {
		final Category fixture = Fixture.from(Category.class).gimme("valid");
		final String body = mapper.writeValueAsString(fixture);

		when(business.save(any(Category.class))).thenReturn(fixture);

		mock.perform(post(URL).content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andDo(print()).andExpect(header().string("Location", containsString(URL + "/" + fixture.getId())));
	}

	@Test
	public void shouldReturnBadRequestWhenTrySaveInvalidCategory() throws Exception {
		final Category fixture = Fixture.from(Category.class).gimme("invalid");
		final String body = mapper.writeValueAsString(fixture);

		mock.perform(post(URL).content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Validation error")));
	}

	@Test
	public void shouldDeleteCategory() throws Exception {
		final Long codigo = 10L;

		doNothing().when(business).delete(any(Long.class));

		mock.perform(delete(URL + "/" + codigo).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturnBadRequestWhenTryDeleteCategoryNonExistent() throws Exception {
		final StandardError standardError = new StandardError(400, "Category not found");
		final Long codigo = 120L;
		final String retorno = mapper.writeValueAsString(standardError);

		doThrow(new NotFoundException("Category not found")).when(business).delete(any(Long.class));

		mock.perform(delete(URL + "/" + codigo).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(retorno));

	}

	@Test
	public void shouldReturnExceptionWhenDeleteCategoryInUse() throws Exception {
		final StandardError standardError = new StandardError(400, "You cannot delete a category being used");
		final Long codigo = 1L;
		final String retorno = mapper.writeValueAsString(standardError);

		doThrow(new BusinessException("You cannot delete a category being used")).when(business)
				.delete(any(Long.class));

		mock.perform(delete(URL + "/" + codigo).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError()).andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(retorno));

	}

	@Test
	public void shouldUpdateCategory() throws Exception {

		final Category fixture = Fixture.from(Category.class).gimme("valid");
		final String body = mapper.writeValueAsString(fixture);

		when(business.update(any(Category.class))).thenReturn(fixture);

		mock.perform(put(URL).content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(MockMvcResultMatchers.content().string(body));
	}

}