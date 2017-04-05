import sun.jvm.hotspot.memory.Space;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * Created by kamran_lateef on 3/13/17.
 */

public class Main extends JPanel {

    public static final int FRAMEWIDTH = 1430, FRAMEHEIGHT = 1000;
    private Timer timer;
    private boolean[] keys;
    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;


    private Sprite Haiti;
    private SpaceShip1 Derek;
    private SmallAsteroid Tony;
    private SmallAsteroid Daniel;
    private MediumAsteroid Lateef;
    private MediumAsteroid Ho;

    private LargeAsteroid Jack;
    private LargeAsteroid Jill;
    private LargeAsteroid Jeff;
    private LargeAsteroid Jim;
    private LargeAsteroid John;

    private Bullet bull;

    private int Level;
    private int Lives;
    private int points;

    private ArrayList<Sprite> obstacles;
    private ArrayList<Bullet> ridemyoto;
    private ArrayList<SpaceShip1> shooti;

    int x = (int) (Math.random() * 1430);
    int y = (int) (Math.random() * 1000);


    public Main() {

        keys = new boolean[513]; //should be enough to hold any key code.

        Haiti = new RocketShip();


        Derek = new SpaceShip1(400, 100, NORTH); // should be /-1000,-1000


        Tony = new SmallAsteroid(500, 300, NORTH);
        Daniel = new SmallAsteroid(100, 200, NORTH);


        Lateef = new MediumAsteroid(300, 200, WEST);
        Ho = new MediumAsteroid(100, 100, WEST);


//        Jack = new LargeAsteroid(10, 10, NORTH);
//        Jill = new LargeAsteroid(100, 400, SW);
//        Jeff = new LargeAsteroid(500, 100, NW);
        Jim = new LargeAsteroid(600, 300, EAST);
        John = new LargeAsteroid(200, 100, SOUTH);


        Jack = new LargeAsteroid(x , y, NORTH);
        Jill = new LargeAsteroid((int) (Math.random() * 1430), (int) (Math.random() * 1000), NORTH);
        Jeff = new LargeAsteroid((int) (Math.random() * 1000), (int) (Math.random() * 800), NORTH);



//TODO: Remove all bull
        bull = new Bullet();

        bull = new Bullet((int)Haiti.getLoc().getX(), (int)Haiti.getLoc().getY(), EAST, Haiti.getSpeed()+5);

        points = 0;
        Level = 0;
        Lives = 50;


        obstacles = new ArrayList<Sprite>();
        //obstacles.add(Derek); //The RocketShip

        obstacles.add(Jack);
        obstacles.add(Jill);
        obstacles.add(Jeff);
        obstacles.add(Jim);
        obstacles.add(John);

        ridemyoto = new ArrayList<Bullet>(); //leave empty when game starts
        shooti = new ArrayList<SpaceShip1>();
        shooti.add(Derek);


//        obstacles.add(Tony);
//        obstacles.add(Lateef);
//        obstacles.add(bull);



        System.out.println();


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

//                move rocketship in direction

                if (keys[KeyEvent.VK_W] == true) {
                    Haiti.setDir(Haiti.getDir());
                    Haiti.setSpeed(Haiti.getSpeed() + 5);
                    keys[KeyEvent.VK_W] = false; //probably.
                }
                if (keys[KeyEvent.VK_S]) {
                    Haiti.setDir(Haiti.getDir());
                    Haiti.setSpeed(Haiti.getSpeed() - 5);
                    keys[KeyEvent.VK_S] = false; //probably.
                }

                //bullets


                if (keys[KeyEvent.VK_SPACE]) {
                    ridemyoto.add(new Bullet((int)Haiti.getLoc().getX(), (int)Haiti.getLoc().getY(), Haiti.getDir(), Haiti.getSpeed()+5));
                    keys[KeyEvent.VK_SPACE] = false; //probably.
                }



                if (keys[KeyEvent.VK_P]) {
                    Lives += 50;
                    keys[KeyEvent.VK_P] = false; //probably.
                }
                //rotate the rocketship left
                if (keys[KeyEvent.VK_A]) {
                    Haiti.rotateBy(20);
//                    keys[KeyEvent.VK_A] = false; //probably.
                }
                //rotate rocketship right
                if (keys[KeyEvent.VK_D]) {
                    Haiti.rotateBy(-20);
//                    keys[KeyEvent.VK_D] = false; //probably.
                }




                //update each obstacle
                //check for collisions ************************

                for (int j = 0; j < obstacles.size(); j++) {
                    Sprite o = obstacles.get(j);

                    o.update();
//                    System.out.println("check");

                    //Player crash?
                    if (Haiti.intersects(o) == true && o instanceof Obstacle) {
                        Haiti.setLoc(new Point(400, 400));
                        System.out.println("hit");
                        Lives--;

                        for (int i = 0; i < shooti.size(); i++) {
                            if (Haiti.intersects(shooti.get(i))) {
                                Haiti.setLoc(new Point(400, 400));
                                System.out.println("hit");
                                Lives--;
                                shooti.remove(i);
                                i = shooti.size();


                            }
                        }

                        obstacles.remove(j);
                        if(o instanceof LargeAsteroid) {
                            points+=50;
                            MediumAsteroid ma = new MediumAsteroid((int) o.getLoc().getX(), (int) o.getLoc().getY(), WEST);
                            obstacles.add(ma);
                            MediumAsteroid ma1 = new MediumAsteroid((int) o.getCenterPoint().getX(), (int) o.getCenterPoint().getY(), WEST);
                            obstacles.add(ma1);
                        }
                        else if(o instanceof MediumAsteroid){
                            points+=100;
                            //spawn smalls
                            SmallAsteroid ha = new SmallAsteroid((int) o.getLoc().getX(), (int) o.getLoc().getY(), NORTH);
                            obstacles.add(ha);
                            SmallAsteroid ha1 = new SmallAsteroid((int) o.getCenterPoint().getX(), (int) o.getCenterPoint().getY(), NORTH);
                            obstacles.add(ha1);
                        }
                        else if(o instanceof SmallAsteroid){
                            points+=200;
                        }



                    }



                    if(obstacles.size() == 0){
                        obstacles.add(Jack);
                        obstacles.add(Jill);
                        obstacles.add(Jeff);
                        obstacles.add(Jim);
                        obstacles.add(John);
                    }


                    for (int i = 0; i < ridemyoto.size(); i++) {
                        if (o.intersects(ridemyoto.get(i))) {
                            ridemyoto.remove(i);
                            obstacles.remove(j);

                            if(o instanceof LargeAsteroid) {
                                points+=50;
                                MediumAsteroid ma = new MediumAsteroid((int) o.getLoc().getX(), (int) o.getLoc().getY(), NORTH);
                                obstacles.add(ma);
                                MediumAsteroid ma1 = new MediumAsteroid((int) o.getCenterPoint().getX(), (int) o.getCenterPoint().getY(), NORTH);
                                obstacles.add(ma1);
                            }
                            else if(o instanceof MediumAsteroid){
                                points+=100;
                                //spawn smalls
                                SmallAsteroid ha = new SmallAsteroid((int) o.getLoc().getX(), (int) o.getLoc().getY(), NORTH);
                                obstacles.add(ha);
                                SmallAsteroid ha1 = new SmallAsteroid((int) o.getCenterPoint().getX(), (int) o.getCenterPoint().getY(), NORTH);
                                obstacles.add(ha1);
                            }
                            else if(o instanceof SmallAsteroid){
                                points+=200;
                            }


                            i = ridemyoto.size();

                        }
                    }







                }






                //bullets go away out of bounds
                for (int j = 0; j < ridemyoto.size(); j++) {
                    Bullet b = ridemyoto.get(j);
                    if(b.getLoc().getX() > 1430 || b.getLoc().getX() < 0 ){
                        ridemyoto.remove(b);
                    }
                    if(b.getLoc().getY() > 1000 || b.getLoc().getY() < 0 ){
                        ridemyoto.remove(b);
                    }


                    b.update();

                }





                //screen 1430, 1000

//                for (Sprite o : obstacles) {
//                    if (o.getLoc().getX() < -5) {
//                        o.setSpeed(-o.getSpeed());
//                        o.setLoc(new Point(x, y));
//                    }
//                    if (o.getLoc().getX() > 1005) {
//                        o.setSpeed(-o.getSpeed());
//                        o.setLoc(new Point(x, y));
//                    }
//                }



                if(points > 1000){
                    Level = 1;
                }

                if(points > 2000 && points < 3000){
                    Level =2;
                }

                if(points > 3000 && points < 4000){
                    Level =3;
                }
                if(points > 4000){
                    Level =4;
                }



                if (Lives == 0 || Lives < 0 ) {
                    timer.stop();
                }

                if (points == 4500) {
                    timer.stop();
                }

                if (Haiti.getLoc().getX() > 1430)
                    Haiti.setLoc(new Point(0, (int) Haiti.getLoc().getY()));

                if (Haiti.getLoc().getX() < 0)
                    Haiti.setLoc(new Point(1430, (int) Haiti.getLoc().getY()));

                if (Haiti.getLoc().getY() > 1000)
                    Haiti.setLoc(new Point((int) Haiti.getLoc().getX(), 0));

                if (Haiti.getLoc().getY() < 0)
                    Haiti.setLoc(new Point((int) Haiti.getLoc().getX(), 1000));



                if (Level == 1) {
                    shooti.add(Derek);

                    SpaceShip1 level1Ship1 = new SpaceShip1(300, 300, NORTH);
                    shooti.add(level1Ship1);
                    SpaceShip1 level1Ship2 = new SpaceShip1(200, 200, NORTH);
                    shooti.add(level1Ship2);
                    SpaceShip1 level1Ship3 = new SpaceShip1(500, 600, NORTH);
                    shooti.add(level1Ship3);
                    SpaceShip1 level1Ship4 = new SpaceShip1(800, 900, NORTH);
                    shooti.add(level1Ship4);


                }



                if (Level == 2) {




                }

                if (Level == 3) {


                }



                Haiti.update();
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });

    }


    //Our paint method.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0,0,1430,1000);

        for (Sprite o : obstacles) { //for each of the Sprites in the arrayList obstacles draw it
            o.draw(g2);
        }
        for(Bullet b : ridemyoto)
            b.draw(g2);

        for(SpaceShip1 s: shooti)
            s.draw(g2);


        g2.setColor(Color.BLUE);
        g2.drawString("Lives:" + Lives, 100, 100);
        g2.drawString("Points:" + points, 100, 140);
        g2.drawString("Level:" + Level, 100, 120);

        if(Level ==1){
            g2.drawString("Level 1", 500, 500);

            g2.drawString("Don't hit the SpaceShips", 500, 600);


        }

        if (Level == 2) {
            g2.drawString("Level 2", 500, 500);
        }

        if (Level == 3) {
            g2.drawString("Level 3", 500, 500);
        }


        Haiti.draw(g2);


        if(Lives == 0){
            g2.drawString("GAME OVER", 500, 500);

        }
        if (points == 4500) {
            g2.drawString("YOU WIN", 500, 500);
        }





    }


    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("Asteroid Project: Ludwig, Ho, Lateef");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        //(width = 1000, height = 600)

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);


        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }


}






