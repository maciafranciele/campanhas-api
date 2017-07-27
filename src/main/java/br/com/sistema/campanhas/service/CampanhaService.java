package br.com.sistema.campanhas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.campanhas.exception.CampanhaNaoEncontradaException;
import br.com.sistema.campanhas.model.Campanha;
import br.com.sistema.campanhas.model.TimeDoCoracao;
import br.com.sistema.campanhas.repository.CampanhaRepository;
import br.com.sistema.campanhas.repository.TimeRepository;

@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository repository;
	
	@Autowired
	private TimeRepository timeRepository;
	

	public List<Campanha> pesquisaCampanhasVigentes(){
		return this.repository.findAllVigentes(LocalDate.now());
	}

	public Campanha adicionarCampanha( Campanha campanha){
		List<Campanha> listaCampanhas = this.pesquisaCampanhasVigentes();
		List<Campanha> listaAtualizada = this.alteraData(listaCampanhas, campanha);
		listaAtualizada.add(campanha);
		
		for (Campanha c : listaAtualizada) {
			System.out.println(c.getDataFim());
			this.repository.save(c);
		}
		return campanha;
	}

	
	public void deleteCampanha(Campanha campanha) {
		this.repository.delete(campanha);
	}

	private List<Campanha> alteraData(List<Campanha> listaCampanha, Campanha campanha) {
		List<Campanha> lista = listaCampanha;
		List<Campanha> listaDatas = new ArrayList<Campanha>();
		List<Campanha> listaAtualizada = new ArrayList<Campanha>();
		
		if (!lista.isEmpty()) {
			for (Campanha c : lista) {
				listaDatas.add(c);
			}

			for (Campanha c : lista) {
				listaDatas.remove(c);
				c.setDataFim(c.getDataFim().plusDays(1));

				listaDatas.add(c);
				listaAtualizada.add(c);
			}
			
			if (existeCampanhaComMesmaVigencia(listaDatas, campanha)) {
				alteraData(listaAtualizada, campanha);
			}
		}
		return lista;
	}
	
	private boolean existeCampanhaComMesmaVigencia(List<Campanha> listaCampanha, Campanha campanha){
		
		for (Campanha c : listaCampanha) {
			if(c.getDataFim().equals(campanha.getDataFim())){
				return true;
			}
		}
		return false;
	}
	
	public Campanha alterar(String id, Campanha Campanha) throws CampanhaNaoEncontradaException { 
		Campanha campanha = this.repository.findById(id);
		Optional.ofNullable(campanha).orElseThrow(() -> new CampanhaNaoEncontradaException());
		return this.repository.save(campanha);
	}
	
	public Response excluir(String id) throws CampanhaNaoEncontradaException {
		Campanha campanha = this.repository.findById(id);
		Optional.ofNullable(campanha).orElseThrow(() -> new CampanhaNaoEncontradaException());
		this.repository.delete(campanha);
		return Response.status(Response.Status.OK).build();
		
	}
	
	public Campanha buscarPorId(String id) throws CampanhaNaoEncontradaException{
		Optional.ofNullable(this.repository.findById(id)).orElseThrow(() -> new CampanhaNaoEncontradaException());
		return this.repository.findById(id);
	}

	public List<Campanha> buscarPorTime(String idTime) {
		TimeDoCoracao time = this.timeRepository.findById(idTime);
		
		return this.repository.findByTime(time.getId());
	}

	
	
	

}
