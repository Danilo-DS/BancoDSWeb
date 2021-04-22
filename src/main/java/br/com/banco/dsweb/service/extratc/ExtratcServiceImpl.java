package br.com.banco.dsweb.service.extratc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.dsweb.config.ModelConvert;
import br.com.banco.dsweb.domain.extratc.Extratc;
import br.com.banco.dsweb.dto.extratc.ConsultaExtratcDTO;
import br.com.banco.dsweb.dto.extratc.ExtratcDTO;
import br.com.banco.dsweb.repository.ExtratcRepository;
import br.com.banco.dsweb.service.account.AccountService;
import br.com.banco.dsweb.service.agency.AgencyService;

@Service
public class ExtratcServiceImpl implements ExtratcService {
	
	@Autowired
	private ExtratcRepository extratcRespository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private AgencyService agencyService;
	
	@Transactional(readOnly = true)
	@Override
	public List<ExtratcDTO> listExtractAccount(ConsultaExtratcDTO consultaExtractDTO) {
		return toListDTO(extratcRespository.findByAccount(accountService.findByAccountAgency(consultaExtractDTO.getNumberAccount(), agencyService.findAgency(consultaExtractDTO.getNumberAgency()))));
	}
	
	@Transactional
	@Override
	public void saveExtratc(Extratc extract) {
		extratcRespository.save(extract);	
	}
	
	private List<ExtratcDTO> toListDTO(List<Extratc> extratcs) {
		return extratcs.stream().map(e -> ModelConvert.convertDto().map(e, ExtratcDTO.class)).collect(Collectors.toList());
	}
	
}
