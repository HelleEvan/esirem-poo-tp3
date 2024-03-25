public class Deck extends Card{
    private Card[] deck;
    //constucteur par defaut
    public Deck(){
        deck=null;
    }
    public void game_deck(){
        int couter = 0;
        //on va creer le jeu de carte
        for(int i=1;i<14;i++) {
            for (int j = 0; j < 4; j++) {
                Card carte = new Card();
                String color_tmp = carte.color_association(j);
                carte.set_value(i);
                carte.set_color(color_tmp);
                deck[couter] = carte;
                couter++;
            }
        }
    }



}
