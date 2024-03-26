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



    public void start_game() {
        Scanner player_input = new Scanner(System.in);

        System.out.println("Miser 10?(y/n): ");
        String user_response = player_input.nextLine();
        if (user_response == "y") {
            player1.bet(10);
            bank+=10;
            continue_game();

        } else if (user_response == "n") {
            end_game();
        }

    }
    public void continue_game() {
        //cretion du la pile de carte, puis shuffle
        Deck game_deck = new Deck();
        game_deck.game_deck();
        game_deck.shuffle();

        bank =0;
        int sum_deck_player1 =0;
        int sum_deck_player2 =0;
        //condition pour que la partie continue
        while(sum_deck_player1 <22 && sum_deck_player2 <22) {

            //les deux joueurs piochent une carte
            player1.draw_card(game_deck);
            player2.draw_card(game_deck);

            //recuperation de la taille actuelle du deck des joueur (c'est la meme pour les deux)
            int size_deck_player = player1.get_player_deck().get_deck().size();

            //recuperation de la valeur du deck de chaque joueur
            for (int i = 0; i < size_deck_player; i++) {
                sum_deck_player1 += player1.get_player_deck().get_deck().get(i).get_value();
                sum_deck_player2 += player2.get_player_deck().get_deck().get(i).get_value();
            }
        }
        //condition de fin de partie
        if (sum_deck_player1 >21) {
            System.out.println("Vous avez perdu!");
            player2.add_money(bank);
            end_game();
        }
        if (sum_deck_player2 >21) {
            System.out.println("Vous avez perdu!");
            player1.add_money(bank);
            end_game();
        }

    }
    public void end_game(){

    };
}
