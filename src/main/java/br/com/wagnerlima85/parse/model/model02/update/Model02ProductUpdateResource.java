package br.com.wagnerlima85.parse.model.model02.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Model02ProductUpdateResource {

	private String ref;
	private String stock;
	private String price;
	private String sale_price;
	
}
