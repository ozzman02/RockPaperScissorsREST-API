package com.restapi.core;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRestController {
	
	@RequestMapping(value = "/rockpaperscissors/singlematch", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody List<Game> games) {
		if (games.size() % 2 != 0) {
			return new ResponseEntity<Error>(new Error("The number of participantes must be an even number"), 
					HttpStatus.FORBIDDEN);
		} else if (ApplicationUtils.validMatch(games)) { 
			Game game = ApplicationUtils.resolveMatch(games);
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} else {
			return new ResponseEntity<Error>(new Error("There are duplicate player names"), 
					HttpStatus.FORBIDDEN);
		}
	}
	
	
	
	
	@RequestMapping(value = "/rockpaperscissors/getPlayer", method = RequestMethod.GET)
	public ResponseEntity<Player> get() {
		Player p1 = new Player("Oscar");
		return new ResponseEntity<Player>(p1, HttpStatus.OK);
	}
	
	
	
}
