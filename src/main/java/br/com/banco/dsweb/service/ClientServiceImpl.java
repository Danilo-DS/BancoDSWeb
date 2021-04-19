package br.com.banco.dsweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.Client;
import br.com.banco.dsweb.dto.ClientDTO;
import br.com.banco.dsweb.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public List<ClientDTO> listAll() {
		return toListClientDTO(clientRepository.findAll());
	}

	@Override
	public Client findClientById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Mensagem"));
	}

	@Override
	public void saveClient(Client client) {
		saveUpdateClient(client);
	}

	@Override
	public Client updateClient(Client clientRequest, Long id) {
		if(isClientExisting(id)) {
			Client clientUpdated = fillClient(clientRepository.findById(id).get(), clientRequest);
			saveUpdateClient(clientUpdated);
			
			return clientUpdated;
		}
		else {
			throw new RuntimeException("Mensagem");
		}
	}

	@Override
	public void deleteClient(Long id) {
		if(isClientExisting(id)) {
			clientRepository.deleteById(id);
		}else {
			throw new RuntimeException("Mensagem");
		}
	}
	
	private List<ClientDTO> toListClientDTO(List<Client> client) {
		return client.stream().map(c -> ModelConvert.convertDto().map(c, ClientDTO.class)).collect(Collectors.toList());
	}
	
	private boolean isClientExisting(Long id) {
		if(clientRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private void saveUpdateClient(Client client) {
		clientRepository.save(client);
	}
	
	private Client fillClient(Client clientExisting, Client clientRequest) {
		clientExisting.setName(StringUtils.hasText(clientRequest.getName()) ? clientExisting.getName() : clientRequest.getName());
		clientExisting.setNuCpfCnpj(StringUtils.hasText(clientRequest.getNuCpfCnpj()) ? clientExisting.getNuCpfCnpj() : clientRequest.getNuCpfCnpj());
		clientExisting.setEmail(StringUtils.hasText(clientRequest.getEmail()) ? clientExisting.getEmail() : clientRequest.getEmail());
		clientExisting.setFone(StringUtils.hasText(clientRequest.getFone()) ? clientExisting.getFone() : clientRequest.getFone());
		clientExisting.setAddress(StringUtils.hasText(clientRequest.getAddress()) ? clientExisting.getAddress() : clientRequest.getAddress());
		
		return clientExisting;
	}
}
