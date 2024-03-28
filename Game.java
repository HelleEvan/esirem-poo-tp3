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
        int user_bet=0;
        player1.get_player_deck().delete_deck();
        dealer.get_player_deck().delete_deck();
        Scanner player_input = new Scanner(System.in);
        Scanner player_bet = new Scanner(System.in);

        System.out.println("Il vous reste,"+player1.get_money()+" coins, Voulez vous en miser (y/n): ");
        String user_response = player_input.nextLine();
        if (user_response.equals("y")) {
            do {
                System.out.println("Combien voulez vous miser?\nVous pouvez miser à hauteur de: "+player1.get_money()+" coins");
                user_bet = player_input.nextInt();

                if(user_bet>player1.get_money()){
                    System.out.println("Erreur, vous avez trop misé!!!");
                }
            } while(user_bet>player1.get_money());
            System.out.println("Vous avez  miser " +user_bet+", le croupier a miser la meme valeur que vous soit, " +user_bet+".");
            player1.bet(user_bet);
            //ajout a la bank la mise du joueur et du croupier (qui est la meme que la mise du joueur)
            bank+=user_bet;
            bank+=user_bet;
            System.out.println("Il vous reste désormais "+player1.get_money()+" coins");

            continue_game(user_bet);

        } else if (user_response.equals("n")) {
            end_game_draw();
        }

    }
    public void continue_game(int _player_bet) {
        //cretion du la pile de carte, puis shuffle
        Deck game_deck = new Deck();
        Card carte = new Card();
        game_deck.game_deck();
        game_deck.shuffle();


        int sum_deck_player1 =0;
        int sum_deck_player2 =0;
        int round = 0;
        //bool pour savoir si un joueur à pioché ou non
        boolean player_draw;
        boolean dealer_draw;
        //condition pour que la partie continue
        while(sum_deck_player1 <=21 && sum_deck_player2 <=21) {

            round +=1;
            System.out.println("\nround n°"+round);

            //les deux joueurs piochent une carte au premier round
            if(round==1) {
                player1.draw_card(game_deck);
                player_draw = true;
                dealer.draw_card(game_deck);
                dealer_draw = true;
            }
            else {
                System.out.println("Voulez vous piocher une carte? (y/n)");
                Scanner user_input = new Scanner(System.in);
                String player_decision = user_input.nextLine();
                if(player_decision.equals("y")){
                    player1.draw_card(game_deck);
                    player_draw = true;
                }
                if(sum_deck_player2 <=17){
                    dealer.draw_card(game_deck);
                    dealer_draw = true;
                }
                player_draw = false;
                dealer_draw = false;
            }



            //recuperation de la taille actuelle du deck des joueurs
            int size_deck_player = player1.get_player_deck().get_deck().size();
            int size_deck_dealer = dealer.get_player_deck().get_deck().size();

            int value1 = player1.get_player_deck().get_deck().get(size_deck_player-1).get_value();
            int value2 = dealer.get_player_deck().get_deck().get(size_deck_dealer-1).get_value();
            String number1 = carte.number_association(value1);
            String number2 = carte.number_association(value2);

            if(player_draw){
                System.out.println("Carte pioché: "+ number1 +player1.get_player_deck().get_deck().get(size_deck_player-1).get_color());
            }
            if(dealer_draw){
                System.out.println("Carte pioché par le croupier: "+ number2 +dealer.get_player_deck().get_deck().get(size_deck_dealer-1).get_color());
            }

            System.out.println("Votre main:");
            player1.get_player_deck().display_deck();

            System.out.println("Main du croupier:");
            dealer.get_player_deck().display_deck();

            //recuperation de la valeur du deck de chaque joueur
            sum_deck_player1 =0;
            sum_deck_player2 =0;
            int value_card_player;
            int value_card_dealer;
            for (int i = 0; i < size_deck_player; i++) {
                value_card_player= player1.get_player_deck().get_deck().get(i).get_value();
                sum_deck_player1+= carte.conv_value(value_card_player);
            }
            for (int i = 0; i < size_deck_dealer; i++) {
                value_card_dealer = dealer.get_player_deck().get_deck().get(i).get_value();
                sum_deck_player2 +=carte.conv_value(value_card_dealer);
            }
            System.out.println("La somme de votre main est: "+sum_deck_player1);
            System.out.println("La somme de la main du croupier: "+sum_deck_player2);
        }
        end_game(sum_deck_player1,sum_deck_player2, _player_bet);

    }
    public void end_game(int _sum_deck_player1, int _sum_deck_player2, int _player_bet){
        //condition si le joueur et le coupier ont > à 21 tous les deux
        if(player1.get_player_deck().is_blackjack()){
            if (dealer.get_player_deck().is_blackjack()){
                player1.add_money(_player_bet);
                bank-=_player_bet;
                end_game_draw();
            }
            else {
                player1.add_money(_player_bet+(_player_bet/2));
                bank-=_player_bet+(_player_bet/2);}

        }

        //si le croupier depasse 21 points, le joueur recupere sa mise et celle du croupier
        if (_sum_deck_player2>21){
            player1.add_money(bank);
            bank=0;
            System.out.println("Vous avez ganger, le croupier a depasser 21 points. Votre solde est: "+ player1.get_money());
        }
        if (_sum_deck_player1 >21){
            System.out.println("Vous avez plus que 21 points! Vous avez donc perdu votre mise de " +_player_bet+" !");
        }

        //les joueurs qui ont 21 points ou moins sans blackjack
        if (_sum_deck_player1<=21 && !player1.get_player_deck().is_blackjack()){
            if (_sum_deck_player2>21){
                player1.add_money(_player_bet);
            }
            if (_sum_deck_player2<21){
                if (_sum_deck_player1<_sum_deck_player2){
                    System.out.println("Vous avez moins de points que le croupier! Vous avez donc perdu votre mise de "+_player_bet+" !");
                }
                if (_sum_deck_player1 == _sum_deck_player2){
                    System.out.println("PUSH !! Vous avez autant de points que le croupier ! Vous recuperer donc votre mise de "+_player_bet+" !");
                    player1.add_money(_player_bet);
                    bank-=_player_bet;
                }
                if (_sum_deck_player1>_sum_deck_player2){
                    System.out.println("Vous avez plus de points que le croupier ! Vous recuperer donc votre mise de "+_player_bet+" !");
                    player1.add_money(_player_bet);
                    bank-=_player_bet;
                }
            }
        }
        end_menu();
    };
    public void end_game_draw(){
        System.out.println("Match nul");
    }
    public void end_menu(){
        System.out.println("Rejouer (1)");
        System.out.println("Acceder a votre solde (2)");
        System.out.println("Acceder au socre (3)");
        System.out.println("Quitter (4)");
        Scanner user_input = new Scanner(System.in);
        String user_choice = user_input.nextLine();
        switch (user_choice){
            case "1":
                start_game();
                break;
            case "2":
                System.out.println("Voici votre solde : "+player1.get_money());
                end_menu();
                break;
            case "3":
                break;
            case "4":
                break;

        }

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
                        System.out.println("Le croupier a eu un blackjack, vous avez donc perdu votre assurance");
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
