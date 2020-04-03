package br.com.uniliva.ucontas.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonAlias("cep")
	private String zipCode;
	@JsonAlias("logradouro")
	private String street;
	@JsonAlias("complemento")
	private String complement;
	@JsonAlias("bairro")
	private String neighborhood;
	@JsonAlias("localidade")
	private String locality;
	@JsonAlias("uf")
	private String uf;
	
}
