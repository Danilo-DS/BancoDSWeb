package br.com.banco.dsweb.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.account.Account;
import br.com.banco.dsweb.domain.extratc.Extratc;

@Repository
public interface ExtratcRepository extends BaseRepository<Extratc, Long>{
	
	List<Extratc> findByAccount( Account accountId);
}
