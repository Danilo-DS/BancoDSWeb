package br.com.banco.dsweb.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{
	
	Boolean findByClient(Long id);
}
