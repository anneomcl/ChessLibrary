package GUI;

import Game.Game;
import Game.*;
import javax.swing.*;

/**
 * Created by Anne on 9/26/2014.
 */
public class Chess {

    public void gameLoop()
    {
        ChessBoardGUI gui = new ChessBoardGUI();
        gui.game = new Game();

        if (gui.game.player1.goesFirst && gui.game.turn % 2 != 0)
        {
            gui.currPlayer = gui.game.player1;
            gui.currPlayer.isTurn = true;
        }
        else if(gui.game.player2.goesFirst && gui.game.turn%2 ==0)
        {
            gui.currPlayer = gui.game.player1;
            gui.currPlayer.isTurn = true;
        }
        else {
            gui.currPlayer = gui.game.player2;
            gui.currPlayer.isTurn = true;
        }

        JOptionPane.showMessageDialog(null, gui.currPlayer.playerColor + " goes first!");

        while (!gui.game.player1.isLoser && !gui.game.player2.isLoser) {

            //JOptionPane.showMessageDialog(null, currPlayer.playerColor + ", make your move.");
            String[] buttons = { "OK", "Tie", "I give up" };
            int returnValue = JOptionPane.showOptionDialog(null, gui.currPlayer.playerColor + ", make your move.", "TURN",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
            System.out.println(returnValue);
            if(returnValue == 1)
            {
                gui.game.player1.score++;
                gui.game.player2.score++;
                buttons = new String[]{"Yes", "No"};
                returnValue = JOptionPane.showOptionDialog(null, "Game is tied. Play a new game?", "GAME OVER",
                        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                if(returnValue == 0)
                {
                    buttons = new String[]{"Yes", "No"};
                    returnValue = JOptionPane.showOptionDialog(null, "Opponent, do you agree to restart the game?", "GAME OVER",
                            JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                    if (returnValue == 0)
                    {
                        gameLoop();
                    }


                }
            }
            if(returnValue == 2)
            {
                gui.currPlayer.isLoser = true;
                if(gui.currPlayer == gui.game.player1)
                    gui.game.player2.score++;
                else
                    gui.game.player1.score++;
                buttons = new String[]{"Yes", "No"};
                returnValue = JOptionPane.showOptionDialog(null, "Play a new game?", "GAME OVER",
                        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
                if(returnValue == 1)
                    System.exit(0);
                else
                {


                    gameLoop();
                }
            }
            gui.waitForInput();

            if(gui.game.invalid)
            {
                gui.endTurn = false;
                gui.game.invalid = false;
            }

            else
            {
                gui.currPlayer.isTurn = false;
                if(gui.currPlayer == gui.game.player1)
                    gui.currPlayer = gui.game.player2;
                else
                    gui.currPlayer = gui.game.player1;
                gui.endTurn = false;

                gui.game.turn++;
            }
        }

        String[] buttons = new String[]{"Yes", "No"};
        int returnValue = JOptionPane.showOptionDialog(null, "CHECKMATE. Play a new game?", "GAME OVER",
                JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[0]);
        if(returnValue == 1)
            System.exit(0);
        else
        {
            gameLoop();
        }
    }
}
