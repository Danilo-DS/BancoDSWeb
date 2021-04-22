package br.com.banco.dsweb.dto.client;

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
	private String email;
	
}
