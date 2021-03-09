package Lab1;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Pic2D.pic2D.repaint(g);
    }

}

