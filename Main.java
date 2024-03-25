public class Main {
    public static void main (String[] args){
        Card carte = new Card();
        Card carte1 = new Card();
        carte1= carte.generation_card();
        System.out.println(carte1.get_value()+carte1.get_color());
    }

}
