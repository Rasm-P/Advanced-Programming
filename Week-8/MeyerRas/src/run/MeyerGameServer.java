package run;

import meyer.MeyerGameCtrl;
import meyer.MeyerPlayer;
import impl.MeyerGameCtrlImpl;
import impl.MeyerPlayerIOImpl;
import impl.MeyerPlayerImpl;
import textgame.ITextGame;
import textgame.TextGameServer;
import textio.ITextIO;
import textio.SysTextIO;

import java.util.ArrayList;
import java.util.List;

public class MeyerGameServer implements ITextGame
{
    private final MeyerGameCtrl gameCtrl;
    private final ITextIO serverConsole;

    public MeyerGameServer(MeyerGameCtrl gameCtrl)
    {
        this.gameCtrl = gameCtrl;
        this.serverConsole = new SysTextIO();
    }

    @Override
    public int getNumberOfPlayers()
    {
        serverConsole.put("How many players? (2-10): ");
        return serverConsole.getInteger(2, 10);
    }

    @Override
    public void startGame(ITextIO[] players)
    {
        List<MeyerPlayer> meyerPlayers = new ArrayList<>();
        for(ITextIO p : players)
        {
            meyerPlayers.add(new MeyerPlayerImpl(new MeyerPlayerIOImpl(p)));
        }
        int i = 1;
        for(MeyerPlayer p : meyerPlayers)
        {
            serverConsole.put("Player " + (i++) + ": " + p.getName() + "\n");
        }
        MeyerPlayer winner = gameCtrl.playGame(meyerPlayers);
        for(ITextIO p : players)
        {
            p.put("The game is over. The winner was " + winner.getName() + "\n");
        }
    }

    public static void main(String[] args)
    {
        int port = 3737;
        TextGameServer server = new TextGameServer(new MeyerGameServer(new MeyerGameCtrlImpl()), port);
        server.run();
    }
}
