package GUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class ChessBoardGUI {

    public static void main(String[] args) {
        new ChessBoardGUI();
    }

    /**
     * Initializes the GUI.
     */
    public ChessBoardGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {


                JFrame frame = new JFrame("Chess");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ChessWindow());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
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


                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < 7) {
                        if (col < 7) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < 7) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    JButton button = new JButton();


                    if(row == 1)
                    {
                        Image blackPawn = null;
                        try {
                            blackPawn = ImageIO.read(getClass().getResource("Assets/blackPawn.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackPawn));
                    }

                    if(row == 6)
                    {
                        Image whitePawn = null;
                        try {
                            whitePawn = ImageIO.read(getClass().getResource("Assets/whitePawn.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whitePawn));
                    }

                    if(row == 0 && (col == 0 || col == 7))
                    {
                        Image blackRook = null;
                        try {
                            blackRook = ImageIO.read(getClass().getResource("Assets/blackRook.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackRook));
                    }

                    if(row == 7 && (col == 0 || col == 7))
                    {
                        Image whiteRook = null;
                        try {
                            whiteRook = ImageIO.read(getClass().getResource("Assets/whiteRook.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whiteRook));
                    }

                    if(row == 0 && (col == 1 || col == 6))
                    {
                        Image blackKnight = null;
                        try {
                            blackKnight = ImageIO.read(getClass().getResource("Assets/blackKnight.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackKnight));
                    }

                    if(row == 7 && (col == 1 || col == 6))
                    {
                        Image whiteKnight = null;
                        try {
                            whiteKnight = ImageIO.read(getClass().getResource("Assets/whiteKnight.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whiteKnight));
                    }

                    if(row == 0 && (col == 2 || col == 5))
                    {
                        Image blackBishop = null;
                        try {
                            blackBishop = ImageIO.read(getClass().getResource("Assets/blackBishop.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackBishop));
                    }

                    if(row == 7 && (col == 2 || col == 5))
                    {
                        Image whiteBishop = null;
                        try {
                            whiteBishop = ImageIO.read(getClass().getResource("Assets/whiteBishop.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whiteBishop));
                    }

                    if(row == 0 && col == 3)
                    {
                        Image blackQueen = null;
                        try {
                            blackQueen = ImageIO.read(getClass().getResource("Assets/blackQueen.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackQueen));
                    }

                    if(row == 7 && col == 3)
                    {
                        Image whiteQueen = null;
                        try {
                            whiteQueen = ImageIO.read(getClass().getResource("Assets/whiteQueen.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whiteQueen));
                    }

                    if(row == 0 && col == 4)
                    {
                        Image blackKing = null;
                        try {
                            blackKing = ImageIO.read(getClass().getResource("Assets/blackKing.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(blackKing));
                    }

                    if(row == 7 && col == 4)
                    {
                        Image whiteKing = null;
                        try {
                            whiteKing = ImageIO.read(getClass().getResource("Assets/whiteKing.png"));
                        } catch (IOException e) {
                        }
                        button.setIcon(new ImageIcon(whiteKing));
                    }

                    button.setBorderPainted(true);
                    button.setPreferredSize(new Dimension(100, 100));
                    button.setBackground(Color.white);
                    add(button, gbc);


                    cellPane.setBorder(border);

                }
            }
        }
    }

    public class CellPane extends JPanel {

        private Color defaultBackground;

        public CellPane() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e){

                }

                @Override
                public void mouseReleased(MouseEvent e){

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(defaultBackground);
                }
            });
        }

    }
}