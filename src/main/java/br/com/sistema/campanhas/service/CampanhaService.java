package br.com.sistema.campanhas.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.campanhas.models.Campanha;
import br.com.sistema.campanhas.models.CampanhaRequest;
import br.com.sistema.campanhas.models.CampanhaResponse;
import br.com.sistema.campanhas.repositories.CampanhaRepository;

@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository repository;
	

	public List<CampanhaResponse> pesquisarTodas(){
		
		List<Campanha> campanhas = getCampanhasVigentes();
		List<CampanhaResponse> response = new ArrayList<>();
		
		for (Campanha campanha : campanhas) {
			
			CampanhaResponse cr = new CampanhaResponse();
			cr.setId(campanha.getId().toHexString());
			cr.setNomeCampanha(campanha.getNomeCampanha());
	
			
			SimpleDateFormat dataFimFormatada = new SimpleDateFormat("yyyy-MM-dd");
			dataFimFormatada.format(campanha.getDataFim());
			Calendar dataFim = dataFimFormatada.getCalendar();
			cr.setDataFim(dataFim);

			SimpleDateFormat dataInicioFormatada = new SimpleDateFormat("yyyy-MM-dd");
			dataInicioFormatada.format(campanha.getDataInicio());
			Calendar dataInicio = dataInicioFormatada.getCalendar();
			cr.setDataInicio(dataInicio);
			
			response.add(cr);
		}
		
		return response;
	}

	public CampanhaResponse adicionarCampanha(CampanhaRequest campanhaRequest) {
		
		Campanha campanha = CampanhaRequestToCampanha(campanhaRequest);
		
		boolean mesmoPeriodo = existeCampanhasMesmoPeriodo(campanha);
		
		List<Campanha> listaCampanhas = this.getCampanhasVigentes();
		
		
		if(mesmoPeriodo){
			
			corrigirData(listaCampanhas,campanha);
			
			repository.salvaCampanha(campanha);
		}
		
		
		
		
		return null;
	}
	
	private Campanha CampanhaRequestToCampanha(CampanhaRequest campanhaRequest) {
		Campanha campanha = new Campanha();
			campanha.setNomeCampanha(campanhaRequest.getNomeCampanha());
			campanha.setDataInicio(campanhaRequest.getDataInicio().getTime());
			campanha.setDataFim(campanhaRequest.getDataFim().getTime());
		return campanha;
	}

	private void corrigirData(List<Campanha> listaCampanha, Campanha campanha) {

		List<Campanha> novaLista = new ArrayList<>();
		
		LocalDate dataCampanha = campanha.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("data recebida" + dataCampanha);
		
		if (!listaCampanha.isEmpty()) {
			
			for (Campanha c : listaCampanha) {
				LocalDate localDateFim = c.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				localDateFim.plusDays(1);
				
				c.setDataFim(Date.from(localDateFim.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				
				novaLista.add(c);
			}
			
			for (Campanha c : novaLista) {
				repository.salvaCampanha(c);
			}
			
			

		}
	}
	
	 public static Date asDate(LocalDate localDate) {
		    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		  }

	public boolean existeCampanhasMesmoPeriodo(Campanha campanha){
		
		LocalDate campanhaDateFim = campanha.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate campanhaDateInicio = campanha.getDataInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		List<Campanha> todasCampanhasVigentes = getCampanhasVigentes();
		
		for(Campanha c : todasCampanhasVigentes){
			LocalDate listaDateFim = c.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate listaDateInicio = c.getDataInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if(campanhaDateInicio == listaDateInicio || campanhaDateInicio.isBefore(listaDateInicio)){
				if(campanhaDateFim == listaDateFim || campanhaDateFim.isAfter(listaDateFim)){
					return true;
				}
			}
		}
		return false;

	}

	private List<Campanha> getCampanhasVigentes() {
		List<Campanha> todasCampanhas = this.repository.findAllCampanhas();
		List<Campanha> vigentes = new ArrayList<>();
		
		for(Campanha campanha : todasCampanhas){

			LocalDate localDateFim = campanha.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate hoje = LocalDate.now();
			
			if(hoje.isBefore(localDateFim) || hoje == localDateFim){
				vigentes.add(campanha);
			}
		}
		return vigentes;
	}

	public Response excluiCampanha(Long id) {
		return Response.ok().build();
	}

	public CampanhaResponse atualizaCampanha(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
