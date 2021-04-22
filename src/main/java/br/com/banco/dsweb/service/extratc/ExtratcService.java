package br.com.banco.dsweb.service.extratc;

import java.util.List;

import br.com.banco.dsweb.domain.extratc.Extratc;
import br.com.banco.dsweb.dto.extratc.ConsultaExtratcDTO;
import br.com.banco.dsweb.dto.extratc.ExtratcDTO;

public interface ExtratcService {
	
	List<ExtratcDTO> listExtractAccount(ConsultaExtratcDTO consultaExtractDTO);
	
	void saveExtratc(Extratc extract);
	
}
