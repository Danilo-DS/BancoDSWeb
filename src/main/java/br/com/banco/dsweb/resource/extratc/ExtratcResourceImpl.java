package br.com.banco.dsweb.resource.extratc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dsweb.dto.extratc.ConsultaExtratcDTO;
import br.com.banco.dsweb.service.extratc.ExtratcService;
import io.swagger.annotations.Api;

@Api("Extratc")
@RestController
@RequestMapping(value = "/api/v1/extratc")
public class ExtratcResourceImpl implements ExtratcResource {

	@Autowired
	private ExtratcService extratcService;
	
	@PostMapping
	@Override
	public ResponseEntity<?> extratcAccount(@RequestBody ConsultaExtratcDTO consultaExtratoDTO) {
		return ResponseEntity.ok(extratcService.listExtractAccount(consultaExtratoDTO));
	}
	
	

}
