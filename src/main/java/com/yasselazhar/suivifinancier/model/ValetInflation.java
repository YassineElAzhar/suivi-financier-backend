package com.yasselazhar.suivifinancier.model;

import java.util.List;
import java.util.Map;

public class ValetInflation {
	
	private Map<String,String> terms;
	
	private Map<String,Map<String,Object>> seriesDetail;
	
	private List<Map<String, Object>> observations;

	public Map<String, String> getTerms() {
		return terms;
	}

	public void setTerms(Map<String, String> terms) {
		this.terms = terms;
	}

	public Map<String, Map<String, Object>> getSeriesDetail() {
		return seriesDetail;
	}

	public void setSeriesDetail(Map<String, Map<String, Object>> seriesDetail) {
		this.seriesDetail = seriesDetail;
	}

	public List<Map<String, Object>> getObservations() {
		return observations;
	}

	public void setObservations(List<Map<String, Object>> observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "ValetInflation [terms=" + terms.toString() + ", seriesDetail=" + seriesDetail.toString() + ", observations=" + observations.toString()
				+ "]";
	}
	
	
	
	
}