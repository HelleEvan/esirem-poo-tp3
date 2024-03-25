import java.util.Random;
public class Card {
    private String color;
    private int value;

    public Card(){
        color = "";
        value = 0;
    }
    public Card(int _value,String _color){
        this.color = _color;
        this.value = _value;
    }
    public int value_card(){
        // génération d'un nombre > 0 et < 14
        Random r = new Random();
        value = r.nextInt(13)+1;
        return value;

    }
    public int color_card(){
        // génération d'un nombre >= 0 et < 4
        Random r = new Random();
        int n = r.nextInt(4);
        return n;
    }
    public String color_association(int value_color ){
        //association de la valeur à un couleur
        switch (value_color){
            case 0:
                color = "♥";
                break;
            case 1:
                color = "♦";
                break;
            case 2:
                color = "♠";
                break;
            case 3:
                color = "♣";

        }
        return color;
    }
    public Card generation_card(){
        //generation d'une carte et ses attributs
        Card carte = new Card();
        value = carte.value_card();
        int color_card = carte.color_card();
        color = carte.color_association(color_card);
        return carte;
    }

    public String get_color() {
        return color;
    }

    public int get_value() {
        return value;
    }

    public void set_color(String _color) {
        this.color = _color;
    }

    public void set_value(int _value) {
        this.value = _value;
    }
}
