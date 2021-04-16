package br.com.banco.dsweb.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.banco.dsweb.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_EXTRATC")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Extratc implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "DATE_TRANSACTION", nullable = false)
	private LocalDate dateTransaction;
	
	@Column(name = "TYPE_ACCOUNT", nullable = false)
	private TypeAccount typeAccount;
	
	@Column(name = "VALUE_TRANSACTION", nullable = false)
	private Double valueTransaction;
	
	@Column(name = "ORIGEM_ACCOUNT", length = 10, nullable = false)
	private String origemAccount;
	
	@Column(name = "DESTINATION_ACCOUNT", length = 10, nullable = false)
	private String destinationAccount;
}
