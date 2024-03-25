
import java.util.ArrayList;
public class Deck extends Card{
    private ArrayList<Card> tab_card = new ArrayList<Card>();
    //constucteur par defaut
    public Deck(){
        //constructeur par defaut
        Card carte1 = new Card();
        tab_card.add(carte1);
    }
    public void game_deck(){
        //on va creer le jeu de carte
        for(int i=1;i<14;i++) {
            for (int j = 0; j < 4; j++) {
                Card carte = new Card();
                String color_tmp = carte.color_association(j);
                carte.set_value(i);
                carte.set_color(color_tmp);
                tab_card.add(carte);

            }
        }
        tab_card.remove(0);
    }
    public ArrayList<Card> get_deck(){
        return tab_card;
    }
    public void display_deck(){
        for(int i = 0; i< tab_card.size(); i++){
            System.out.println(tab_card.get(i).get_value()+tab_card.get(i).get_color());
        }
    }
    public void shuffle(){

    }


}
