package br.com.uniliva.ucontas.fixture.model.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.uniliva.ucontas.model.Categoria;

public class CategoriaFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Categoria.class).addTemplate("valida", new Rule() {
			{
				add("codigo", random(Long.class, range(1L, 500L)));
				add("descricao", random("Aluguel", "Educação", "Lazer", "Alimentação"));
			}

		}).addTemplate("invalida", new Rule() {
			{
				add("codigo", null);
				add("descricao", null);
			}

		});
	}
}