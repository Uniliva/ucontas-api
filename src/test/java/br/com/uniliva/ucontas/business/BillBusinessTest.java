package br.com.uniliva.ucontas.business;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.uniliva.ucontas.exception.NotFoundException;
import br.com.uniliva.ucontas.model.Bill;
import br.com.uniliva.ucontas.service.BillService;

@RunWith(MockitoJUnitRunner.class)
public class BillBusinessTest {


	@InjectMocks
	private BillBusiness business;

	@Mock
	private BillService service;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void configure() {
		FixtureFactoryLoader.loadTemplates("br.com.uniliva.ucontas.fixture");
	}
	
	@Test
	public void shouldReturnAllCategories() {
		final List<Bill> BillsFixture = Fixture.from(Bill.class).gimme(5, "valid");

		when(service.listAll()).thenReturn(BillsFixture);

		List<Bill> result = business.listAll();

		assertEquals(BillsFixture, result);
		assertEquals(BillsFixture.size(), result.size());

		verify(service, timeout(1)).listAll();

	}
	
	@Test
	public void shouldReturnBillByID() {
		final Bill BillFixture = Fixture.from(Bill.class).gimme("valid");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(BillFixture));

		Bill result = business.findById(1L);

		assertNotNull(result);
		assertEquals(BillFixture, result);

		verify(service, timeout(1)).findById(any(Long.class));
	}

	@Test
	public void shouldReturnExceptionWhenFindBillNotExist() {

		expectedException.expect(NotFoundException.class);
		expectedException.expectMessage("Bill not found");

		when(service.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

		business.findById(1L);

		verify(service, timeout(1)).findById(any(Long.class));
	}

	@Test
	public void shouldSaveBill() {
		final Bill BillFixture = Fixture.from(Bill.class).gimme("valid");

		when(service.save(any(Bill.class))).thenReturn(BillFixture);

		Bill result = business.save(BillFixture);

		assertEquals(BillFixture, result);
		verify(service, timeout(1)).save(any(Bill.class));
	}

	@Test
	public void shouldDeleteBillByID() {
		final Bill BillFixture = Fixture.from(Bill.class).gimme("valid");

		when(service.findById(any(Long.class))).thenReturn(Optional.of(BillFixture));
		doNothing().when(service).deleteById(any(Long.class));

		business.delete(BillFixture.getId());

		verify(service, timeout(1)).deleteById(any(Long.class));
	}
	
	
	@Test
	public void shouldUpdateBill() {
		final Bill BillFixture = Fixture.from(Bill.class).gimme("valid");
		final Bill BillUpdateFixture = new Bill(BillFixture.getId(), BillFixture.getDescription() + " updated", 200L, BillFixture.getCategory(), LocalDate.now());

		when(service.findById(any(Long.class))).thenReturn(Optional.of(BillFixture));
		when(service.save(any(Bill.class))).thenReturn(BillUpdateFixture);

		Bill result = business.update(BillFixture);

		assertEquals(BillUpdateFixture, result);
		assertNotEquals(BillFixture.getValue(), result.getValue());
		
		verify(service, timeout(1)).save(any(Bill.class));
		verify(service, timeout(1)).findById(any(Long.class));
	}

}

