package br.com.banco.dsweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.domain.Client;
import br.com.banco.dsweb.service.ClientService;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientResourceImpl implements ClientResource{
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	@Override
	public ResponseEntity<?> allClient() {
		return ResponseEntity.ok(service.listAll());
	}

	@GetMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> FindClientId(@PathVariable Long id) {
		return ResponseEntity.ok(service.findClientById(id));
	}

	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@Override
	public void saveClient(Client client) {
		service.saveClient(client);
	}

	@PutMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> UpdateClient(@RequestBody Client client, @PathVariable Long id) {
		return ResponseEntity.ok(service.updateClient(client, id));
	}

	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> DeleteClient(Long id) {
		service.deleteClient(id);
		return ResponseEntity.noContent().build();
	}

}
