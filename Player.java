
public class Player {
    private String name;
    private boolean is_dealer;
    private Deck player_deck;
    private int money;

    public Player(String _name, Deck _player_deck, boolean _is_dealer){
        name =_name;
        player_deck=_player_deck;
        is_dealer =_is_dealer;
    }

    //getter
    public String get_name() {
        return name;
    }
    public boolean is_dealer() {
        return is_dealer;
    }
    public Deck get_player_deck() {
        return player_deck;
    }
    public int get_money(){return money;}

    //setter
    public void set_name(String _name) {
        this.name = _name;
    }
    public void set_is_dealer(boolean _is_dealer) {
        this.is_dealer = _is_dealer;
    }
    public void set_player_deck(Deck _player_deck) {
        this.player_deck = _player_deck;
    }
    public void set_money(int _money){this.money = _money;}


    //le joueur peut miser avec la methode bet
    public void bet(){  this.money= this.money-1; }
    public void bet(int _amout){this.money=this.money - _amout; }


}
