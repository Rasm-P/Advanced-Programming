package meyer2;

import meyerClient.*;
import java.io.IOException;
import meyer2.*;

/**
 *
 * @author Rasmus2
 */
public class MeyerGameClient {

    private static final ClientGameControle GC = ClientGameControle.getInstance();

    public static void main(String[] args) throws IOException {
        GC.playGame();
    }
}
