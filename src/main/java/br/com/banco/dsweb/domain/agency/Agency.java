package br.com.banco.dsweb.domain.agency;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.banco.dsweb.dto.agency.AgencyRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_AGENCY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agency implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD")
	private Long id;
	
	@Column(name = "NAME_AGENCY", length = 60 ,nullable = false)
	private String nameAgency;
	
	@Column(name = "NUMBER_AGENCY", length = 4 ,nullable = false)
	private String numberAgency;
	
	@Column(name = "LOCALITY_AGENCY", nullable = false)
	private String locality;
	
	public static Agency builder(AgencyRequestDTO agencyRequest) {
		return new Agency(null, agencyRequest.getNameAgency(), generateNumber(), agencyRequest.getLocality());
	}
	
	private static String generateNumber() {
		String number = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
	        Integer n = random.nextInt(9);
	        number += n.toString();
	     }
		
	 return number.concat("7");
	}
	
}
