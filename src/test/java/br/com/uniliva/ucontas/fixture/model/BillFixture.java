package br.com.uniliva.ucontas.fixture.model;

import java.time.LocalDate;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.uniliva.ucontas.model.Category;
import br.com.uniliva.ucontas.model.Bill;

public class BillFixture implements TemplateLoader{


	@Override
	public void load() {
		Fixture.of(Bill.class).addTemplate("valid", new Rule() {
			{
				add("id", random(Long.class, range(1L, 500L)));
				add("description", random("Christmas shopping", "Weekly fair", "Barbecue with friends"));
				add("value", random(Double.class, range(1L, 500L)) );
				add("category", one(Category.class, "valid"));
				add("dateBill", LocalDate.now().minusDays(2));
			}

		}).addTemplate("invalid", new Rule() {
			{
				add("id", random(Long.class, range(1L, 500L)));
				add("description",  random("Christmas shopping", "Weekly fair", "Barbecue with friends"));
				add("value", 0D);
				add("category", one(Category.class, "valid"));
				add("dateBill", null);
			}

		}).addTemplate("noSaved", new Rule() {
			{
				add("id", null);
				add("description", random("Christmas shopping", "Weekly fair", "Barbecue with friends"));
				add("value", random(Double.class, range(1L, 500L)) );
				add("category", one(Category.class, "noSaved"));
				add("dateBill", LocalDate.now().minusDays(2));
			}

		});
	}
}