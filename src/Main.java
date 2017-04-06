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

//    To do:
//
//
//
//-complete large asteroids, go off screen and come back on the other side, spawn randomly on screen and go in random directions
//-have two med asteroids turn into 2 small asteroids when hit
//-add point system
//           -small: 100
//            -med: 50
//            -large: 20
//            -spaceships: 200


    //instance fields for the general environment
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

    int x = (int) (Math.random() * 1430);
    int y = (int) (Math.random() * 1000);


    public Main() {

        keys = new boolean[513]; //should be enough to hold any key code.

        Haiti = new RocketShip();


        Derek = new SpaceShip1(-1000, -1000, NORTH);

        Tony = new SmallAsteroid(500, 300, NORTH);
        Daniel = new SmallAsteroid(100, 200, NORTH);


        Lateef = new MediumAsteroid(300, 200, NORTH);
        Ho = new MediumAsteroid(100, 100, NORTH);


        Jack = new LargeAsteroid(10, 10, NORTH);
        Jill = new LargeAsteroid(100, 400, SW);
        Jeff = new LargeAsteroid(500, 100, NW);
        Jim = new LargeAsteroid(600, 300, EAST);
        John = new LargeAsteroid(200, 100, SOUTH);

//TODO: Remove all bull
        bull = new Bullet();

        bull = new Bullet((int)Haiti.getLoc().getX(), (int)Haiti.getLoc().getY(), EAST, Haiti.getSpeed()+5);

        points = 0;
        Level = 0;
        Lives = 50;


        obstacles = new ArrayList<Sprite>();
        obstacles.add(Derek); //The RocketShip

        obstacles.add(Jack);
        obstacles.add(Jill);
        obstacles.add(Jeff);
        obstacles.add(Jim);
        obstacles.add(John);

        ridemyoto = new ArrayList<Bullet>(); //leave empty when game starts


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
                if (keys[KeyEvent.VK_X]) {
                    ridemyoto.add(new Bullet((int)Haiti.getLoc().getX(), (int)Haiti.getLoc().getY(), EAST, Haiti.getSpeed()+5));
                    keys[KeyEvent.VK_X] = false; //probably.
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


                //make Spaceship1 Derek fire at the RocketShip Haiti
//                if(timer.){
//                Derek.setLoc(new Point(600, 600)
//                }



                //update each obstacle
                //check for collisions


                for (int j = 0; j < obstacles.size(); j++) {
                    Sprite o = obstacles.get(j);

                    o.update();
//                    System.out.println("check");


                    if (Haiti.intersects(o) == true && o instanceof Obstacle) {
                        Haiti.setLoc(new Point(400, 400));
                        System.out.println("hit");
                        Lives--;
                        //maybe add a delay to the timer here
                    }


                    for (int i = 0; i < ridemyoto.size(); i++) {
                        if (o.intersects(ridemyoto.get(i))) {
                            points += 20;
                            ridemyoto.remove(i);
                            obstacles.remove(j);

                            Lateef.setLoc(o.getLoc());
                            obstacles.add(Lateef);

                            Ho.setLoc(o.getCenterPoint());
                            obstacles.add(Ho);

                            i = ridemyoto.size();

                        }
                        if(ridemyoto.get(i).intersects(Lateef) == true){
                                ridemyoto.remove(i);
                                obstacles.remove(Lateef);
                                Tony.setLoc(o.getLoc());
                                obstacles.add(Tony);
                            }

                        // currently removes large asteroid and replaces it with medium asteroids Ho and Lateef
                        //figure out how to replace medium asteroids with 2 small ones


                        //these lines cause the timer stop error / don't work

//                            //if(ridemyoto.get(i).intersects(Lateef) == true){
//                                //ridemyoto.remove(i);
//                              //  obstacles.remove(Lateef);
////                                Tony.setLoc(o.getLoc());
////                                obstacles.add(Tony);
//                            }
////                            if(ridemyoto.get(i).intersects(Ho) == true){
////                                //ridemyoto.remove(i);
////                                obstacles.remove(Ho);
////                                Daniel.setLoc(o.getCenterPoint());
////                                obstacles.add(Daniel);
////
////                            }

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

                for (Sprite o : obstacles) {
                    if (o.getLoc().getX() < -5) {
                        //o.setSpeed(-o.getSpeed());
                        //o.setLoc(new Point(x, y));
                    }
                    if (o.getLoc().getX() > 1005) {
                        //o.setSpeed(-o.getSpeed());
                        //o.setLoc(new Point(x, y));
                    }
                }




                if (Lives == 0 || Lives < 0 ) {
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


//                if (Jack.getLoc().getX() > Main.FRAMEWIDTH) {
//                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
//                }
//                if (Jack.getLoc().getX() < 0) {
//                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
//                }
//                if (Jack.getLoc().getY() > Main.FRAMEHEIGHT)
//                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
//                if (Jack.getLoc().getY() < 0)
//                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));


                if (Level == 1) {

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


        g2.setColor(Color.BLUE);
        g2.drawString("Lives:" + Lives, 100, 100);
        g2.drawString("Points:" + points, 100, 140);
        g2.drawString("Level:" + Level, 100, 120);



        Haiti.draw(g2);


        if(Lives == 0){
            g2.drawString("GAME OVER", 500, 500);

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


