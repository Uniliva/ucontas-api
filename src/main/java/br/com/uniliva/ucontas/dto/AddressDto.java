package br.com.uniliva.ucontas.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String zipCode;
	private String street;
	private String complement;
	private String neighborhood;
	private String locality;
	private String uf;
	
}
