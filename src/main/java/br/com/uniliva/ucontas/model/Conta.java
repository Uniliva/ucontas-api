package br.com.uniliva.ucontas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTA")
public class Conta {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_CONTA")
    private String codigo;

	@NotNull(message="Campo Obrigatorio!")
    private String descricao;

    @NotNull(message="Campo Obrigatorio!")
    private double valor;

    @OneToMany
    @JoinColumn(name = "codigo")
    @NotNull(message="Campo Obrigatorio!")
    private Categoria categoria;


    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message="Campo Obrigatorio!")
    private LocalDate data;

}