package br.com.banco.dsweb.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.agency.Agency;

@Repository
public interface AgencyRepository extends BaseRepository<Agency, Long>{

	Optional<Agency> findByNumberAgency(String numberAgency);
	
}	
