package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.ExtratcDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Extratc")
public interface ExtratcResource {

	@ApiOperation("Extratc Account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Extract Success"),
			@ApiResponse(code = 404, message = "Extratc Account Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> extratcAccount(ExtratcDTO extratcDTO);
}
