package br.com.uniliva.ucontas.fixture.model.model;

import java.time.LocalDate;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.uniliva.ucontas.model.Categoria;
import br.com.uniliva.ucontas.model.Conta;

public class ContaFixture implements TemplateLoader{

	@Override
	public void load() {
		Fixture.of(Conta.class).addTemplate("valida", new Rule() {
			{
				add("codigo", random(Long.class, range(1L, 500L)));
				add("descricao", random("Compras de natal", "Feira semanal", "Churrasco com os amigos"));
				add("valor", random(Double.class, range(1L, 500L)) );
				add("categoria", one(Categoria.class, "valida"));
				add("data", LocalDate.now().minusDays(2));
			}

		}).addTemplate("invalida", new Rule() {
			{
				add("codigo", random(Long.class, range(1L, 500L)));
				add("descricao", random("Compras de natal", "Feira semanal", "Churrasco com os amigos"));
				add("valor", null);
				add("categoria", one(Categoria.class, "valida"));
				add("data", null);
			}

		});
	}
}