package br.com.alura.forum.controller;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TopicosController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> Lista() {
		Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programação"));
		
		return Arrays.asList(topico, topico, topico);
		
	}
	
}
