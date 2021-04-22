package br.com.banco.dsweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.dsweb.domain.account.Account;
import br.com.banco.dsweb.domain.agency.Agency;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>{

	Optional<Account> findByAccountNumberAndAgency(String numberAccount, Agency agency);
	
	@Modifying
	@Query("UPDATE TB_ACCOUNT A SET A.balance = :newBalance WHERE A.id = :accountId")
	void updateBalanceAccount(@Param("newBalance") Double newBalance, @Param("accountId") Long accountId);
	
	@Query("SELECT A FROM TB_ACCOUNT A WHERE A.agency = :agency")
	Boolean existAccoutWithAgency(@Param("agency") Agency agency);
	
	Boolean existsByAgency(Agency agency);
}
