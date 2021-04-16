package br.com.banco.dsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	private Long id;
	private String name;
	private String  nuCpfCnpj;
	
}
