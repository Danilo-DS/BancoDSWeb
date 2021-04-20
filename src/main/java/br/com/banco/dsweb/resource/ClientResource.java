package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.domain.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Client")
public interface ClientResource {
	
	@ApiOperation(value = "List Client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List Client Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> allClient();
	
	@ApiOperation(value = "Find Client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Client Found Success"),
			@ApiResponse(code = 404, message = "Client Not Found Error"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> FindClientId(Long id);
	
	@ApiOperation(value = "Save Client")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Save Client Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	void saveClient(Client client);
	
	@ApiOperation(value = "Update Client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Client Update Success"),
			@ApiResponse(code = 400, message = "Client Not Found for Update"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> UpdateClient(Client client, Long id);
	
	@ApiOperation(value = "Delete Client")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Client Delete Success"),
			@ApiResponse(code = 400, message = "Client Not Found for Delete"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> DeleteClient(Long id);
}
