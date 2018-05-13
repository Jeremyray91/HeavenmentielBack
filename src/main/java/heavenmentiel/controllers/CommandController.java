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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.models.Commande;
import heavenmentiel.services.CommandeService;
import heavenmentiel.services.EventService;
import heavenmentiel.utils.Utils;

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
				@RequestParam(value = "datemin", required = false) String datemin,
				@RequestParam(value = "datemax", required = false) String datemax,
				@RequestParam(value = "page", required = false) Integer page
	){

		Date dateMin = null;
		Date dateMax = null;
		if (datemin != null)
			dateMin = Utils.parseDateFrToGb(datemin);
		if (datemax != null)
			dateMax = Utils.parseDateFrToGb(datemax);
		List<Commande> commands = commandService.getMulticriteria(firstname, lastname, idClient, dateMin, dateMax, page);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonCommands = mapper.createArrayNode();
		for(Commande cmd : commands) {
			jsonCommands.add(commandService.toJson(cmd));
		}
		return jsonCommands;
	}
}
