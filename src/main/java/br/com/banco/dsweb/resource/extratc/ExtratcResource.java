package br.com.banco.dsweb.resource.extratc;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.extratc.ConsultaExtratcDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface ExtratcResource {

	@ApiOperation("Extratc Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Extract Success"),
			@ApiResponse(code = 404, message = "Extratc Account Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> extratcAccount(ConsultaExtratcDTO consultaExtratoDTO);
}
