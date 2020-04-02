package br.com.uniliva.ucontas.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.model.Category;
import br.com.uniliva.ucontas.repository.CategoryRepository;
import br.com.uniliva.ucontas.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService service;

	@Mock
	private CategoryRepository repo;
	
	@BeforeClass
	public static void configurar() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}
	
	
	 @Test
	 public void shouldReturnAllCategories() {
		 final List<Category> categoriesFixture = Fixture.from(Category.class).gimme(5, "valid");
		 
		 when(repo.findAll()).thenReturn(categoriesFixture);
		 
		 List<Category> result = service.listAll();
		 
		 assertEquals(categoriesFixture, result);
		 assertEquals(categoriesFixture.size(), result.size());
		 
		 verify(repo, timeout(1)).findAll();		 
	 }
	 
	 @Test
	 public void shouldReturnCategoryByID() {
		 final Category categoryFixture = Fixture.from(Category.class).gimme("valid");
		 
		 when(repo.findById(any(Long.class))).thenReturn(Optional.of(categoryFixture));
		 
		 Optional<Category> result = service.findById(1L);
		 
		 assertTrue(result.isPresent());
		 assertEquals(categoryFixture, result.get());
		 assertEquals(categoryFixture.getId(), result.get().getId());
		 
		 verify(repo, timeout(1)).findById(any(Long.class));	 
	 }
	 
	 @Test
	 public void shouldSaveCategory() {
		 final Category categoryFixture = Fixture.from(Category.class).gimme("noSaved");
		 
		 when(repo.save(any(Category.class))).thenReturn(categoryFixture);
		 
		 service.save(categoryFixture);
		 
		 verify(repo, timeout(1)).save(any(Category.class));	 
	 }
	 
	 @Test
	 public void shouldDeleteCategoryByID() {
		 final Category categoryFixture = Fixture.from(Category.class).gimme("valid");
		 
		 doNothing().when(repo).deleteById(any(Long.class));
		 
		 service.deleteById(categoryFixture.getId());
		 
		 verify(repo, timeout(1)).deleteById(any(Long.class));
	 }
	
}
