//package game;
//
//import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.Random;
//
//import javax.swing.JPanel;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//
//import javax.swing.Timer;
//
//public class Panel extends JPanel implements ActionListener, KeyListener {
//	
//	private Timer time;
//	
//	BufferedImage image;
//	
//	public Random rand;
//	
////	public Image character;
//	public Rectangle character;
//	
//	public ArrayList<Rectangle> columns;
//
//	public int ticks, yMotion, score;
//
//	public boolean gameOver, started , continueFly = true;
//
//	
//	public Panel() {
//		Timer timer = new Timer(20, this); //wszystko wykonuje si� w 20 milisekund 
//		rand = new Random();	
//		this.addKeyListener(this);
//		
//		
//		character = new Rectangle(WIDTH / 2 - 400, HEIGHT - 150, 20, 20); //tworz� posta� na pocz�tku gry
////		character = new ImageIcon("img/__jet_pack_man_now_weapon_flying.png").getImage();
//		columns = new ArrayList<Rectangle>();
//
//		addColumn(true);
//		addColumn(true);
//		addColumn(true);
//		addColumn(true);
//		
//		
//		//addMissile(true);
//		//addBluePill(true);
//		//addRedPill(true);
//		//addVioletPill(true);
//		//addTimeSlowmo(true);
//		
//		
//		timer.start();
//	}
//	
//	
//	public void addColumn(boolean start)
//	{
//		int space = 300;	//odst�p mi�dzy kolumnami(przestrze� mi�dzy g�rn� a doln�)
//		int width = 100;	//szeroko�� kolumn
//		int height = 50 + rand.nextInt(300); // kolumny pojawiaj� si� nad ziemi� ale o losowej wysoko�ci
//
//		if (start)
//		{
//			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height)); // pierwsze dwa argumenty to pozycja w oknie (w jakiej szeroko�ci Frame'a si� tworz�, w jakiej wysoki�ci Frame'a si� tworz�, prostok�ty o jakiej szeroko�ci, prostok�ty o jakiej wysoko�ci)
//			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
//		}
//		else
//		{
//			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
//			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
//		}
//	}
//	
//	public void paintColumn(Graphics g, Rectangle column)
//	{
//		g.setColor(Color.green.darker());
//		g.fillRect(column.x, column.y, column.width, column.height); //uzupe�nia wn�trze i rysuje prostok�t
//	}
//
//	public void jump()
//	{
//		if (gameOver) // aby resetowa� po�o�enie gracza do stanu pocz�tkowego w przypadku pora�ki
//		{
//			character = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
//			columns.clear();
//			yMotion = 0;
//			score = 0;
//
//			addColumn(true);
//			addColumn(true);
//			addColumn(true);
//			addColumn(true);
//
//			gameOver = false;
//		}
//
//		if (!started) //aby rozpoczyna� gr� gdy wywo�amy jump() np porzez kllikni�cie
//		{
//			started = true;
//		}
//		else if (!gameOver)
//		{
//			if (yMotion < 0)		//(yMotion > 0)
//			{
//				yMotion = 0;
//			}
//
//			yMotion -= 10;	//zmiana po�o�enia - ruch po osi y wi�c w g�r� lub w d�(odejmuj� wi�c w g�r�)
//		}
//	}
//
//	
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		int speed = 10; //ustalona szybko��
//
//		ticks++;
//
//		if (started)
//		{
//			for (int i = 0; i < columns.size(); i++)	//pojawiaj� si� kolumny z okre�lon� szybko�ci�
//			{
//				Rectangle column = columns.get(i);
//
//				column.x -= speed;	//zmiana po�o�enia - ruch po osi x wi�c w lewo lub w prawo (odejmuj� wi�c w lewo)
//			}
//
//			if (ticks % 2 == 0 && yMotion < 15) //posta� b�dzie spada� przy sta�ej pr�dko�ci
//			{
//				yMotion += 1.82; 	//z jakim tempem spada posta�
////				if(character.y == HEIGHT) {
////					yMotion = 0;
////				}
//			}
//
//			for (int i = 0; i < columns.size(); i++)
//			{
//				Rectangle column = columns.get(i);
//
//				if (column.x + column.width < 0)
//				{
//					columns.remove(column); //usuwa kolumny jak wyjd� w lewo poza ramk�
//
//					if (column.y == 0)
//					{
//						addColumn(false);
//					}
//				}
//			}
//
//			character.y += yMotion; //dodaj� do ruch po osi y dla postaci(spadek)
//
//			for (Rectangle column : columns)
//			{
//				if (column.y == 0 && character.x + character.width / 2 > column.x + column.width / 2 - 10 && character.x + character.width / 2 < column.x + column.width / 2 + 10)  //dziel� na dwa aby nie nabija�o podw�jnie punkt�w tylko 1 punkt przy tym jak posta� min� 2 kolumny(g�rn� i doln�)
//				{
//					score++;
//				}
//
//				if (column.intersects(character)) //jak kolumna i charakter si� zde�� koniec gry
//				{
//					gameOver = true;
//
//					if (character.x <= column.x)
//					{
//						character.x = column.x - character.width; //aby posta� przy zderzeniu zatrzyma�a si� na kolumnie
//
//					}
//					else
//					{
//						if (column.y != 0)
//						{
//							character.y = column.y - character.height; //aby posta� np. przy zderzeniu z kolumn� g�rn� - od �rodka w g�r�, nie przenikn�a przez kolumn� pod wzgl�dem wysoko�ci
//						}
//						else if (character.y < column.height)
//						{
//							character.y = column.height; //aby posta� np. przy zderzeniu z kolumn� doln� - od �rodka w d�, nie przenikn�a przez kolumn� pod wzgl�dem wysoko�ci
//						}
//					}
//				}
//				
//			}
//
//			if (character.y > HEIGHT - 120 || character.y < 0) //jak posta� wyleci za wysoko koniec gry i bezw�adnie opada na ziemie
//			{
//				character.y = 0 ;	//aby nie przekracza� sufitu w jetpacku
//			//	gameOver = true;
//			}
//
//			if (character.y + yMotion >= HEIGHT - 130) //jak posta� dotknie ziemi to pozostaje na jej wysoko�ci , koniec gry   ,(w jetpacku biegnie)
//			{
//				character.y = HEIGHT - 120 - character.height;
//				yMotion = 5;  // moment skoku (if jak si� styka z ziemia to skacze - daje animacje ze skoku) (jak dotyka ziemi jest animacja biegu)
////				if (gameOver)	//jak zginie to si� powoli zatrzymuje w miejscu
////				{
////					yMotion = 5;
////
////				}
//				//Posta� biegnie run()
//								//jak posta� zginie natrafiaj�c na przeszkod�:
////				if (gameOver)	//jak zginie to si� powoli zatrzymuje w miejscu
////				{
////					character.x -= speed;
////				}
//			//	character.x -= speed; //aby posta� pozosta�a w miejscu �mierci (mog� to jeszcze podzieli� np. prze 1.2 albo co� doda� aby lekko naturalnie si� przesun�a do przodu - z si�� rozp�du) 
//			//	gameOver = true;
//			}
//		}
//
//		renderer.repaint();
//	}
//
//	public void repaint(Graphics g)
//	{	//t�o
//		g.setColor(Color.cyan);			
//		g.fillRect(0, 0, WIDTH, HEIGHT);
//		//ziemia
//		g.setColor(Color.orange);
//		g.fillRect(0, HEIGHT - 120, WIDTH, 120);
//		//pod�oga
//		g.setColor(Color.green);
//		g.fillRect(0, HEIGHT - 120, WIDTH, 20);
//		//posta�
//		g.setColor(Color.red);
//		g.fillRect(character.x, character.y, character.width, character.height);
//		//kolumny
//		for (Rectangle column : columns)
//		{
//			paintColumn(g, column);
//		}
//		//napisy
//		g.setColor(Color.white);
//		g.setFont(new Font("Arial", 1, 100)); 
//
//		if (!started)
//		{
//			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
//		}
//
//		if (gameOver)
//		{
//			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
//		}
//
//		if (!gameOver && started)
//		{
//			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
//		}
//	}
//
//	
//	
//	
//	
//	
//	
//	@Override
//	public void keyPressed(KeyEvent e)
//	{
//		if(continueFly) {
//			if (e.getKeyCode() == KeyEvent.VK_SPACE)
//			{	//yMotion = 0;  // moment skoku (if jak si� styka z ziemia to skacze - daje animacje ze skoku) (jak dotyka ziemi jest animacja biegu)
//				jump();
//			}
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//	
//	
//}
