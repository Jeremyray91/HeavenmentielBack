package heavenmentiel.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.models.AchatsEvents;
import heavenmentiel.models.Commande;
import heavenmentiel.models.Event;
import heavenmentiel.repositories.CommandeRepo;
import heavenmentiel.repositories.UserRepository;

@Service
@Transactional
@CrossOrigin("http://localhost:4200")
public class CommandeService {
	@Autowired CommandeRepo commandRepo;
	@Autowired AchatsEventsService achatsService;
	@Autowired UserRepository userRepo;
	
	public JsonNode getAll() {
		return commandRepo.getAll();
	}
	
	public Commande create(Commande commande) {
		return commandRepo.create(commande);
	}
	
	public List<Commande> getMulticriteria(String nom, String prenom, Long idClient, Date datemin, Date datemax,Integer page){
		return commandRepo.getMulticriteria(nom, prenom, idClient, datemin, datemax,page);
	}
	
	public Commande getById(Integer id) {
		return commandRepo.getById(id);
	}
	
	public JsonNode toJson(Commande cmd) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode rootNode = mapper.createObjectNode();
			rootNode.put("id", cmd.getId());
			rootNode.put("date", cmd.getDate().toString());
			rootNode.putPOJO("user",userRepo.toJsonCmd(cmd.getUser()));
			ArrayNode events = rootNode.putArray("achatsEvents");
			for(AchatsEvents achat : cmd.getEvents()) {
				events.add(achatsService.toJson(achat));
			}
			return rootNode;
	}
	
	
}
