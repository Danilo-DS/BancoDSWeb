package br.com.banco.dsweb.service;

import java.util.List;

import br.com.banco.dsweb.domain.Client;
import br.com.banco.dsweb.dto.ClientDTO;

public interface ClientService {
	
	List<ClientDTO> listAll();
	
	Client findClientById(Long id);
	
	void saveClient(Client client);
	
	Client updateClient(Client client, Long id);
	
	void deleteClient(Long id);
}
