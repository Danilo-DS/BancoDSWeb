package br.com.banco.dsweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NU_ACCOUNT")
	private String accountNumber;
	
	@Column(name = "NU_AGENCY")
	private String agency;
	
	@Column(name = "BALANCE")
	private Double balance;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "ID_CLIENT"))
	private Client client;
	
}
