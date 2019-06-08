package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

public class Distretto {
	private int idDistretto;
	private List<Event> listaEventiDistretto;

	public Distretto(int idDistretto) {
		super();
		this.idDistretto = idDistretto;
	}
	
	

	public List<Event> getListaEventiDistretto() {
		return listaEventiDistretto;
	}



	public void setListaEventiDistretto(List<Event> listaEventiDistretto) {
		this.listaEventiDistretto = listaEventiDistretto;
	}



	public int getIdDistretto() {
		return idDistretto;
	}

	public void setIdDistretto(int idDistretto) {
		this.idDistretto = idDistretto;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDistretto;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		if (idDistretto != other.idDistretto)
			return false;
		return true;
	}
	
	public double  longitudineMedia() {
		double somma=0;
		int conta=0;
		for(Event e:this.getListaEventiDistretto()) {
			somma+=e.getGeo_lon();
			conta++;
		}
		return somma/conta;
	}
	
	public double  latitudineMedia() {
		double somma=0;
		int conta=0;
		for(Event e:this.getListaEventiDistretto()) {
			somma+=e.getGeo_lon();
			conta++;
		}
		return somma/conta;
	}
	
	

}
