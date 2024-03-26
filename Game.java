import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private int bank;

    public Game(Player _player1, Player _player2) {
        player1 = _player1;
        player2 = _player2;
        bank = 0;
    }

    public boolean start_game() {
        Scanner player_input = new Scanner(System.in);

        System.out.println("Miser 10?(y/n): ");
        String user_response = player_input.nextLine();
        if (user_response == "y") {
            player1.bet(10);
            return true;
        } else if (user_response == "n") {
            return false;
        } else {
            return false;
        }
    }
}
