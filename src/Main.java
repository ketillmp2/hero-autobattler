import core.controllers.GameController;
import core.player.Player;
import utils.Constants;

public class Main {
    public static void main(String[] args) {
        Constants.PRINT_MESSAGES_IN_CONTROLLER.value = 1;
        Player kirill = Player.newPlayerWithName("Player 1");
        Player valera = Player.newPlayerWithName("Player 2");

        GameController gameController = GameController.forPlayers(kirill, valera);
        gameController.startGame();
    }
}
