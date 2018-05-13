package heavenmentiel.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import heavenmentiel.models.Commande;
import heavenmentiel.services.CommandeService;
import heavenmentiel.services.EventService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CommandController {
	
	@Autowired CommandeService commandService;
	@Autowired protected Environment env;
	
	@RequestMapping(value="/commandes", method = RequestMethod.POST)
	public Commande create(@RequestBody Commande commande) {
		return commandService.create(commande);
	}
	
	@RequestMapping(value = "/commands/multicriteria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getMultiCriteria(
				@RequestParam(value = "firstname", required = false) String firstname,
				@RequestParam(value = "lastname", required = false) String lastname,
				@RequestParam(value = "id", required = false) Long idClient,
				@RequestParam(value = "datemin", required = false) Date datemin,
				@RequestParam(value = "datemax", required = false) Date datemax,
				@RequestParam(value = "page", required = false) Integer page
	){
		List<Commande> commands = commandService.getMulticriteria(firstname, lastname, idClient, datemin, datemax, page);
		System.out.println(commands.toString());
		return null;
	}
}
