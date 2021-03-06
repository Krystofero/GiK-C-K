package Lab1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Pic2D implements ActionListener {

    public static Pic2D pic2D;
    public final int WIDTH = 1200, HEIGHT = 800; //Height rośnie od gory w dół , a width od lewej do prawej  (podloga na HEIGHT = 800-120 = 780)
    public int radius = 60;
    public Render renderer;

    public Pic2D()
    {
        JFrame jframe = new JFrame();

        renderer = new Render();

        jframe.add(renderer);
        jframe.setTitle("2D");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(true);
        jframe.setLocationRelativeTo(null); //Okno pojawi sie na środku ekranu
        jframe.setVisible(true);

    }

//    public void paintCar1(Graphics g, int x , int y)
//    {
//        g.setColor(Color.darkGray);
//        g.fillRect( x, y, 150, 80);
//        g.setColor(Color.black);
//        g.fillOval(x+10, y+58, radius * 2 -80, radius * 2 -80);
//        g.fillOval(x+100, y+58, radius * 2 -80, radius * 2 -80);
//        g.setColor(Color.cyan.darker());
//        g.fillRect( x, y, 110, 40);
//    }
//    public void paintCar2(Graphics g, int x, int y)
//    {
//        g.setColor(Color.red.darker());
//        g.fillRect( 750, 492, 150, 80);
//        g.setColor(Color.black);
//        g.fillOval(760, 550, radius * 2 -80, radius * 2 -80);
//        g.fillOval(850, 550, radius * 2 -80, radius * 2 -80);
//        g.setColor(Color.cyan.darker());
//        g.fillRect( 750, 492, 110, 40);
//    }
//    public void paintCar3(Graphics g)
//    {
//        g.setColor(Color.yellow.darker());
//        g.fillRect( 500, 620, 150, 80);
//        g.setColor(Color.black);
//        g.fillOval(510, 678, radius * 2 -80, radius * 2 -80);
//        g.fillOval(600, 678, radius * 2 -80, radius * 2 -80);
//        g.setColor(Color.cyan.darker());
//        g.fillRect( 540, 620, 110, 40);
//    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        renderer.repaint(); 	//ponownie maluje wszystkie elementy w oknie
    }

    public void repaint(Graphics g)
    {   //tło
        g.setColor(Color.cyan);
        g.fillRect( 0, 0, WIDTH, HEIGHT);
        //droga
        g.setColor(Color.lightGray);
        g.fillRect( 0, HEIGHT - 320, WIDTH, 300);
        //pasy
        g.setColor(Color.white);
        g.fillRect( 700, HEIGHT - 190, 150, 20);
        g.fillRect( 400, HEIGHT - 190, 150, 20);
        g.fillRect( 100, HEIGHT - 190, 150, 20);
        g.fillRect( 1000, HEIGHT - 190, 150, 20);
        //góry
        g.setColor(Color.green.darker());
        g.fillOval((WIDTH / 2) - radius, (HEIGHT / 2) - (radius), radius * 2 +250, radius * 2);
        g.fillOval((WIDTH / 2) - radius + 50 , (HEIGHT / 2) - (radius) - 10, radius * 2 +50, radius * 2);
        g.fillOval((WIDTH / 2) - radius - 450, (HEIGHT / 2) - (radius) - 10, radius * 2 +250, radius * 2);
        g.fillOval((WIDTH / 2) - radius - 400 , (HEIGHT / 2) - (radius) - 20, radius * 2 +100, radius * 2);
        g.fillOval((WIDTH / 2) - radius + 500 , (HEIGHT / 2) - (radius) - 10, radius * 2 +300, radius * 2);
        g.fillOval((WIDTH / 2) - radius + 550 , (HEIGHT / 2) - (radius) - 20, radius * 2 +300, radius * 2);
        //trawa
        g.fillRect( 0, HEIGHT - 440, WIDTH, 120);
        //Słońce
        g.setColor(Color.yellow);
        g.fillOval((WIDTH / 2) - radius, (HEIGHT / 2) - (radius) - 250, radius * 2, radius * 2);
        //samochód1
        g.setColor(Color.darkGray);
        g.fillRect( 150, 492, 150, 80);
        g.setColor(Color.black);
        g.fillOval(160, 550, radius * 2 -80, radius * 2 -80);
        g.fillOval(250, 550, radius * 2 -80, radius * 2 -80);
        g.setColor(Color.white);
        g.fillOval(168, 558, radius * 2 -97, radius * 2 -97);
        g.fillOval(258, 558, radius * 2 -97, radius * 2 -97);
        g.setColor(Color.cyan.darker());
        g.fillRect( 150, 492, 110, 40);
        //samochód2
        g.setColor(Color.red.darker());
        g.fillRect( 750, 492, 150, 80);
        g.setColor(Color.black);
        g.fillOval(760, 550, radius * 2 -80, radius * 2 -80);
        g.fillOval(850, 550, radius * 2 -80, radius * 2 -80);
        g.setColor(Color.white);
        g.fillOval(768, 558, radius * 2 -97, radius * 2 -97);
        g.fillOval(858, 558, radius * 2 -97, radius * 2 -97);
        g.setColor(Color.cyan.darker());
        g.fillRect( 750, 492, 110, 40);
        //samochód3
        g.setColor(Color.yellow.darker());
        g.fillRect( 500, 620, 150, 80);
        g.setColor(Color.black);
        g.fillOval(510, 678, radius * 2 -80, radius * 2 -80);
        g.fillOval(600, 678, radius * 2 -80, radius * 2 -80);
        g.setColor(Color.white);
        g.fillOval(518, 686, radius * 2 -97, radius * 2 -97);
        g.fillOval(608, 686, radius * 2 -97, radius * 2 -97);
        g.setColor(Color.cyan.darker());
        g.fillRect( 540, 620, 110, 40);
        //drzewa
        g.setColor(Color.orange.darker());
        g.fillRect( 1000, HEIGHT - 530, 35, 100);
        g.fillRect( 750, HEIGHT - 480, 30, 100);
        g.fillRect( 200, HEIGHT - 500, 32, 100);
        g.fillRect( 550, HEIGHT - 450, 28, 100);

        g.setColor(Color.green);
        g.fillOval(980, HEIGHT - 550, radius * 2 -40, radius * 2 -40);
        g.fillOval(980, HEIGHT - 540, radius * 2 -40, radius * 2 -40);

        g.fillOval(175, HEIGHT - 520, radius * 2 -35, radius * 2 -20);
        g.fillOval(190, HEIGHT - 500, radius * 2 -40, radius * 2 -40);

        Polygon p1 = new Polygon(new int[] {715, 765, 815}, new int[] {400, 300, 400}, 3);
        Polygon p2 = new Polygon(new int[] {715, 765, 815}, new int[] {380, 280, 380}, 3);
        Polygon p3 = new Polygon(new int[] {515, 565, 615}, new int[] {400, 300, 400}, 3);
        Polygon p4 = new Polygon(new int[] {515, 565, 615}, new int[] {380, 280, 380}, 3);
        Polygon p5 = new Polygon(new int[] {515, 565, 615}, new int[] {360, 260, 360}, 3);
        g.fillPolygon(p1);
        g.fillPolygon(p2);
        g.fillPolygon(p3);
        g.fillPolygon(p4);
        g.fillPolygon(p5);

        //ptaki
        g.setColor(Color.black);
        g.drawArc(800, 200, 80, 50, 10, 60);
        g.drawArc(878, 200, 85, 50, 120, 60);

        g.drawArc(870, 200, 50, 35, 10, 60);
        g.drawArc(920, 200, 55, 35, 120, 60);

        //szprychy
        g.drawLine(768, 570,790,570);
        g.drawLine(768, 560,790,580);
        g.drawLine(768, 580,790,560);
        g.drawLine(779, 580,779,560);
        g.drawLine(858, 570,880,570);
        g.drawLine(858, 560,880,580);
        g.drawLine(858, 580,880,560);
        g.drawLine(869, 580,869,560);

        g.drawLine(168, 570,190,570);
        g.drawLine(168, 560,190,580);
        g.drawLine(168, 580,190,560);
        g.drawLine(179, 580,179,560);
        g.drawLine(258, 570,280,570);
        g.drawLine(258, 560,280,580);
        g.drawLine(258, 580,280,560);
        g.drawLine(269, 580,269,560);

    }

    public static void main(String[] args)
    {
        pic2D = new Lab1.Pic2D();
    }

}

