
package nj.cst323activity1.service;

import nj.cst323activity1.model.Game;
import nj.cst323activity1.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private static final Logger logger =
            LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        logger.info("Entering GameService constructor");
        this.gameRepository = gameRepository;
        logger.info("Exiting GameService constructor");
    }

    public List<Game> getAllGames() {
        logger.info("Entering GameService.getAllGames");

        try {
            List<Game> games = gameRepository.findAll();

            logger.info(
                    "Exiting GameService.getAllGames successfully. Found {} games",
                    games.size()
            );

            return games;
        } catch (Exception e) {
            logger.error("Exception in GameService.getAllGames", e);
            throw e;
        }
    }

    public Game getGameById(int id) {
        logger.info("Entering GameService.getGameById for ID: {}", id);

        try {
            Game game = gameRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Game not found: " + id)
                    );

            logger.info(
                    "Exiting GameService.getGameById successfully for ID: {}",
                    id
            );

            return game;
        } catch (Exception e) {
            logger.error(
                    "Exception in GameService.getGameById for ID: {}",
                    id, e
            );
            throw e;
        }
    }

    public void saveGame(Game game) {
        logger.info("Entering GameService.saveGame");

        try {
            gameRepository.save(game);

            logger.info(
                    "Exiting GameService.saveGame successfully for ID: {}",
                    game.getId()
            );
        } catch (Exception e) {
            logger.error("Exception in GameService.saveGame", e);
            throw e;
        }
    }

    public void deleteGame(Game game) {
        logger.info(
                "Entering GameService.deleteGame for ID: {}",
                game.getId()
        );

        try {
            gameRepository.delete(game);

            logger.info(
                    "Exiting GameService.deleteGame successfully for ID: {}",
                    game.getId()
            );
        } catch (Exception e) {
            logger.error(
                    "Exception in GameService.deleteGame for ID: {}",
                    game.getId(), e
            );
            throw e;
        }
    }
}
