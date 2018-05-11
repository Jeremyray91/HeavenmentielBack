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
import heavenmentiel.repositories.CommandeRepo;
import heavenmentiel.repositories.EventRepo;

@Service
@Transactional
@CrossOrigin("http://localhost:4200")
public class CommandeService {
	@Autowired CommandeRepo commandRepo;
	@Autowired EventService evs;
	
	public List<Commande> getMulticriteria(String nom, String prenom, Long idClient, Date datemin, Date datemax,Integer page){
		return commandRepo.getMulticriteria(nom, prenom, idClient, datemin, datemax,page);
	}
	
	public JsonNode toJsonCmd(Commande cmd) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", cmd.getId());
		rootNode.put("date", cmd.getDate().toString());
		rootNode.put("userId", cmd.getUser().getId());
		
		ArrayNode events = rootNode.putArray("events");
		for(AchatsEvents achat : cmd.getAchatsEvents()) {
			ObjectNode eventNode = mapper.createObjectNode();
			eventNode.put("qte", achat.getQte());
			eventNode.putPOJO("event", evs.toJsonEvent(achat.getEvent()));
			events.add(eventNode);
		}
		
		return rootNode;
	}
}
