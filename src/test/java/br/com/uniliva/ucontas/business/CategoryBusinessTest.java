package br.com.uniliva.ucontas.business;


import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.exception.BusinessException;
import br.com.uniliva.ucontas.exception.NotFoundException;
import br.com.uniliva.ucontas.model.Category;
import br.com.uniliva.ucontas.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryBusinessTest {


	@InjectMocks
	private CategoryBusiness business;

	@Mock
	private CategoryService service;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void configure() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}
	
	@Test
	public void shouldReturnAllCategories() {
		final List<Category> categorysFixture = Fixture.from(Category.class).gimme(5, "valid");

		when(service.listAll()).thenReturn(categorysFixture);

		List<Category> result = business.listAll();

		assertEquals(categorysFixture, result);
		assertEquals(categorysFixture.size(), result.size());

		verify(service, timeout(1)).listAll();

	}
	
	@Test
	public void shouldReturnCategoryByID() {
		final Category categoryFixture = Fixture.from(Category.class).gimme("valid");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(categoryFixture));

		Category result = business.findById(1L);

		assertNotNull(result);
		assertEquals(categoryFixture, result);
		assertEquals(categoryFixture.getId(), result.getId());

		verify(service, timeout(1)).findById(any(Long.class));
	}

	@Test
	public void shouldReturnExceptionWhenFindCategoryNotExist() {

		expectedException.expect(NotFoundException.class);
		expectedException.expectMessage("Category not found");

		when(service.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

		business.findById(1L);

		verify(service, timeout(1)).findById(any(Long.class));
	}

	@Test
	public void shouldSaveCategory() {
		final Category categoryFixture = Fixture.from(Category.class).gimme("valid");

		when(service.save(any(Category.class))).thenReturn(categoryFixture);

		Category result = business.save(categoryFixture);

		assertEquals(categoryFixture, result);
		verify(service, timeout(1)).save(any(Category.class));
	}

	@Test
	public void shouldDeleteCategoryByID() {
		final Category categoryFixture = Fixture.from(Category.class).gimme("valid");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(categoryFixture));
		doNothing().when(service).deleteById(any(Long.class));

		business.delete(categoryFixture.getId());

		verify(service, timeout(1)).deleteById(any(Long.class));
	}
	

	@Test
	public void shouldReturnExceptionWhenDeleteCategoryInUse() {
		final Category categoryFixture = Fixture.from(Category.class).gimme("valid");

		expectedException.expect(BusinessException.class);
		expectedException.expectMessage("You cannot delete a category being used");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(categoryFixture));

		doThrow(DataIntegrityViolationException.class).when(service).deleteById(any(Long.class));

		business.delete(categoryFixture.getId());

		verify(service, timeout(1)).deleteById(any(Long.class));
		verify(service, timeout(1)).findById(any(Long.class));
	}
	


	
	
	@Test
	public void shouldUpdateCategory() {
		final Category categoryFixture = Fixture.from(Category.class).gimme("valid");
		final Category categoryUpdateFixture = new Category(categoryFixture.getId(), categoryFixture.getName() + " updated");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(categoryFixture));
		when(service.save(any(Category.class))).thenReturn(categoryUpdateFixture);

		Category result = business.update(categoryFixture);

		assertEquals(categoryUpdateFixture, result);
		assertNotEquals(categoryFixture.getName(), result.getName());
		
		verify(service, timeout(1)).save(any(Category.class));
		verify(service, timeout(1)).findById(any(Long.class));
	}

}

