package br.com.banco.dsweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import br.com.banco.dsweb.domain.agency.Agency;
import br.com.banco.dsweb.domain.client.Client;
import br.com.banco.dsweb.repository.AgencyRepository;
import br.com.banco.dsweb.repository.ClientRepository;

@Configuration
public class LoadingBase implements ApplicationRunner{
	
	@Autowired
	private ClientRepository clientRespository;
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if(clientRespository.findAll().isEmpty()) {
			clientRespository.save(new Client(null, "Danilo Silva", "16863584689", "586384133684", "Rua a esquerda direita", "danilo@gmail.com"));
			clientRespository.save(new Client(null, "Alguem da Silva", "16863958468", "133684586384", "Rua a direita esquerda", "alguem@gmail.com"));
		}
		
		if(agencyRepository.findAll().isEmpty()) {
			agencyRepository.save(new Agency(null, "Agency CIA", "5843", "Rua em frete vire"));
			agencyRepository.save(new Agency(null, "Agency NSA", "1685", "Rua vire e siga"));
		}
		
	}

}
