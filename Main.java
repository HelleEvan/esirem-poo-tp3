public class Main {
    public static void main (String[] args){
        Deck deck_player1 = new Deck();
        Deck deck_dealer= new Deck();
        Player joueur1 = new Player("Joueur1",deck_player1 , false,100);
        Player dealer = new Player("croupier",deck_dealer , true,100);
        Game jeu = new Game(joueur1,dealer);
        jeu.start_game();
    }

}
