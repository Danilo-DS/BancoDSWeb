package br.com.banco.dsweb.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.Extratc;

@Repository
public interface ExtratcRepository extends BaseRepository<Extratc, Long>{
	
	List<Extratc> findByOriginAccountAndOriginAgency(String originAccount, String originAgency);
}
