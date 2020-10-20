package br.com.wagnerlima85.parse.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wagnerlima85.parse.model.model02.insert.Model02ProductInsertResource;
import br.com.wagnerlima85.parse.model.model02.update.Model02ProductUpdateResource;
import br.com.wagnerlima85.parse.service.ParseService;

@RestController
public class ParseRestController {
	
	@Autowired
	private ParseService service;
	
	@PostMapping("/insert/{cnpj}")
	public void insertProductsModel02(HttpServletRequest request, @RequestBody String payload, @PathVariable String cnpj) throws InterruptedException {

		String host = request.getHeader(HttpHeaders.HOST);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Model02ProductInsertResource>>(){}.getType();
		List<Model02ProductInsertResource> list = gson.fromJson(payload, type);
		service.executeInsertModel02(list, host, cnpj);
	}
	
	@PostMapping("/update/{cnpj}")
	public void updateProducts(HttpServletRequest request, @RequestBody String payload, @PathVariable String cnpj) throws InterruptedException {

		String host = request.getHeader(HttpHeaders.HOST);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Model02ProductUpdateResource>>(){}.getType();
		List<Model02ProductUpdateResource> list = gson.fromJson(payload, type);
		service.executeUpdateModel02(list, host, cnpj);
	}
}
