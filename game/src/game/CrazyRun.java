package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CrazyRun implements ActionListener, MouseListener, KeyListener
{
	public static CrazyRun creazyRun;

	public final int WIDTH = 1200, HEIGHT = 800; //Height rośnie od gory w dół , a width od lewej do prawej  (podloga na HEIGHT = 800-120 = 780)
	
	public int pozX = WIDTH / 2 - 400, pozY = HEIGHT - 170; //pozycja gracza
	public int speed = 12 ;//szybkość przeszkód
	
	public Renderer renderer;		
	
	public ArrayList<Przeszkoda> przeszkody1; //postopadłe
	public ArrayList<Missle> missles; 
	public MainCharacter maincharacter;
	
	public int ticks, yMotion, score ,finalscore;

	public boolean gameOver, started , continueFly = true , endscore = true;

	public Random rand;
	
	public Image background;
	public Icon jump;
	public Image postac;	
	public Image missle;
	public Image przeszkoda1;
	public ImageIcon icon;
	public Image ground;
	public Image floor;
	public Image sufit;
	public Image spike;	
	
	public Przeszkoda ziemia, podloga;
//	public Animation kolec;
	
			
	public CrazyRun()
	{
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this); //wszystko wykonuje sie w 20 milisekund (wykonuje sie 1 klatka)
		
		icon =  new ImageIcon("img/icondog.png");	
		
		rand = new Random();
		renderer = new Renderer();
		background = renderer.background; 
		ground = renderer.ground;
		floor = renderer.floor;
		sufit = renderer.sufit;
		postac = renderer.dog; 
		missle = renderer.missle;
		przeszkoda1 = renderer.przeszkoda1;
		spike = renderer.spike;
	//	postac = postac.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);  //zmiana rozmmiar�w zdj�cia- wyskalowanie obrazu
	//	przeszkoda1 = przeszkoda1.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);  
	//	missle = missle.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);  
			
		ziemia = new Przeszkoda(0, HEIGHT - 120, WIDTH, 120, ground);
		podloga = new Przeszkoda(0, HEIGHT - 170, WIDTH, 120, floor);
		maincharacter = new MainCharacter(WIDTH / 2 - 400, HEIGHT - 250, 100, 100, postac);
		
//		kolec = new Animation(20, renderer.missle0, renderer.missle1, renderer.missle2, renderer.missle3, renderer.missle4, renderer.missle5, renderer.missle6);		
		
		przeszkody1 = new ArrayList<Przeszkoda>();
		missles = new ArrayList<Missle>();
		
//        jump =  new ImageIcon("img/skok.gif");
//        JLabel label = new JLabel(jump);
//        jframe.getContentPane().add(label);

		
		jframe.add(renderer);
		jframe.setTitle("Creazy Run");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(true);
		jframe.setLocationRelativeTo(null); //Okno pojawi sie na srodku ekranu
		jframe.setVisible(true);
		jframe.setIconImage(icon.getImage());
		
		addPrzeszkoda(true);
		addPrzeszkoda(true);
		
		addMissile(true);
		addMissile(true);
		//addBluePill(true);
		//addRedPill(true);
		//addVioletPill(true);
		//addTimeSlowmo(true);

		timer.start();
	}
	
	public void addPrzeszkoda(boolean start)
	{
		int width = 50;	//szerokosc kolumn
		int random = rand.nextInt(100) + 300;
		int random2 = rand.nextInt(350);
		int random3 = rand.nextInt(100) + 800;
		int height = 135; // kolumny pojawiaja sie nad ziemia ale o losowej wysokosci

		if (start) //pierwsze przeszkody przy starcie:
		{			
			przeszkody1.add(new Przeszkoda(WIDTH + width + przeszkody1.size() * 300, HEIGHT - height - 170 - random2, width, height, przeszkoda1)); // pierwsze dwa argumenty to pozycja w oknie (w jakiej szeroko�ci Frame'a si� tworz�, w jakiej wysoki�ci Frame'a si� tworz�, prostok�ty o jakiej szeroko�ci, prostok�ty o jakiej wysoko�ci)
 			przeszkody1.add(new Przeszkoda(WIDTH + width, 0, width, height, spike));
		}
		else
		{			
			przeszkody1.add(new Przeszkoda(przeszkody1.get(przeszkody1.size() - 1).getX() + random3, HEIGHT - height - 170 - random2, width, height, przeszkoda1));
			przeszkody1.add(new Przeszkoda(przeszkody1.get(przeszkody1.size() - 1).getX()+ random, 0, width, height , spike));
		}		
		
	}
	
//	public void addMissile(boolean start)
//	{
//
//		int width = 50;	//szeroko�� kolumn
//		int random = rand.nextInt(300);
//		int random2 = rand.nextInt(350);
//		int height = 30; // kolumny pojawiaj� si� nad ziemi� ale o losowej wysoko�ci
//
//		if (start)
//		{
//			missles.add(new Missle(WIDTH + width + missles.size() * 300, HEIGHT - height - 170 - random2, width, height, missle)); 
//			missles.add(new Missle(WIDTH + width + (missles.size() - 1) * 300 + random, 0, width, height, missle));
//		}
//		else
//		{			
//			missles.add(new Missle(missles.get(missles.size() - 1).getX() + 600, HEIGHT - height - 170 - random2, width, height, missle));
//			missles.add(new Missle(missles.get(missles.size() - 1).getX() + random, 0, width, height , missle));
//		}		
//		
//	}
	
	public void addMissile(boolean start)
	{
		int width = 50;
		int random = rand.nextInt(100) + 300;
		int random2 = rand.nextInt(100) + 500;
		int height = 30; // pojawiaja sie nad ziemia

		missles.add(new Missle(WIDTH + width + missles.size() * 300, HEIGHT - height - 170 - random, width, height, missle)); 
		missles.add(new Missle(WIDTH + width + (missles.size() - 1) * 300 + random2, random2, width, height, missle));
		missles.add(new Missle(WIDTH + width + (missles.size() - 1) * 300 + random, 0, width, height, missle));
		
//		if (start)
//		{
//			missles.add(new Missle(WIDTH + width + missles.size() * 300, HEIGHT - height - 170 - random2, width, height, missle)); 
//			missles.add(new Missle(WIDTH + width + (missles.size() - 1) * 300 + random, 0, width, height, missle));
//		}
//		else
//		{			
//			missles.add(new Missle(missles.get(missles.size() - 1).getX() + 600, HEIGHT - height - 170 - random2, width, height, missle));
//			missles.add(new Missle(missles.get(missles.size() - 1).getX() + random, 0, width, height , missle));
//		}		
		
	}	
	
	
	
	
	public void paintPrzeszkoda(Graphics g, Przeszkoda przeszkoda)
	{
		g.drawImage(przeszkoda.getImage(),
				przeszkoda.getX(), przeszkoda.getY(), przeszkoda.getWidth(), przeszkoda.getHeight(),
			       null);
	}
	public void paintMissile(Graphics g, Missle missle)
	{
		g.drawImage(missle.getImage(),
				missle.getX(), missle.getY(), missle.getWidth(), missle.getHeight(),
			       null);
	}
	public void paintDog(Graphics g, MainCharacter maincharacter)
	{
		g.drawImage(postac,																										
				maincharacter.getX(), maincharacter.getY(), maincharacter.getWidth(), maincharacter.getHeight(),
			       null);
	}

	public void jump()
	{
		if (gameOver) //resetuje polozenie gracza do stanu poczatkowego w przypadku porazki oraz czyszczenie listy obiektow
		{
			maincharacter.setX(WIDTH / 2 - 400);
			maincharacter.setY(HEIGHT - 170);
			przeszkody1.clear();
			missles.clear();
			yMotion = 0;
			score = 0;
			speed = 12;
			
			
			addPrzeszkoda(true);
			addPrzeszkoda(true);
			addMissile(true);
			addMissile(true);
			endscore = true;
			gameOver = false;
		}

		if (!started) //aby rozpoczynac gre gdy wywolamy jump() np porzez kllikniecie
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion < 0)		//(yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;	//zmiana polozenia - ruch po osi y wiec w gore lub w dol(odejmuje wiec w gore)
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		score ++;
		ticks++;
		int speed2 = speed + 5; //szybkosc pociskow
		if(score == 100) {
			speed++;
//			addPrzeszkoda(true);
// 			addMissile(true);
		}
		if(score == 250) {
			speed++;
		}
		if(score == 400) {
			speed++;
		}
		if(score == 650) {
			speed++;
		}
		if(score == 800) {
			speed++;
		}
		if(score == 1000) {
			speed++;
		}
		if(score == 1200) {
			speed++;
		}
		if(score == 1500) {
			speed++;
		}
		if(score == 2000) {
			speed++;
		}
		if(score == 2500) {
			speed++;
		}
		if(score == 3000) {
			speed++;
		}
		if(score == 3500) {
			speed++;
		}
		
		if(gameOver && endscore) {
			finalscore = score;
			endscore = false;
		}
		
		
		if (started)
		{
			for (int i = 0; i < przeszkody1.size(); i++)	//pojawiaja sie przeszkody z okreslona szybkoscia
			{
				Przeszkoda przeszkoda = przeszkody1.get(i);
				
				przeszkoda.setX(przeszkoda.getX() - speed);	//zmiana polozenia - ruch po osi x wiec w lewo lub w prawo (odejmuje wiec w lewo)
			}
			for (int i = 0; i < missles.size(); i++)	//pojawiaja sie pociski z okrealona szybkoscia
			{
				Missle missle = missles.get(i);
				
				missle.setX(missle.getX() - speed2);	
			}

			if (ticks % 2 == 0 && yMotion < 15) //postac bedzie spadac przy stalej predkosci
			{
				yMotion += 1.82; 	//z jakim tempem spada postac
			//				if(character.y == HEIGHT) {
			//					yMotion = 0;
			//				}
			}

			for (int i = 0; i < przeszkody1.size(); i++)
			{
				Przeszkoda przeszkoda = przeszkody1.get(i);

				if (przeszkoda.getX() + przeszkoda.getWidth() < 0)
				{
					przeszkody1.remove(przeszkoda); //usuwa kolumny jak wyjda w lewo poza ramke

					if (przeszkoda.getY() == 0)
					{
						addPrzeszkoda(false); 
					}
				}
			}
			for (int j = 0; j < missles.size(); j++)
			{
				Missle missle = missles.get(j);

				if (missle.getX() + missle.getWidth() < 0)
				{
					missles.remove(missle); //usuwa pociski jak wyjda w lewo poza ramke

					if (missle.getY() == 0)
					{
						addMissile(false); 
					}
				}
			}

			maincharacter.setY(maincharacter.getY() + yMotion); //dodaje do ruchu po osi y dla postaci(zmiana polozenia o yMotion)

			for (Przeszkoda przeszkoda : przeszkody1)
			{
				if (przeszkoda.character.intersects(maincharacter.character)) //jak kolumna i charakter sie zdeza koniec gry
				{
					gameOver = true;

				//	if (maincharacter.getX() <= przeszkoda.getX())//aby posta� przy zderzeniu zatrzyma�a si� na przeszkodzie	
				//	{
				//		maincharacter.setX(przeszkoda.getX() - maincharacter.getWidth());
				//	}
				}
				
			}
			
			for (Missle missle : missles)
			{
				if (missle.character.intersects(maincharacter.character)) //jak pocisk i charakter sie zdeza koniec gry
				{
					gameOver = true;
				}
				
			}
			
			if (maincharacter.getY() < 0) // postac nie wyleci ponad niebo
			{				
				maincharacter.setY(0);				
			}
			
			if (maincharacter.getY() > HEIGHT - 250) // nie spadnie ponizej ziemi
			{
				maincharacter.setY(HEIGHT - 250);
				yMotion = 0;	// moment skoku (if jak sie styka z ziemia to skacze - daje animacje ze skoku) (jak dotyka ziemi jest animacja biegu)
			}
		}

		renderer.repaint(); 	//ponownie maluje wszystkie elementy w oknie
	}

	public void repaint(Graphics g)
	{	//tlo	
		g.drawImage(background, 0, 0, null);
		//ziemia
		paintPrzeszkoda(g, ziemia);
		//podloga
		paintPrzeszkoda(g, podloga);
		//przeszkody				
		for (Przeszkoda przeszkoda : przeszkody1)
		{
			paintPrzeszkoda(g, przeszkoda);
		}		
		//rakiety				
		for (Missle rakieta : missles)
		{
			paintMissile(g, rakieta);
		}
		//postac
		paintDog(g,maincharacter);
				
//		kolec.drawAnimation(g, WIDTH / 4, 400, 70, 70);
		
		//napisy
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100)); 

		if (!started)
		{
			g.drawString("Press space to start!", 120, HEIGHT / 2 - 50);
		}

		if (gameOver)
		{
			g.drawString("Game Over!", WIDTH / 4, HEIGHT / 2 - 50 );
			g.setFont(new Font("Arial", 1, 50));
			g.drawString(String.valueOf("Your Final Score:" + finalscore), WIDTH / 4, 400);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Click to restart", WIDTH / 4, 450);
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	
	
	public static void main(String[] args)
	{
		creazyRun = new CrazyRun();
	}

//STEROWANIE:
	
	@Override
	public void mouseClicked(MouseEvent e)
	{//	started=true;
	//	jump();
//		if(continueFly) {	
//
//			if(character.y + yMotion >= HEIGHT - 120) {
//				jump();
//			}
//		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
//		if (e.getKeyCode() == KeyEvent.VK_SPACE)
//		{
//			continueFly = false;
//		//	yMotion += 20;
//		//	jump();
//		}
//		continueFly = true;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{	//KeyEvent.addActionListener(jump());			
		if(gameOver ) {
			jump();
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
//		continueFly = false;
//		continueFly = true;
	}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void keyPressed(KeyEvent e)
	{
	//	if(continueFly) {
			if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE)
			{		
//				if (maincharacter.getY() == HEIGHT - 220){ //jak postac dotknie ziemi(wartosc 660)) to skakacze zamiast latac
//					yMotion = 0;  // moment skoku (if jak sie styka z ziemia to skacze - daje animacje ze skoku) (jak dotyka ziemi jest animacja biegu)
//				}
	//			else {
	//				animacja latania
	//				jump()
	//			}
				
				jump();
			}
	//	}
	}

}

