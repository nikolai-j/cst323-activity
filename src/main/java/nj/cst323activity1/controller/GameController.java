package nj.cst323activity1.controller;

import nj.cst323activity1.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String games(Model model) {

        model.addAttribute("games", gameService.getAllGames());

        return "games";
    }


}
