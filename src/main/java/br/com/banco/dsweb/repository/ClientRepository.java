package br.com.banco.dsweb.repository;

import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.Client;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long>{

}
