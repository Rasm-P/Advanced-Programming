package meyer2;

/**
 *
 * @author Rasmus2
 */
public class MeyerGame {

    private static final GameControleImpl GC = GameControleImpl.getInstance();

    public static void main(String[] args) {
        GC.playGame();
    }
}
