package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.domain.Client;

public interface ClientResource {
	
	ResponseEntity<?> allClient();
	ResponseEntity<?> FindClientId(Long id);
	void SaveClient(Client client);
	ResponseEntity<?> UpdateClient(Client client, Long id);
	ResponseEntity<?> DeleteClient(Long id);
}
