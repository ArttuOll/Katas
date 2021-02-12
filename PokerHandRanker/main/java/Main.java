public class Main {

    public static void main(String[] args) {
        PokerHand smaller = new PokerHand("1D 2C 3H 4A 5S");
        PokerHand higher = new PokerHand("1A 2A 3A 4A 5A");
        System.out.println(smaller.compareWith(higher));
    }
}
