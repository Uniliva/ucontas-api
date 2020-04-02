package br.com.uniliva.ucontas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CATEGORIAS")
public class Categoria {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_CATEGORIA")
    private String codigo;

    @NotNull(message="Campo Obrigatorio!")
    private String nome;

}