package br.com.banco.dsweb.service.agency;

import java.util.List;

import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.dto.agency.AgencyDetailsDTO;
import br.com.banco.dsweb.dto.agency.AgencyRequestDTO;

public interface AgencyService {
	
	List<AgencyDetailsDTO> ListAgency();
	Agency findAgency(String numberAgency);
	AgencyDetailsDTO saveAgency(AgencyRequestDTO agencyRequest);
	AgencyDetailsDTO updateAgency(String numberAgency, AgencyRequestDTO agencyRequest);
	void deleteAgency(Long id);
	
	
}
