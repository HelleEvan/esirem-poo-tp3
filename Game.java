import java.util.Scanner;

public class Game {
    private Player player1;
    private Player dealer;
    private int bank;

    public Game(Player _player1, Player _dealer) {
        player1 = _player1;
        dealer = _dealer;

        bank = 0;
    }

    public int main_menu(){
        System.out.println("Bienvenue chez Black-Jack BABA!\nQue voulez-vous faire?");
        System.out.println("Jouer au jeu (1)\nQuitter le jeu (2)");
        Scanner user_input = new Scanner(System.in);
        String user_choice = user_input.nextLine();
        if(user_choice.equals("1")){
            start_game();
        } else if (user_choice.equals("2")) {
            System.out.println(" Au revoir, Revenez vite!");
            return 0;
        }
        return 0;
    }

    public void start_game() {
        int player1_bank = player1.get_money();
        int user_bet=0;
        Scanner player_input = new Scanner(System.in);
        Scanner player_bet = new Scanner(System.in);

        System.out.println("Il vous reste,"+player1_bank+" coins, Voulez vous en miser (y/n): ");
        String user_response = player_input.nextLine();
        if (user_response.equals("y")) {
            do {
                System.out.println("Combien voulez vous miser?\nVous pouvez misez à hauteur de: "+player1_bank+" coins");
                user_bet = player_input.nextInt();
            } while(user_bet>player1_bank);

            player1.bet(user_bet);
            player1_bank -= user_bet;
            bank+=user_bet;
            System.out.println("Il vous reste désormais "+player1_bank+" coins");

            continue_game();

        } else if (user_response.equals("n")) {
            end_game_draw();
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
            dealer.draw_card(game_deck);

            //recuperation de la taille actuelle du deck des joueur (c'est la meme pour les deux)
            int size_deck_player = player1.get_player_deck().get_deck().size();

            //recuperation de la valeur du deck de chaque joueur
            for (int i = 0; i < size_deck_player; i++) {
                sum_deck_player1 += player1.get_player_deck().get_deck().get(i).get_value();
                sum_deck_player2 += dealer.get_player_deck().get_deck().get(i).get_value();
            }
            if (sum_deck_player1 == 21){

            }
        }
        //condition de fin de partie
        if (sum_deck_player1 >21) {
            end_game(dealer,player1);
        }
        if (sum_deck_player2 >21) {
            end_game(player1, dealer);
        }

    }
    public void end_game(Player _winner,Player _loser){
        winner(_winner);
        loser(_loser);
    };
    public void end_game_draw(){
        System.out.println("Match nul");
    }

    public void winner(Player _winner){
        _winner.add_money(bank);
    }
    public void loser(Player _loser){
        System.out.println("Vous avez perdu!");
    }

    public void assurance(int player_bet){
        //condition sur la premiere carte du croupier
                Scanner player_response = new Scanner(System.in);
                System.out.println("Est-ce que voulez-vous assurer? (cela vous permet de vous proteger d'un eventuel blackjack du croupier) (y/n)");
                String user_response = player_response.nextLine();
                if(user_response.equals("y")) {
                    player1.bet(player_bet / 2);
                    bank+=player_bet/2;
                    //verification de la deusiemme carte du croupier (si c'est un figure, c'est un blackjack)
                    if (dealer.get_player_deck().is_blackjack()) {
                        player1.add_money(player_bet);
                        bank-=player_bet/2;
                    }
                    else {
                        System.out.println("Le croupier n'a pas eu de blackjack, vous avez donc perdu votre assurance");
                        if(player1.get_player_deck().is_blackjack()){
                            player1.add_money(player_bet);
                            bank-=player_bet;
                        }
                    }
                }


    }
}
