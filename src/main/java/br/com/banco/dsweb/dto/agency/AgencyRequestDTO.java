package br.com.banco.dsweb.dto.agency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgencyRequestDTO {

	private String nameAgency;
	private String locality;
	
}
