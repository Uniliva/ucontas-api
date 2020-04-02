package br.com.uniliva.ucontas.fixture.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.uniliva.ucontas.model.Category;

public class CategoryFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Category.class).addTemplate("valid", new Rule() {
			{
				add("id", random(Long.class, range(1L, 500L)));
				add("name", random("Rent", "Education", "Recreation", "Food"));
			}

		}).addTemplate("invalid", new Rule() {
			{
				add("id", null);
				add("name", null);
			}

		}).addTemplate("noSaved", new Rule() {
			{
				add("id", random(Long.class, range(1L, 500L)));
				add("name", random("Rent", "Education", "Recreation", "Food"));
			}

		});
	}
}