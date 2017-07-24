package br.com.sistema.campanhas.controllers;

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

import br.com.sistema.campanhas.models.CampanhaRequest;
import br.com.sistema.campanhas.models.CampanhaResponse;
import br.com.sistema.campanhas.service.CampanhaService;

@RestController
@RequestMapping("/api/campanha")
public class CampanhaController {
	
	@Autowired
	private CampanhaService service;

	@Cacheable(value = "campanhas")
	@RequestMapping(method = RequestMethod.GET, produces =  {MediaType.APPLICATION_JSON_VALUE })
	public List<CampanhaResponse> listaTodasAsCampanhas(){
		return service.pesquisarTodas();
	}
	
	@CacheEvict(allEntries = true, value = "campanhas", beforeInvocation = false)
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponse salvar(@RequestBody CampanhaRequest Campanha){
		return service.adicionarCampanha(Campanha);
		
	}
	
	@CacheEvict(allEntries = true, value = "campanhas", beforeInvocation = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Response remove(@PathVariable Long id) {
        return service.excluiCampanha(id);
    }
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponse alterar(@PathVariable Long id){
		return service.atualizaCampanha(id);
	}
}
