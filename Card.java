import java.util.Random;
public class Card {
    private int card;
    private String color;
    private int value;

    public Card(){
        card=0;
    }
    public int value_card(){
        // génération d'un nombre > 0 et < 14
        Random r = new Random();
        value = r.nextInt(13)+1;
        return value;

    }
    public String color_card(){
        // génération d'un nombre >= 0 et < 4
        Random r = new Random();
        int n = r.nextInt(4);
        //association de la valeur à un couleur
        switch (n){
            case 0:
                color = "Coeur";
                break;
            case 1:
                color = "Carreau";
                break;
            case 2:
                color = "Pique";
                break;
            case 3:
                color = "Trefle";

        }
        return color;
    }
    public Card generation_card(){
        //generation d'une carte et ses attributs
        Card carte = new Card();
        value = carte.value_card();
        color = carte.color_card();
        return carte;
    }

    public String get_color() {
        return color;
    }

    public int get_value() {
        return value;
    }

}
