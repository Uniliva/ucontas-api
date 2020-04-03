package br.com.uniliva.ucontas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(name="billSeq", sequenceName="SEQ_CF_COD_BILL", allocationSize=1)
@Entity(name = "TB_BILL")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="billSeq")
	@Column(name = "COD_BILL")
    private Long id;

	@NotNull
	@Column(name = "DESCRIPTION")
    private String description;

    @NotNull
	@Column(name = "VALUE")
    private double value;

    @OneToOne
    @JoinColumn(name = "CATEGORY")
    @NotNull
    private Category category;


    @Column(name = "DATE_BILL", columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate dateBill;

}