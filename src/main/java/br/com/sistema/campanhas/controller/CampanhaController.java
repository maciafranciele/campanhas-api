package br.com.sistema.campanhas.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.campanhas.exception.CampanhaNaoEncontradaException;
import br.com.sistema.campanhas.model.Campanha;
import br.com.sistema.campanhas.service.CampanhaService;

@RestController
@RequestMapping("/api/campanha")
public class CampanhaController {
	
	@Autowired
	private CampanhaService service;
	
	@Cacheable(value = "campanhas")
	@RequestMapping(method = RequestMethod.GET, produces =  {MediaType.APPLICATION_JSON_VALUE })
	public List<Campanha> listaTodasAsCampanhas(){
		return service.pesquisarTodas();
	}
	
	@CacheEvict(allEntries = true, value = "campanhas", beforeInvocation = false)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha salvar(@RequestBody  Campanha Campanha) {
		return this.service.adicionarCampanha(Campanha);
	}
	
	@CacheEvict(allEntries = true, value = "campanhas", beforeInvocation = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha alterarCampanha(@PathVariable String id, @RequestBody Campanha campanha) throws CampanhaNaoEncontradaException {
		return this.service.alterar(id, campanha);
	}

	@CacheEvict(allEntries = true, value = "campanhas", beforeInvocation = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Response excluir(@PathVariable String id) throws CampanhaNaoEncontradaException {
		return this.service.excluir(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha pesquisarId(@PathVariable String id) throws CampanhaNaoEncontradaException {
		return this.service.buscarPorId(id);
	}
	
}
