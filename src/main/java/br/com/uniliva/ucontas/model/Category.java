package br.com.uniliva.ucontas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="categorySeq", sequenceName="SEQ_CF_COD_CATEGORY", allocationSize=1)
@Entity(name = "TB_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="categorySeq")
	@Column(name = "COD_CATEGORY")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

}