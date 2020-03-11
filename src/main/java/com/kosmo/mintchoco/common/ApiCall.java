package com.kosmo.mintchoco.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ApiCall 
{
	public static String post(String requestURL) 
	{
		String strResponse = "";
		String JsonData = "";
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity param = new HttpEntity(JsonData, headers);

	    RestTemplate restTemplate = new RestTemplate();
	    strResponse = restTemplate.postForObject(requestURL, param, String.class);
		return strResponse;
	}
}
