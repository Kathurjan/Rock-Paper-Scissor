package rps.bll.player;

//Project imports
import rps.bll.game.*;
import rps.gui.controller.GameViewController;

//Java imports
import java.util.ArrayList;
import java.util.Random;

import static rps.bll.player.PlayerType.AI;
import static rps.bll.player.PlayerType.Human;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private GameViewController gv;
    private GameManager ge;
    private IPlayer player;
    private IPlayer AI;
    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
        ge = new GameManager(player,AI);
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }

    public ArrayList<Move> getMoveResult(){
        ArrayList<Move> moveResult = new ArrayList<>();
        for ( Result rs: ge.getGameState().getHistoricResults()){
            if (rs.getWinnerPlayer().getPlayerType() == Human && rs.getType() != ResultType.Tie){
                if (rs.getWinnerMove().equals(Move.Rock)){
                    moveResult.add(Move.Paper);
                }
                if (rs.getWinnerMove().equals(Move.Paper)){
                    moveResult.add(Move.Scissor);
                }
                if (rs.getWinnerMove().equals(Move.Scissor)){
                    moveResult.add(Move.Rock);
                }

            }
        }
        moveResult.add(Move.Rock);
        moveResult.add(Move.Paper);
        moveResult.add(Move.Scissor);

        return moveResult;

    }



    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {


        // working ai, very simple version ofc.
        Move AiMove;
        Random random = new Random();
        int randmove = random.nextInt(getMoveResult().size());
        AiMove = getMoveResult().get(randmove);

        // used for the first test.
       /*  Random random = new Random();
       int result = random.nextInt(3) + 1;
        switch (result) {
            case 1:
                AiMove = Move.Rock;
                break;
            case 2:
                AiMove = Move.Paper;
                break;
            default:
                AiMove = Move.Scissor;
                break;
                
            
        }
        */
        return AiMove;
    }
}
