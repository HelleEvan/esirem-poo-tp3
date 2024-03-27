
import java.util.ArrayList;
import java.util.Random;

public class Deck extends Card{
    private ArrayList<Card> tab_card = new ArrayList<Card>();
    //constucteur par defaut
    public Deck(){
        //constructeur par defaut
        Card carte1 = new Card();
        tab_card.add(carte1);
        tab_card.remove(0);
    }
    public void game_deck(){
        //on va creer le jeu de carte
        for(int i=1;i<14;i++) {
            for (int j = 0; j < 4; j++) {
                Card carte = new Card();
                String color_tmp = carte.color_association(j);
                if(i>9) {
                    carte.set_value(10);
                }
                else{
                    carte.set_value(i);
                }
                carte.set_color(color_tmp);
                tab_card.add(carte);

            }
        }
    }
    public ArrayList<Card> get_deck(){
        return tab_card;
    }
    public void display_deck(){
        for(int i = 0; i< tab_card.size(); i++){
             int value = tab_card.get(i).get_value();
             String card_number = number_association(value);
            System.out.print( card_number+tab_card.get(i).get_color()+" ");
            if(i== tab_card.size()-1){
                System.out.println("\n");
            }
        }
    }
    public void shuffle(){

        for(int i=0;i<tab_card.size();i++){
            // génération d'un nombre >= 0 et < 53
            Random r = new Random();
            int n = r.nextInt(52);

            Card tmp =new Card();
            Card tmp1 =new Card();

            //on echange la i eme carte et celle d'une position random
            tmp= tab_card.get(i);
            tmp1 = tab_card.get(n);
            tab_card.set(n,tmp);
            tab_card.set(i,tmp1);
        }
    }
    public void add_card(Card carte){
        //ajout d'une carte au bout d'une pile de carte
        tab_card.add(carte);
    }
    public Card draw_card(){
        // methode pour piocher une carte et la retirer de la pile
        Card carte_withdrew =new Card();
        int size = tab_card.size();
        carte_withdrew = tab_card.get(size-1);
        tab_card.remove(size-1);
        return carte_withdrew;

    }

    public boolean is_blackjack(){
        if ((tab_card.get(0).get_number().equals("1") && tab_card.get(1).get_number().equals("J")) || (tab_card.get(0).get_number().equals("1") && tab_card.get(1).get_number().equals("Q"))  || (tab_card.get(0).get_number().equals("1") && tab_card.get(1).get_number().equals("K"))){
            return true;
        }
        else{
            return false;}
    }

}
