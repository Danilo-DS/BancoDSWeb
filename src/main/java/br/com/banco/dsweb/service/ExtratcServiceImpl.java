package br.com.banco.dsweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.dsweb.domain.Extratc;
import br.com.banco.dsweb.dto.ExtratcDTO;
import br.com.banco.dsweb.repository.ExtratcRepository;

@Service
public class ExtratcServiceImpl implements ExtratcService {
	
	@Autowired
	private ExtratcRepository extratcRespository;
	
	@Override
	public List<Extratc> listExtractAccount(ExtratcDTO extratcDTO) {
		return extratcRespository.findByOriginAccountAndOriginAgency(extratcDTO.getNumberAccount(), extratcDTO.getNumberAgency());
	}

	@Override
	public void saveExtratc(Extratc extract) {
		extratcRespository.save(extract);	
	}

}
