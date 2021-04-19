package br.com.banco.dsweb.service;

import java.util.List;

import br.com.banco.dsweb.domain.Extratc;
import br.com.banco.dsweb.dto.ExtratcDTO;

public interface ExtratcService {
	
	List<Extratc> listExtractAccount(ExtratcDTO extratcDTO);
	
	void saveExtratc(Extratc extract);
	
}
