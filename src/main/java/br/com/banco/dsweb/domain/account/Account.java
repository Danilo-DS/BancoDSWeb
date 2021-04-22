package br.com.banco.dsweb.domain.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.domain.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_ACCOUNT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_ACCOUNT", discriminatorType = DiscriminatorType.STRING, length = 2)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "NU_ACCOUNT", nullable = false)
	private String accountNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AGENCY_COD", foreignKey = @ForeignKey(name = "AGENCY_COD"))
	private Agency agency;
	
	@Column(name = "BALANCE", insertable = false, updatable = false, columnDefinition = "DECIMAL(10,2) default 0")
	private Double balance;
	
	@Column(name = "TYPE_ACCOUNT", nullable = false, insertable = false, updatable = false, length =  2)
	private String typeAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_COD", foreignKey = @ForeignKey(name = "CLIENT_COD"))
	private Client client;
	
}
