import java.util.Random;
public class Card {
    private String color;
    private int value;
    private String number;

    public Card(){
        color = "";
        value = 0;
        number ="0";
    }
    public Card(int _value,String _color, String _number){
        this.color = _color;
        this.value = _value;
        this.number = _number;
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

    public String number_association(int value){
        switch (value){
            case 1:
                number = "1";
                break;
            case 2:
                number = "2";
                break;
            case 3:
                number = "3";
                break;
            case 4:
                number = "4";
                break;
            case 5:
                number = "5";
                break;
            case 6:
                number = "6";
                break;
            case 7:
                number = "7";
                break;
            case 8:
                number = "8";
                break;
            case 9:
                number = "9";
                break;
            case 10:
                number = "10";
                break;
            case 11:
                number = "J";
                break;
            case 12:
                number = "Q";
                break;
            case 13:
                number = "K";
                break;
        }
        return number;
    }

    public String get_color() {
        return color;
    }

    public int get_value() {
        return value;
    }

    public String get_number(){ return number;}

    public void set_color(String _color) {
        this.color = _color;
    }

    public void set_value(int _value) {
        this.value = _value;
    }

    public void as_value(Player player){
        int sum = 0;
        int size = player.get_player_deck().get_deck().size();
        //on recupere la valeur de la main du joueur
        for (int i=0;i<size;i++){
            sum+=player.get_player_deck().get_deck().get(i).value_card();
        }
        if (sum <=10){
            value = 11;
        }
        else {
            value = 1;
        }
        // cette methode necessitera de regarder si la carte pioché est un 1 ou non à chaque fois
    }
}
