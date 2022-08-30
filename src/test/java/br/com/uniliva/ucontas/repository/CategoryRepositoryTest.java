package br.com.uniliva.ucontas.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repo;

	@BeforeClass
	public static void configureFixture() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}

	private final Integer QTD_MODEL_SALVE = 5;

	@BeforeEach
	public void configureDB() {
		final List<Category> fixture = Fixture.from(Category.class).gimme(QTD_MODEL_SALVE, "noSaved");
		repo.saveAll(fixture);
	}

	@AfterEach
	public void cleanDB() {
		repo.deleteAll();
	}

	@Test
	public void shouldListAllCategories() {

		List<Category> categories = repo.findAll();

		assertEquals(QTD_MODEL_SALVE, categories.size());
	}
	
	@Test
	public void shouldSaveCategory() {
		
		Category fixture = Fixture.from(Category.class).gimme("noSaved");

		Category categorySave = repo.save(fixture);

		assertNotNull(categorySave.getId());
		assertEquals(fixture.getName(), categorySave.getName());
	}

	/**
	 * Classe de teste apenas para demostração, não estou usando nenhum recurso
	 * adicionado por mim no repository, sendo assim sendo desnecessário testa-lo
	 */

}