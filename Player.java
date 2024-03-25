
public class Player {
    private String name;
    private boolean is_dealer;
    private Deck player_deck;

    public Player(String _name, Deck _player_deck, boolean _is_dealer){
        name =_name;
        player_deck=_player_deck;
        is_dealer =_is_dealer;
    }


}
