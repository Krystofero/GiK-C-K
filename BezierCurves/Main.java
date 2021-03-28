/*
Program rysuje inicjaly(KO).
Robi to zlepiajac pokolei krzywe Bezier'a ze sobą na podstawie
4 punktów kontrolnych o współrzędnych (x, y) przypisanych każdej Krzywej Bezier'a
-pierwsza i ostatnia współrzędna to Wierzcholki glowne Krzywej,
 natomiast dwa srodkowe to wierzcholki które decydują o luku/wygieciu danej krzywej.
 Parametry x punktow wszystkich krzywych sa przechowywane w jednej tablicy, natomiast y w drugiej.
 Wspolrzedne zostaly ustalone na podstawie programu do rysowania krzywych: https://www.victoriakirst.com/beziertool/
Klasa ustawianie zawiera metodę dopasowanie, ktora odpowiada za odpowiednie ulozenie/dopasowanie inicjalow w oknie
Litera O sklada sie z duzego O i malego O, ktore jest w srodku
*/

package BezierCurves;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Okno fr = new Okno();
        Panel pa = new Panel();
        fr.getContentPane().add(pa);
    }
}
class Okno extends JFrame{
    public int width=1000;
    public int height=550;
    Okno(){
        setVisible(true);
        setResizable(true);
        setSize(width, height);
        setTitle("Bezier KO initials");
        setLocation(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Panel extends JPanel{

    Panel(){
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int lw=4; //liczba wierzcholkow dla jednej krzywej Beziera
        double[] xk = new double[lw]; //wspolrzedne x jednego luku krzywej Beziera
        double[] yk = new double[lw]; //wspolrzedne y jednego luku krzywej Beziera

        int n = 10000; //liczba punktow do przeskakiwania dla jednego luku krzywej Beziera
        int lk = 11; //liczba krzywych
        int[]xe = new int[lk*n]; //wspolrzedne ekranowe x punktow krzywej
        int[]ye = new int[lk*n]; //wspolrzedne ekranowe y punktow krzywej

        double[]x = new double[lk*n]; //wspolrzedne rzeczywiste x punktow krzywej
        double[]y = new double[lk*n]; //wspolrzedne rzeczywiste y punktow krzywej

//Współrzędne - Litera K:
//        ctx.moveTo(123 + xoff, 117 + yoff);
//        ctx.bezierCurveTo(117 + xoff, 108 + yoff, 40 + xoff, 108 + yoff, 47 + xoff, 121 + yoff);
//        ctx.bezierCurveTo(97 + xoff, 216 + yoff, 99 + xoff, 287 + yoff, 47 + xoff, 388 + yoff);
//        ctx.bezierCurveTo(40 + xoff, 401 + yoff, 136 + xoff, 394 + yoff, 125 + xoff, 384 + yoff);
//        ctx.bezierCurveTo(105 + xoff, 366 + yoff, 124 + xoff, 312 + yoff, 134 + xoff, 285 + yoff);
//        ctx.bezierCurveTo(139 + xoff, 271 + yoff, 186 + xoff, 375 + yoff, 200 + xoff, 380 + yoff);
//        ctx.bezierCurveTo(231 + xoff, 390 + yoff, 284 + xoff, 382 + yoff, 269 + xoff, 382 + yoff);
//        ctx.bezierCurveTo(220 + xoff, 382 + yoff, 177 + xoff, 255 + yoff, 179 + xoff, 246 + yoff);
//        ctx.bezierCurveTo(182 + xoff, 231 + yoff, 219 + xoff, 128 + yoff, 270 + xoff, 113 + yoff);
//        ctx.bezierCurveTo(284 + xoff, 109 + yoff, 231 + xoff, 109 + yoff, 217 + xoff, 113 + yoff);
//        ctx.bezierCurveTo(191 + xoff, 121 + yoff, 145 + xoff, 226 + yoff, 136 + xoff, 214 + yoff);
//        ctx.bezierCurveTo(113 + xoff, 185 + yoff, 113 + xoff, 158 + yoff, 123 + xoff, 117 + yoff);

        double[] xb = {123,117,40,47,
                        47,97,99,47,
                        47,40,136,125,
                        125,105,124,134,
                        134,139,186,200,
                        200,231,284,269,
                        269,220,177,179,
                        179,182,219,270,
                        270,284,231,217,
                        217,191,145,136,
                        136,113,113,123}; //wspolrzedne X wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)
        double[] yb =  {117,108,108,121,
                        121,216,287,388,
                        388,401,394,384,
                        384,366,312,285,
                        285,271,375,380,
                        380,390,382,382,
                        382,382,255,246,
                        246,231,128,113,
                        113,109,109,113,
                        113,121,226,214,
                        214,185,158,117};//wspolrzedne Y wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)

        int i,j,k,m,lzk,kk;
        double t; //paramametr krzywej Beziera: 0 <= t <= 1

        double sk=1./(n-1);     //sk = o ile przeskakuje parametr t kazdego luku Beziera
        for(k=0,lzk=0;lzk<lk;lzk++) //podroz po zlepianych krzywych
        {
            for(kk=0,t=0;kk<n;kk++,k++,t+=sk) //zliczenie n punktow kazdej ze zlepianych krzywych
            {
                for(i=0;i<lw;i++){xk[i]=xb[lw*lzk+i]; yk[i]=yb[lw*lzk+i];}
                m=lw-1;
                for(i=0;i<lw-1;i++)
                {
                    for(j=0;j<m;j++)
                    {
                        xk[j]=xk[j]+t*(xk[j+1]-xk[j]);
                        yk[j]=yk[j]+t*(yk[j+1]-yk[j]);
                    }
                    m--;
                }
                x[k]=xk[0];
                y[k]=yk[0];
            }
        }

        //dopasowanie zliczonych wspolrzednych do okna wizualizacji
        ustawianie u1 = new ustawianie();
        int[] row = {30,30,470,470};    //okno wizualizacji
        u1.dopasowanie(k, this.getHeight(), x, y, row, xe, ye); //dopasowuję odpowiednie położenie inicjałów w oknie


//Wspolrzedne - Litera O(duze)
//        ctx.moveTo(594 + xoff, 249 + yoff);
//        ctx.bezierCurveTo(596 + xoff, 127 + yoff, 592 + xoff, 99 + yoff, 558 + xoff, 72 + yoff);
//        ctx.bezierCurveTo(495 + xoff, 22 + yoff, 418 + xoff, 59 + yoff, 407 + xoff, 76 + yoff);
//        ctx.bezierCurveTo(365 + xoff, 142 + yoff, 374 + xoff, 232 + yoff, 374 + xoff, 247 + yoff);
//        ctx.bezierCurveTo(378 + xoff, 425 + yoff, 397 + xoff, 431 + yoff, 423 + xoff, 453 + yoff);
//        ctx.bezierCurveTo(450 + xoff, 476 + yoff, 527 + xoff, 483 + yoff, 571 + xoff, 440 + yoff);
//        ctx.bezierCurveTo(599 + xoff, 413 + yoff, 597 + xoff, 231 + yoff, 594 + xoff, 250 + yoff);
        double[] xb2 = {594,596,592,558,
                        558,495,418,407,
                        407,365,374,374,
                        374,378,397,423,
                        423,450,527,571,
                        571,599,597,594}; //wspolrzedne X wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)
        double[] yb2 = {249,127,99,72,
                        72,22,59,76,
                        76,142,232,247,
                        247,425,431,453,
                        453,476,483,440,
                        440,413,231,249};//wspolrzedne Y wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)

        double[] xk2 = new double[lw]; //wspolrzedne x jednego luku krzywej Beziera
        double[] yk2 = new double[lw]; //wspolrzedne y jednego luku krzywej Beziera

        int lk2 = 6; //liczba krzywych
        int[]xe2 = new int[lk2*n]; //wspolrzedne ekranowe x punktow krzywej
        int[]ye2 = new int[lk2*n]; //wspolrzedne ekranowe y punktow krzywej

        double[]x2 = new double[lk2*n]; //wspolrzedne rzeczywiste x punktow krzywej
        double[]y2 = new double[lk2*n]; //wspolrzedne rzeczywiste y punktow krzywej

        for(k=0,lzk=0;lzk<lk2;lzk++) //podroz po zlepianych krzywych
        {
            for(kk=0,t=0;kk<n;kk++,k++,t+=sk) //zliczenie n punktow kazdej ze zlepianych krzywych
            {
                for(i=0;i<lw;i++){xk2[i]=xb2[lw*lzk+i]; yk2[i]=yb2[lw*lzk+i];}
                m=lw-1;
                for(i=0;i<lw-1;i++)
                {
                    for(j=0;j<m;j++)
                    {
                        xk2[j]=xk2[j]+t*(xk2[j+1]-xk2[j]);
                        yk2[j]=yk2[j]+t*(yk2[j+1]-yk2[j]);
                    }
                    m--;
                }
                x2[k]=xk2[0];
                y2[k]=yk2[0];
            }
        }

        //dopasowanie zliczonych wspolrzednych do okna wizualizacji
        ustawianie u2 = new ustawianie();
        u2.dopasowanie(k, this.getHeight(), x2, y2, row, xe2, ye2); //dopasowuję odpowiednie położenie inicjałów w oknie


//Wspolrzedne - Litera O(male)
//        ctx.moveTo(417 + xoff, 258 + yoff);
//        ctx.bezierCurveTo(418 + xoff, 188 + yoff, 423 + xoff, 113 + yoff, 480 + xoff, 113 + yoff);
//        ctx.bezierCurveTo(536 + xoff, 113 + yoff, 540 + xoff, 188 + yoff, 538 + xoff, 257 + yoff);
//        ctx.bezierCurveTo(536 + xoff, 339 + yoff, 531 + xoff, 405 + yoff, 477 + xoff, 404 + yoff);
//        ctx.bezierCurveTo(432 + xoff, 403 + yoff, 418 + xoff, 338 + yoff, 417 + xoff, 258 + yoff);
        double[] xb3 = {417,418,423,480,
                        480,536,540,538,
                        538,536,531,477,
                        477,432,418,417}; //wspolrzedne X wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)
        double[] yb3 = {258,188,113,113,
                        113,113,188,257,
                        257,339,405,404,
                        404,403,338,258};//wspolrzedne Y wierzcholkow wszystkich wielobokow Bezier'a(lk*lw wspolrzednych)

        double[] xk3 = new double[lw]; //wspolrzedne x jednego luku krzywej Beziera
        double[] yk3 = new double[lw]; //wspolrzedne y jednego luku krzywej Beziera

        int lk3 = 4; //liczba krzywych
        int[]xe3 = new int[lk3*n]; //wspolrzedne ekranowe x punktow krzywej
        int[]ye3 = new int[lk3*n]; //wspolrzedne ekranowe y punktow krzywej

        double[]x3 = new double[lk3*n]; //wspolrzedne rzeczywiste x punktow krzywej
        double[]y3 = new double[lk3*n]; //wspolrzedne rzeczywiste y punktow krzywej

        for(k=0,lzk=0;lzk<lk3;lzk++) //podroz po zlepianych krzywych
        {
            for(kk=0,t=0;kk<n;kk++,k++,t+=sk) //zliczenie n punktow kazdej ze zlepianych krzywych
            {
                for(i=0;i<lw;i++){xk3[i]=xb3[lw*lzk+i]; yk3[i]=yb3[lw*lzk+i];}
                m=lw-1;
                for(i=0;i<lw-1;i++)
                {
                    for(j=0;j<m;j++)
                    {
                        xk3[j]=xk3[j]+t*(xk3[j+1]-xk3[j]);
                        yk3[j]=yk3[j]+t*(yk3[j+1]-yk3[j]);
                    }
                    m--;
                }
                x3[k]=xk3[0];
                y3[k]=yk3[0];
            }
        }

        //dopasowanie zliczonych wspolrzednych do okna wizualizacji
        ustawianie u3 = new ustawianie();
        u3.dopasowanie(k, this.getHeight(), x3, y3, row, xe3, ye3); //dopasowuję odpowiednie położenie inicjałów w oknie

        //narysowanie linii lamanej -Litera K
        g.setColor(Color.red);
        for(i=1;i<lk*n;i++){
            g.drawLine(xe[i-1], ye[i-1], xe[i], ye[i]);
        }
        //Litera O
        g.setColor(Color.blue);
        for(i=1;i<lk2*n;i++){
            g.drawLine(xe2[i-1]+300, ye2[i-1], xe2[i]+300, ye2[i]);
        }
        //Litera O(male)
        g.setColor(Color.blue);
        for(i=1;i<lk3*n;i++){
            g.drawLine(xe3[i-1]+325, ye3[i-1], xe3[i]+325, ye3[i]);
        }
    }
}
