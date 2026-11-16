
package nj.cst323activity1.controller;

import nj.cst323activity1.model.Game;
import nj.cst323activity1.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private static final Logger logger =
            LoggerFactory.getLogger(GameController.class);

    private final GameService gameService;

    public GameController(GameService gameService) {
        logger.info("Entering GameController constructor");
        this.gameService = gameService;
        logger.info("Exiting GameController constructor");
    }

    @GetMapping("/")
    public String home() {
        logger.info("Entering GameController.home");

        try {
            logger.info("Exiting GameController.home");
            return "redirect:/games";
        } catch (Exception e) {
            logger.error("Exception in GameController.home", e);
            throw e;
        }
    }

    @GetMapping("/games")
    public String games(Model model) {
        logger.info("Entering GameController.games");

        try {
            model.addAttribute("games", gameService.getAllGames());

            logger.info("Exiting GameController.games successfully");
            return "games";
        } catch (Exception e) {
            logger.error("Exception in GameController.games", e);
            throw e;
        }
    }

    @GetMapping("/games/add")
    public String showAddForm(Model model) {
        logger.info("Entering GameController.showAddForm");

        try {
            model.addAttribute("game", new Game());

            logger.info("Exiting GameController.showAddForm successfully");
            return "add-game";
        } catch (Exception e) {
            logger.error("Exception in GameController.showAddForm", e);
            throw e;
        }
    }

    @PostMapping("/games/add")
    public String addGame(@ModelAttribute Game game) {
        logger.info("Entering GameController.addGame");

        try {
            gameService.saveGame(game);

            logger.info("Exiting GameController.addGame successfully");
            return "redirect:/games";
        } catch (Exception e) {
            logger.error("Exception in GameController.addGame", e);
            throw e;
        }
    }

    @GetMapping("/games/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        logger.info("Entering GameController.showEditForm for ID: {}", id);

        try {
            Game game = gameService.getGameById(id);
            model.addAttribute("game", game);

            logger.info("Exiting GameController.showEditForm successfully");
            return "edit-game";
        } catch (Exception e) {
            logger.error(
                    "Exception in GameController.showEditForm for ID: {}",
                    id, e
            );
            throw e;
        }
    }

    @PostMapping("/games/edit/{id}")
    public String updateGame(
            @PathVariable int id,
            @ModelAttribute Game game) {

        logger.info("Entering GameController.updateGame for ID: {}", id);

        try {
            game.setId(id);
            gameService.saveGame(game);

            logger.info("Exiting GameController.updateGame successfully");
            return "redirect:/games";
        } catch (Exception e) {
            logger.error(
                    "Exception in GameController.updateGame for ID: {}",
                    id, e
            );
            throw e;
        }
    }

    @PostMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable int id) {
        logger.info("Entering GameController.deleteGame for ID: {}", id);

        try {
            Game game = gameService.getGameById(id);
            gameService.deleteGame(game);

            logger.info("Exiting GameController.deleteGame successfully");
            return "redirect:/games";
        } catch (Exception e) {
            logger.error(
                    "Exception in GameController.deleteGame for ID: {}",
                    id, e
            );
            throw e;
        }
    }
}
