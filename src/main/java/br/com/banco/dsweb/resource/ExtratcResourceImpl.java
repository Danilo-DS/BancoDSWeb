package br.com.banco.dsweb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.ExtratcDTO;
import br.com.banco.dsweb.service.ExtratcService;

@RestController
@RequestMapping(value = "/api/v1/extratc")
public class ExtratcResourceImpl implements ExtratcResource {

	@Autowired
	private ExtratcService extratcService;
	
	@PostMapping
	@Override
	public ResponseEntity<?> extratcAccount(ExtratcDTO extratcDTO) {
		return ResponseEntity.ok(extratcService.listExtractAccount(extratcDTO));
	}
	
	

}
