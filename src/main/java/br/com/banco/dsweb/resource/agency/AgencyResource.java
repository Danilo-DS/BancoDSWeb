package br.com.banco.dsweb.resource.agency;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.agency.AgencyDetailsDTO;
import br.com.banco.dsweb.dto.agency.AgencyRequestDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface AgencyResource {
	
	@ApiOperation(value = "Return List Agency")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List Account Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> listAll();
	
	@ApiOperation(value = "Find Agency by Number")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Agency Found Success"),
			@ApiResponse(code = 404, message = "Agency Not Found"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> findAgency(String numberAgency);
	
	@ApiOperation(value = "Save Agency")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Save Agency Success"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	AgencyDetailsDTO createAgency(AgencyRequestDTO agencyResquestDTO);
	
	@ApiOperation(value = "Update Agency")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update Account Success"),
			@ApiResponse(code = 400, message = "Agency Not Found, Impossible Update"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> updatedAgency(String numberAgency, AgencyRequestDTO agencyRequestDTO);
	
	@ApiOperation(value = "Delete Agency")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete Agency Success"),
			@ApiResponse(code = 400, message = "Agency Not Found, Impossible Delete"),
			@ApiResponse(code = 500, message = "Unexpected Error")
	})
	ResponseEntity<?> deleteAgency(Long id);
}
