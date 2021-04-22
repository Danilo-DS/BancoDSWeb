package br.com.banco.dsweb.service.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.client.Client;
import br.com.banco.dsweb.dto.client.ClientDTO;
import br.com.banco.dsweb.exception.client.ClientException;
import br.com.banco.dsweb.repository.ClientRepository;
import br.com.banco.dsweb.util.ConstantUtil;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<ClientDTO> listAll() {
		return toListClientDTO(clientRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	@Override
	public Client findClientById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new ClientException(ConstantUtil.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public void saveClient(Client client) {
		saveUpdateClient(client);
	}
	
	@Override
	public Client updateClient(Client client, Long id) {
		if(isClientExisting(id)) {
			Client clientUpdated = fillClient(clientRepository.findById(id).get(), client);
			saveUpdateClient(clientUpdated);
			
			return clientUpdated;
		}
		else {
			throw new ClientException(ConstantUtil.CLIENT_UPDATE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@Override
	public void deleteClient(Long id) {
		if(isClientExisting(id)) {
			clientRepository.deleteById(id);
		}else {
			throw new ClientException(ConstantUtil.CLIENT_DELETE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}
	
	private List<ClientDTO> toListClientDTO(List<Client> client) {
		return client.stream().map(c -> ModelConvert.convertDto().map(c, ClientDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	private boolean isClientExisting(Long id) {
		if(clientRepository.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Transactional
	private void saveUpdateClient(Client client) {
		clientRepository.save(client);
	}
	
	private Client fillClient(Client clientExisting, Client clientRequest) {
		clientExisting.setName(StringUtils.hasText(clientRequest.getName()) ? clientRequest.getName() : clientExisting.getName());
		clientExisting.setNuCpfCnpj(StringUtils.hasText(clientRequest.getNuCpfCnpj()) ? clientRequest.getNuCpfCnpj() : clientExisting.getNuCpfCnpj());
		clientExisting.setEmail(StringUtils.hasText(clientRequest.getEmail()) ? clientRequest.getEmail() : clientExisting.getEmail());
		clientExisting.setFone(StringUtils.hasText(clientRequest.getFone()) ? clientRequest.getFone() : clientExisting.getFone());
		clientExisting.setAddress(StringUtils.hasText(clientRequest.getAddress()) ? clientRequest.getAddress() : clientExisting.getAddress());
		
		return clientExisting;
	}
}
