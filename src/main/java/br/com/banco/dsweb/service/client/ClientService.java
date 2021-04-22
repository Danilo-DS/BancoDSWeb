package br.com.banco.dsweb.service.client;

import java.util.List;

import br.com.banco.dsweb.domain.client.Client;
import br.com.banco.dsweb.dto.client.ClientDTO;

public interface ClientService {
	
	List<ClientDTO> listAll();
	
	Client findClientById(Long id);
	
	void saveClient(Client client);
	
	Client updateClient(Client client, Long id);
	
	void deleteClient(Long id);
}
