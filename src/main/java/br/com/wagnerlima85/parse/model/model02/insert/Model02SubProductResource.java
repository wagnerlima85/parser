package br.com.wagnerlima85.parse.model.model02.insert;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Model02SubProductResource {

	private String price;
	private String sale_price;
	private String code;
	private String refId;
	private String name;
	private String stock;
	private String brand;
	private List<Model02FieldResource> fields;

}
