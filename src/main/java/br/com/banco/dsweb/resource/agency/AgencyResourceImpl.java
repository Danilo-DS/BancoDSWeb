package br.com.banco.dsweb.resource.agency;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.agency.AgencyDetailsDTO;
import br.com.banco.dsweb.dto.agency.AgencyRequestDTO;
import br.com.banco.dsweb.service.agency.AgencyService;
import io.swagger.annotations.Api;

@Api("Agency")
@RestController
@RequestMapping(value = "/api/v1/agency")
public class AgencyResourceImpl implements AgencyResource {
	
	@Autowired
	private AgencyService agencyService;
	
	@GetMapping
	@Override
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(agencyService.ListAgency());
	}
	
	@GetMapping(value = "/{numberAgency}")
	@Override
	public ResponseEntity<?> findAgency(@PathVariable String numberAgency) {
		return ResponseEntity.ok(agencyService.findAgency(numberAgency));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	@Override
	public AgencyDetailsDTO createAgency(@RequestBody AgencyRequestDTO agencyRequestDTO) {
		return agencyService.saveAgency(agencyRequestDTO);
	}
	
	@PutMapping(value = "/{numberAgency}")
	@Override
	public ResponseEntity<?> updatedAgency(@PathVariable String numberAgency, @RequestBody AgencyRequestDTO agencyRequestDTO) {
		return ResponseEntity.ok(agencyService.updateAgency(numberAgency, agencyRequestDTO));
	}

	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> deleteAgency(@PathVariable Long id) {
		agencyService.deleteAgency(id);
		return ResponseEntity.noContent().build();
	}

}
