package com.yasselazhar.suivifinancier.external.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasselazhar.suivifinancier.model.ValetInflation;


public class ValetBankOfCanada {
	
	public Map<String,Integer> getInflationRate() {
		Map<String,Integer> mapDateTauxInflation = new HashMap<String, Integer>();
		RestTemplate restTemplate = new RestTemplate();
		
		//Les URLs à appeler
		String BELOW1 = "https://www.banqueducanada.ca/valet/observations/BELOW1";
		String ONETWO = "https://www.banqueducanada.ca/valet/observations/ONETWO";
		String TWOTHREE = "https://www.banqueducanada.ca/valet/observations/TWOTHREE";
		String ABOVE3 = "https://www.banqueducanada.ca/valet/observations/ABOVE3";
		
		//Nous préparons les headers avec le MediaType JSON
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		//Nous préparons les URLs
		String urlTemplateBELOW1 = UriComponentsBuilder.fromHttpUrl(BELOW1)
		        .queryParam("start_date", "{start_date}").queryParam("end_date", "{end_date}")
		        .encode().toUriString();
		String urlTemplateONETWO = UriComponentsBuilder.fromHttpUrl(ONETWO)
		        .queryParam("start_date", "{start_date}").queryParam("end_date", "{end_date}")
		        .encode().toUriString();
		String urlTemplateTWOTHREE = UriComponentsBuilder.fromHttpUrl(TWOTHREE)
		        .queryParam("start_date", "{start_date}").queryParam("end_date", "{end_date}")
		        .encode().toUriString();
		String urlTemplateABOVE3 = UriComponentsBuilder.fromHttpUrl(ABOVE3)
		        .queryParam("start_date", "{start_date}").queryParam("end_date", "{end_date}")
		        .encode().toUriString();
		
		//Map des paramêtres (startDate et endDate)
		Map<String, String> params = new HashMap<>();

		//On prend la valeur de l'inflation sur 3 ans
		params.put("start_date", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-5)+"-01-01");
		params.put("end_date", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-12-31");

		//On appel le webservice pour avoir les taux.
		HttpEntity<String> responseBELOW1 = restTemplate.exchange(
				//Inflation inférieur à 1
				urlTemplateBELOW1, HttpMethod.GET, entity, String.class, params
		);
		HttpEntity<String> responseONETWO = restTemplate.exchange(
				//Inflation entre 1 et 2
				urlTemplateONETWO, HttpMethod.GET, entity, String.class, params
		);
		HttpEntity<String> responseTWOTHREE = restTemplate.exchange(
				//Inflation entre 2 et 3
				urlTemplateTWOTHREE, HttpMethod.GET, entity, String.class, params
		);
		HttpEntity<String> responseABOVE3 = restTemplate.exchange(
				//Inflation supérieur à 3
				urlTemplateABOVE3, HttpMethod.GET, entity, String.class, params
		);
		
        ObjectMapper mapper = new ObjectMapper();
        try {
        	//On traite les données recu pour créer notre tableau de valeurs
        	//On le fait en croissant pour overwrite les valeurs dans le tableau si elle sont supérieurs
			ValetInflation map = mapper.readValue(responseBELOW1.getBody(), ValetInflation.class);
			map.getObservations().forEach(observation -> {
				String date = observation.get("d").toString();
				
				@SuppressWarnings("unchecked")
				Map<String,String> mapTauxInflation = (Map<String,String>) observation.get("BELOW1");
				if(Integer.valueOf(mapTauxInflation.get("v")) > 0) {
					mapDateTauxInflation.put(date,Integer.valueOf(mapTauxInflation.get("v")));
				}
			});
			
			map = mapper.readValue(responseONETWO.getBody(), ValetInflation.class);
			map.getObservations().forEach(observation -> {
				String date = observation.get("d").toString();
				
				@SuppressWarnings("unchecked")
				Map<String,String> mapTauxInflation = (Map<String,String>) observation.get("ONETWO");
				if(Integer.valueOf(mapTauxInflation.get("v")) > 0) {
					mapDateTauxInflation.put(date,Integer.valueOf(mapTauxInflation.get("v")));
				}
			});
			
			map = mapper.readValue(responseTWOTHREE.getBody(), ValetInflation.class);
			map.getObservations().forEach(observation -> {
				String date = observation.get("d").toString();
				
				@SuppressWarnings("unchecked")
				Map<String,String> mapTauxInflation = (Map<String,String>) observation.get("TWOTHREE");
				if(Integer.valueOf(mapTauxInflation.get("v")) > 0) {
					mapDateTauxInflation.put(date,Integer.valueOf(mapTauxInflation.get("v")));
				}
			});
			
			map = mapper.readValue(responseABOVE3.getBody(), ValetInflation.class);
			map.getObservations().forEach(observation -> {
				String date = observation.get("d").toString();
				
				@SuppressWarnings("unchecked")
				Map<String,String> mapTauxInflation = (Map<String,String>) observation.get("ABOVE3");
				if(Integer.valueOf(mapTauxInflation.get("v")) > 0) {
					mapDateTauxInflation.put(date,Integer.valueOf(mapTauxInflation.get("v")));
				}
			});
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        
        //Nous trions notre tableau avec la date (notre key)
        TreeMap<String, Integer> sorted = new TreeMap<>();
        sorted.putAll(mapDateTauxInflation);
		
		return sorted;
	}

}