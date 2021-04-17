package br.com.banco.dsweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.banco.dsweb.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NU_ACCOUNT", nullable = false)
	private String accountNumber;
	
	@Column(name = "NU_AGENCY", nullable = false)
	private String agency;
	
	@Column(name = "BALANCE")
	private Double balance;
	
	@Column(name = "TypeAccount", nullable = false)
	private TypeAccount typeAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "ID_CLIENT"))
	private Client client;
	
}
