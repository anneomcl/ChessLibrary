package GUI;
import Game.*;
import Pieces.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;




public class ChessBoardGUI {

    Game game;
    Player currPlayer;
    Piece selectedPiece;
    JButton selectedSquare;
    boolean endTurn = false;

    public static void main(String[] args) throws InterruptedException {
        Chess inst = new Chess();
        inst.gameLoop();
    }


    public synchronized void waitForInput()
    {
        while(!endTurn)
        {
            try{
                wait();
            } catch (InterruptedException e){ e.printStackTrace();}
        }
    }

    public synchronized  void notifyInput()
    {
        endTurn = true;
        notifyAll();
    }

    /**
     * Initializes the GUI.
     */
    public ChessBoardGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Chess");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                ChessWindow window = new ChessWindow();
                frame.add(window);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            JButton button = (JButton) e.getSource();
            Point rv = new Point();
            Piece selection = game.gameBoard.boardArray[button.getLocation(rv).x/100][button.getLocation(rv).y/100];

            if(selection == null || (selectedPiece != null && selection.player != selectedPiece.player && selectedPiece.player == currPlayer))
            {
                if(selectedPiece == null)
                    return;

                else
                {
                    selectedPiece.player.myGame.gameBoard.movePiece(selectedPiece, button.getLocation(rv).x / 100, button.getLocation(rv).y / 100);
                    if(game.capture)
                    {
                        button.setIcon(null);
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                    }
                    else if(game.invalid)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid movement! Please try another move.");
                        game.capture = false;
                        selectedSquare = null;
                        selectedPiece = null;
                        game.invalid = false;
                        return;
                    }

                    else
                    {
                        Icon img = selectedSquare.getIcon();
                        button.setIcon(img);
                        selectedSquare.setIcon(null);
                    }

                    game.capture = false;
                    selectedSquare = null;
                    selectedPiece = null;
                    game.invalid = false;
                    notifyInput();


                }

            }


            else
            {
                if(selection.player == currPlayer) {
                    selectedPiece = game.gameBoard.boardArray[button.getLocation(rv).x / 100][button.getLocation(rv).y / 100];
                    selectedSquare = button;
                }
            }


        }
    }
        /**
         * ChessWindow class that creates the grid layout and populates the grid with buttons
         * that have piece icons on them.
         */
        public class ChessWindow extends JPanel {

            public ChessWindow() {
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        gbc.gridx = col;
                        gbc.gridy = row;

                        Border border = null;
                        if (row < 7) {
                            if (col < 7) {
                                border = new MatteBorder(1, 1, 0, 0, java.awt.Color.GRAY);
                            } else {
                                border = new MatteBorder(1, 1, 0, 1, java.awt.Color.GRAY);
                            }
                        } else {
                            if (col < 7) {
                                border = new MatteBorder(1, 1, 1, 0, java.awt.Color.GRAY);
                            } else {
                                border = new MatteBorder(1, 1, 1, 1, java.awt.Color.GRAY);
                            }
                        }
                        JButton button = new JButton();


                        if (row == 1) {
                            Image blackPawn = null;
                            try {
                                blackPawn = ImageIO.read(getClass().getResource("Assets/blackPawn.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackPawn));
                        }

                        if (row == 6) {
                            Image whitePawn = null;
                            try {
                                whitePawn = ImageIO.read(getClass().getResource("Assets/whitePawn.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whitePawn));
                        }

                        if (row == 0 && (col == 0 || col == 7)) {
                            Image blackRook = null;
                            try {
                                blackRook = ImageIO.read(getClass().getResource("Assets/blackRook.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackRook));
                        }

                        if (row == 7 && (col == 0 || col == 7)) {
                            Image whiteRook = null;
                            try {
                                whiteRook = ImageIO.read(getClass().getResource("Assets/whiteRook.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteRook));
                        }

                        if (row == 0 && (col == 1 || col == 6)) {
                            Image blackKnight = null;
                            try {
                                blackKnight = ImageIO.read(getClass().getResource("Assets/blackKnight.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackKnight));
                        }

                        if (row == 7 && (col == 1 || col == 6)) {
                            Image whiteKnight = null;
                            try {
                                whiteKnight = ImageIO.read(getClass().getResource("Assets/whiteKnight.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteKnight));
                        }

                        if (row == 0 && (col == 2 || col == 5)) {
                            Image blackBishop = null;
                            try {
                                blackBishop = ImageIO.read(getClass().getResource("Assets/blackBishop.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackBishop));
                        }

                        if (row == 7 && (col == 2 || col == 5)) {
                            Image whiteBishop = null;
                            try {
                                whiteBishop = ImageIO.read(getClass().getResource("Assets/whiteBishop.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteBishop));
                        }

                        if (row == 0 && col == 3) {
                            Image blackQueen = null;
                            try {
                                blackQueen = ImageIO.read(getClass().getResource("Assets/blackQueen.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackQueen));
                        }

                        if (row == 7 && col == 3) {
                            Image whiteQueen = null;
                            try {
                                whiteQueen = ImageIO.read(getClass().getResource("Assets/whiteQueen.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteQueen));
                        }

                        if (row == 0 && col == 4) {
                            Image blackKing = null;
                            try {
                                blackKing = ImageIO.read(getClass().getResource("Assets/blackKing.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(blackKing));
                        }

                        if (row == 7 && col == 4) {
                            Image whiteKing = null;
                            try {
                                whiteKing = ImageIO.read(getClass().getResource("Assets/whiteKing.png"));
                            } catch (IOException e) {
                            }
                            button.setIcon(new ImageIcon(whiteKing));
                        }

                        button.setBorderPainted(true);
                        button.setPreferredSize(new Dimension(100, 100));
                        button.setBackground(java.awt.Color.white);
                        MyActionListener mal = new MyActionListener();
                        button.addActionListener(mal);
                        add(button, gbc);


                        //cellPane.setBorder(border);

                    }
                }
            }
        }}