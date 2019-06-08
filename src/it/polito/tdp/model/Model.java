package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.math.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.db.EventsDao;

public class Model {
	Graph<Distretto,DefaultEdge> grafo;
	
	public Model() {
		
	}
	
	public void creaGrafo(int anno) {
		EventsDao dao=new EventsDao();
		grafo =new SimpleWeightedGraph<>(DefaultEdge.class);
		for(Distretto d:dao.tuttiDistretti()) {
			grafo.addVertex(d);
			d.setListaEventiDistretto(dao.eventiperAnnoDistretti(anno, d.getIdDistretto()));
		}
		for(Distretto d1:grafo.vertexSet()) {
			for(Distretto d2:grafo.vertexSet()) {
				if(d1.getIdDistretto()!=d2.getIdDistretto()) {
					 grafo.addEdge(d1, d2);
                     grafo.setEdgeWeight(d1, d2, calcolaDistanza(d1.longitudineMedia(),d1.latitudineMedia(),d2.longitudineMedia(),d2.latitudineMedia()));
				}
			}
		}
		    
	}
	
	public double calcolaDistanza(double lon1,double lat1,double lon2,double lat2) {
		double sommaquadrati=(lon1-lon2)*(lon1-lon2)+(lat1-lat2)*(lat1-lat2);
		return sommaquadrati;//fare la radice e capire come si eleva a potenza
	}

	public Graph<Distretto, DefaultEdge> getGrafo() {
		return grafo;
	}
	
	public Map<Double,Distretto> distretti_adiacenti(Distretto d){
		Map<Double,Distretto> lista=new TreeMap<Double,Distretto>();
	
		for(Distretto altro:grafo.vertexSet()) {
			if(altro.getIdDistretto()!=d.getIdDistretto())
				lista.put(grafo.getEdgeWeight(grafo.getEdge(altro, d)),altro);
		}
		return lista;
	}
	public List<Integer> getAnni(){
		EventsDao dao=new EventsDao();
		return dao.anniDisponibili();
	}
	
}
