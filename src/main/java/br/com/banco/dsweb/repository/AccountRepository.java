package br.com.banco.dsweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>{

	@Query("SELECT A FROM TB_ACCOUNT A WHERE A.accountNumber = :numberAccount AND A.agency = :numberAgency")
	Optional<Account> findByAccountAgency(@Param("numberAccount") String numberAccount, @Param("numberAgency") String agency);
}
