package br.com.banco.dsweb.service.agency;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.dto.agency.AgencyDetailsDTO;
import br.com.banco.dsweb.dto.agency.AgencyRequestDTO;
import br.com.banco.dsweb.exception.agency.AgencyException;
import br.com.banco.dsweb.repository.AccountRepository;
import br.com.banco.dsweb.repository.AgencyRepository;
import br.com.banco.dsweb.util.ConstantUtil;

@Service
public class AgencyServiceImpl implements AgencyService {
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<AgencyDetailsDTO> ListAgency() {
		return toListDTO(agencyRepository.findAll());
	}

	@Override
	public Agency findAgency(String numberAgency) {
		return agencyRepository.findByNumberAgency(numberAgency).orElseThrow(() -> new AgencyException(ConstantUtil.AGENCY_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public AgencyDetailsDTO saveAgency(AgencyRequestDTO agencyRequest) {
		Agency agency = Agency.builder(agencyRequest);
		agencyRepository.save(agency);
		saveUpdateAgency(agency);
		return toDTO(agency);
	}
	
	@Override
	public AgencyDetailsDTO updateAgency(String numberAgency, AgencyRequestDTO agencyRequest) {
		
		Agency agency = findAgency(agencyRequest.getNameAgency());
		agency.setNameAgency(StringUtils.hasText(agencyRequest.getNameAgency()) ? agencyRequest.getNameAgency() : agency.getNameAgency() );
		agency.setLocality(StringUtils.hasText(agencyRequest.getLocality()) ? agencyRequest.getLocality() : agency.getLocality());
		
		saveUpdateAgency(agency);
		
		return toDTO(agency);
	}

	@Override
	public void deleteAgency(Long id) {
		
		if(isExistingAgency(id)) {
			if(accountRepository.existsByAgency(agencyRepository.getOne(id))) {
				throw new AgencyException(ConstantUtil.AGENCY_DELETE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}
			agencyRepository.deleteById(id);
		}
	}
	
	@Transactional
	private void saveUpdateAgency(Agency agency) {
		agencyRepository.save(agency);
	}
	
	private boolean isExistingAgency(Long id) {
		return agencyRepository.existsById(id);
	}
	
	private List<AgencyDetailsDTO> toListDTO(List<Agency> agency){
		return agency.stream().map(a -> ModelConvert.convertDto().map(a, AgencyDetailsDTO.class)).collect(Collectors.toList());
	}
	
	private AgencyDetailsDTO toDTO(Agency agency) {
		return ModelConvert.convertDto().map(agency, AgencyDetailsDTO.class);		
	}
	
}
