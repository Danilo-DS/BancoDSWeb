package br.com.banco.dsweb.domain.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_CLIENT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "NM_CLIENT", length = 60, nullable = false)
	private String name;
	
	@Column(name = "NU_CPF_CNPJ", length = 14, nullable = false)
	private String  nuCpfCnpj;
	
	@Column(name = "FONE", length = 12)
	private String fone;
	
	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;
	
	@Column(name = "EMAIL", length = 60, nullable = false)
	private String email;
}
