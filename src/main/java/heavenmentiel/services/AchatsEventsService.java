package heavenmentiel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.models.AchatsEvents;

@Service
@Transactional
@CrossOrigin("http://localhost:4200")
public class AchatsEventsService {
	@Autowired EventService evs;
	
	public JsonNode toJson(AchatsEvents achat) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", achat.getId());
		rootNode.putPOJO("event", evs.toJsonEvent(achat.getEvent()));
		rootNode.put("commandeId",achat.getCommande().getId());
		rootNode.put("qte",achat.getQte());
		return rootNode;
	}
}
