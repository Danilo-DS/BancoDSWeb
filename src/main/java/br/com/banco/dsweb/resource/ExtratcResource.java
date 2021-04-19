package br.com.banco.dsweb.resource;

import org.springframework.http.ResponseEntity;

import br.com.banco.dsweb.dto.ExtratcDTO;

public interface ExtratcResource {

	ResponseEntity<?> extratcAccount(ExtratcDTO extratcDTO);
}
