package br.com.uniliva.ucontas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.model.Bill;
import br.com.uniliva.ucontas.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillRepositoryTest {

	@Autowired
	private BillRepository repo;

	@Autowired
	private CategoryRepository repoCategory;

	@BeforeClass
	public static void configureFixture() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}

	private final Integer QTD_MODEL_SALVE = 2;

	@Before
	public void configureDB() {
		final List<Category> categoriesFixture = Fixture.from(Category.class).gimme(QTD_MODEL_SALVE, "noSaved");
		List<Category> categoriesSaved = repoCategory.saveAll(categoriesFixture);

		final List<Bill> billFixture = Fixture.from(Bill.class).gimme(QTD_MODEL_SALVE, "noSaved");
		
		List<Bill> newBills = billFixture.stream().map(bill -> {
			bill.setCategory(categoriesSaved.get(0));
			return bill;
		}).collect(Collectors.toList());
		
		repo.saveAll(newBills);
	}

	@After
	public void cleanDB() {
		repo.deleteAll();
		repoCategory.deleteAll();
	}

	@Test
	public void shouldRemoveById() {
		Long codigoBill = 1L;
		repo.deleteById(codigoBill);
		
		long total = repo.count();

		assertEquals((QTD_MODEL_SALVE -1), total);
	}

	/**
	 * Classe de teste apenas para demostração, não estou usando nenhum recuso
	 * adicionado por mim no repository, sendo assim sendo desnecessário testa-lo
	 */

}