package br.com.wagnerlima85.parse.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.wagnerlima85.parse.model.model02.insert.Model02ProductInsertResource;
import br.com.wagnerlima85.parse.model.model02.update.Model02ProductUpdateResource;

@Service
public class ParseService {

	@Async
	public void executeInsertModel02(List<Model02ProductInsertResource> list, String host, String cnpj) throws InterruptedException {
		Gson gson = new Gson();
		for (Model02ProductInsertResource product : list){
			String str = gson.toJson(product);
			String url = new String("xxxx/insert");
			this.connectServer(url, cnpj, str);
		}
	}
	
	@Async
	public void executeUpdateModel02(List<Model02ProductUpdateResource> list, String host, String cnpj) throws InterruptedException {
		Gson gson = new Gson();
		for (Model02ProductUpdateResource product : list){
			String str = gson.toJson(product);
			String url = new String("http://xxxx/insert/product/qtd-price?RefID="+product.getRef()+"&sku="+product.getRef());
			this.connectServer(url, cnpj, str);
		}
	}

	private void connectServer(String urlString, String cnpj, String object){

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleGFtcGxlIjoidHJ1ZSIsIm5hbWUiOiJXYWduZXIgTGltYSIsInBhc3N3b3JkIjoibm8tcGFzc3dvcmQifQ.wwNT1By6U4suda7PRiih6I8p8GZdGC5E7iEAj3LcqEM");
			conn.setRequestProperty("cnpj", cnpj);
			
			OutputStream os = conn.getOutputStream();
			os.write("".getBytes());
			os.flush();
			
			if ((conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
				&& (conn.getResponseCode() != HttpURLConnection.HTTP_OK)) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
