/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPstart;

import org.junit.Test;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rasmu
 */
public class GreetServerTest {

    GreetClient echoClient = new GreetClient();

    @Before
    public void setup() throws IOException {
//        echoClient.startConnection("127.0.0.1", 4444);
    }

    @After
    public void tearDown() throws IOException {
//        echoClient.stopConnection();
    }

//    @Test
//    public void testGivenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
//        GreetClient client = new GreetClient();
//        client.startConnection("127.0.0.1", 6666);
//        String response = client.sendMessage("hello server");
//        assertEquals("hello client", response);
//    }
//    @Test
//    public void testGivenClient_whenServerEchosMessage_thenCorrect() throws IOException {
//        String resp1 = echoClient.sendMessage("hello");
//        String resp2 = echoClient.sendMessage("world");
//        String resp3 = echoClient.sendMessage("!");
//        String resp4 = echoClient.sendMessage(".");
//
//        assertEquals("hello", resp1);
//        assertEquals("world", resp2);
//        assertEquals("!", resp3);
//        assertEquals("good bye", resp4);
//    }
    @Test
    public void givenClient1_whenServerResponds_thenCorrect() throws IOException {
        GreetClient client1 = new GreetClient();
        client1.startConnection("127.0.0.1", 5555);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() throws IOException {
        GreetClient client2 = new GreetClient();
        client2.startConnection("127.0.0.1", 5555);
        String msg1 = client2.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client2.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

}
