package advprg.meyer.impl;

import advprg.meyer.MeyerGameCtrl;
import advprg.meyer.MeyerPlayer;
import advprg.meyer.MeyerRoll;
import advprg.meyer.MeyerRollFactory;

import java.util.List;
import java.util.Random;

public class MeyerGameCtrlImpl implements MeyerGameCtrl
{
    @Override
    public MeyerPlayer playGame(List<MeyerPlayer> players)
    {
        MeyerRollFactory rollFactory = MeyerRollFactoryImpl.getInstance();
        Random rnd = new Random();
        int curPlayerIndex = rnd.nextInt(players.size());
        MeyerPlayer curPlayer = players.get(curPlayerIndex);
        while (players.size() > 1)
        {
            MeyerRoll actualRoll = null;
            MeyerRoll claimedRoll = null;
            actualRoll = rollFactory.getRollFromDice(rnd.nextInt(6) + 1, rnd.nextInt(6) + 1);
            claimedRoll = curPlayer.makeFirstRoll(actualRoll);
            curPlayerIndex = nextPlayerIndex(curPlayerIndex, players);
            boolean sameOrAbove = false;
            while (true)
            {
                curPlayer = players.get(curPlayerIndex);
                if (curPlayer.flip(claimedRoll))
                {
                    if (    ( sameOrAbove && actualRoll.getValue() < claimedRoll.getValue() ) ||
                            ( !sameOrAbove && claimedRoll.getValue() != actualRoll.getValue() )     )
                    {
                        curPlayerIndex = prevPlayerIndex(curPlayerIndex, players);
                        curPlayer = players.get(curPlayerIndex);
                    }
                    looseLife(players, curPlayer, claimedRoll);
                    if (curPlayerIndex >= players.size())
                    {
                        curPlayerIndex = 0;
                    }
                    break;
                }
                actualRoll = rollFactory.getRollFromDice(rnd.nextInt(6) + 1, rnd.nextInt(6) + 1);
                MeyerRoll choice = curPlayer.makeRoll(claimedRoll, actualRoll);
                if (choice == null)
                {
                    //Reroll
                    actualRoll = rollFactory.getRollFromDice(rnd.nextInt(6) + 1, rnd.nextInt(6) + 1);
                    sameOrAbove = true;
                } else
                {
                    claimedRoll = choice;
                    sameOrAbove = false;
                }
                curPlayerIndex = nextPlayerIndex(curPlayerIndex, players);
            }
        }
        return players.get(0);
    }

    private int nextPlayerIndex(int curPlayerIndex, List<MeyerPlayer> players)
    {
        int res = ++curPlayerIndex;
        if (res >= players.size())
        {
            res = 0;
        }
        return res;
    }

    private int prevPlayerIndex(int curPlayerIndex, List<MeyerPlayer> players)
    {
        int res = curPlayerIndex - 1;
        if (res < 0)
        {
            res = players.size() - 1;
        }
        return res;
    }

    private void looseLife(List<MeyerPlayer> players, MeyerPlayer player, MeyerRoll claimedRoll)
    {
        if (claimedRoll.getValue() == 21)
        {
            player.looseLife(2);
        } else
        {
            player.looseLife(1);
        }
        if (player.getLife() <= 0)
        {
            int place = players.size();
            players.remove(player);
            player.gameOver(place);
        }
    }
}
