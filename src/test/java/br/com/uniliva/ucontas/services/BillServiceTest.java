package br.com.uniliva.ucontas.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import br.com.uniliva.ucontas.model.Bill;
import br.com.uniliva.ucontas.repository.BillRepository;
import br.com.uniliva.ucontas.service.BillService;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

	@InjectMocks
	private BillService service;

	@Mock
	private BillRepository repo;
	
	@BeforeClass
	public static void configurar() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}
	
	
	 @Test
	 public void shouldReturnAllCategories() {
		 final List<Bill> billsFixture = Fixture.from(Bill.class).gimme(5, "valid");
		 
		 when(repo.findAll()).thenReturn(billsFixture);
		 
		 List<Bill> result = service.listAll();
		 
		 assertEquals(billsFixture, result);
		 assertEquals(billsFixture.size(), result.size());
		 
		 verify(repo, timeout(1)).findAll();		 
	 }
	 
	 @Test
	 public void shouldReturnBillByID() {
		 final Bill billFixture = Fixture.from(Bill.class).gimme("valid");
		 
		 when(repo.findById(any(Long.class))).thenReturn(Optional.of(billFixture));
		 
		 Optional<Bill> result = service.findById(1L);
		 
		 assertTrue(result.isPresent());
		 assertEquals(billFixture, result.get());
		 assertEquals(billFixture.getId(), result.get().getId());
		 
		 verify(repo, timeout(1)).findById(any(Long.class));	 
	 }
	 
	 @Test
	 public void shouldSaveBill() {
		 final Bill billFixture = Fixture.from(Bill.class).gimme("noSaved");
		 
		 when(repo.save(any(Bill.class))).thenReturn(billFixture);
		 
		 service.save(billFixture);
		 
		 verify(repo, timeout(1)).save(any(Bill.class));	 
	 }
	 
	 @Test
	 public void shouldDeleteByID() {
		 final Bill billFixture = Fixture.from(Bill.class).gimme("valid");
		 
		 doNothing().when(repo).deleteById(any(Long.class));
		 
		 service.deleteById(billFixture.getId());
		 
		 verify(repo, timeout(1)).deleteById(any(Long.class));
	 }
}
