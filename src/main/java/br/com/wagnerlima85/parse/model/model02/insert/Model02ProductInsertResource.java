package br.com.wagnerlima85.parse.model.model02.insert;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Model02ProductInsertResource {

	private String description;
	private String code;
	private String refId;
	private String brand;
	private List<Model02SubProductResource> skus;
	
}
