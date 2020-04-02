package br.com.uniliva.ucontas.fixture.dto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.uniliva.ucontas.dto.AddressDto;

public class EnderecoDTOFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(AddressDto.class).addTemplate("valid", new Rule() {
			{
				add("zipCode", random("06608000", "17720000"));
				add("street", random("Avenida Paulista", "Rua Ibirapuera"));
				add("complement", "Casa 10");
				add("neighborhood", "Helena Maria");
				add("locality", "Osasco");
				add("uf", "SP");
			}

		}).addTemplate("invalid", new Rule() {
			{
				add("zipCode", null);
				add("street", null);
				add("complement", null);
				add("neighborhood",null);
				add("locality", null);
				add("uf", null);
			}

		});
	}
}

