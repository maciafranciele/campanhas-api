package br.com.sistema.campanhas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cadastro já foi efetuado!")
public class CampanhaNaoEncontradaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
