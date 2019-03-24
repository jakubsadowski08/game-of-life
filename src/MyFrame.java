import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {

    private static final int ROWS = 50;
    private static final int COLS = 50;
    private JButton[][] squares = new JButton[50][50];
    private int [][] neighbours = new int[50][50];

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                update();
            }
            return false;
        }
    }


    class Button extends JButton implements MouseListener {

        Button() {
            super.setBackground(Color.WHITE);
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(super.getBackground().getRGB() == Color.BLACK.getRGB())
            {
                super.setBackground(Color.WHITE);
            }
            else
            {
                super.setBackground(Color.BLACK);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public int alive(int[][] indexes)
    {
        int count = 0;
        for(int i =0;i < indexes.length;i++)
        {
            if(squares[indexes[i][0]][indexes[i][1]].getBackground().getRGB() == Color.BLACK.getRGB())
            {
                count++;
            }
        }
        return count;
    }
    public void getNeighbours(int i, int j)
    {
        int count = 0;
        if(i ==0)
        {
            if(j == 0)
            {
                count = alive(new int[][]{{0,1},{1,0},{1,1}});
            }
            else if(j == COLS- 1)
            {
                count = alive(new int[][]{{0,j-1},{1,j-1},{1,j}});
            }
            else
            {
                count = alive(new int[][]{{0,j-1},{0,j+1},{1,j-1},{1,j},{1,j+1}});
            }
        }
        else if(i == ROWS-1)
        {
            if(j == 0)
            {
                count = alive(new int[][]{{i,1},{i-1,0},{i-1,1}});
            }
            else if(j == COLS- 1)
            {
                count = alive(new int[][]{{i,j-1},{i-1,j-1},{i-1,j}});
            }
            else
            {
                count = alive(new int[][]{{i,j-1},{i,j+1},{i-1,j-1},{i-1,j},{i-1,j+1}});
            }
        }
        else
        {
            if(j == 0)
            {
                count = alive(new int[][]{{i-1,j},{i+1,j},{i-1,j+1},{i,j+1},{i+1,j+1}});
            }
            else if(j == COLS- 1)
            {
                count = alive(new int[][]{{i-1,j},{i+1,j},{i-1,j-1},{i,j-1},{i+1,j-1}});
            }
            else
            {
                count = alive(new int[][]{{i-1,j-1},{i-1,j},{i-1,j+1},{i,j-1},{i,j+1},{i+1,j-1},{i+1,j},{i+1,j+1}});
            }
        }
        neighbours[i][j] = count;
    }

    public void update()
    {

        for(int i=0; i<50; i++)
        {
            for(int j=0;j < 50; j++)
            {
                getNeighbours(i,j);
            }
        }
        for(int i=0; i<50; i++)
        {
            for(int j=0;j < 50; j++)
            {
                if(neighbours[i][j] == 3)
                {
                    squares[i][j].setBackground(Color.BLACK);
                }
                else if(neighbours[i][j] == 2 && squares[i][j].getBackground().getRGB() == Color.BLACK.getRGB())
                {
                    squares[i][j].setBackground(Color.BLACK);
                }
                else if(neighbours[i][j] != 2 && squares[i][j].getBackground().getRGB() == Color.BLACK.getRGB())
                {
                    squares[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public MyFrame() throws InterruptedException {
        super( "Game Of Life" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new GridLayout(50, 50));
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());


        for(int i=0; i<50; i++)
        {
            for(int j=0;j < 50; j++)
            {
                Button b = new Button();
                squares[i][j] = b;
                add(squares[i][j]);
            }
        }


        setVisible(true);


    }
}