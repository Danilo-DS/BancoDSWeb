package br.com.banco.dsweb.dto.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.banco.dsweb.domain.client.Client;
import br.com.banco.dsweb.dto.agency.AgencyDTO;
import br.com.banco.dsweb.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreateDTO {

	private AgencyDTO agency;
	@Enumerated(EnumType.STRING)
	private TypeAccount typeAccount;
	private Client client;
}
