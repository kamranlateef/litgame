import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by kamran_lateef on 3/13/17.
 */

public class Main extends JPanel {

//    To do:
//
//
//
//-complete large asteroids, go off screen and come back on the other side, spawn randomly on screen and go in random directions
//-add working bullets
//-have large asteroids turn into 2 med asteroids when hit
//-have two med asteroids turn into 2 small asteroids when hit
//-add point system
//	          -small: 20
//            -med: 50
//            -large: 100
//            -spaceships: 200
//            -make rocketship explode when it gets hit
//-add crazy levels and shit
//


    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1430, FRAMEHEIGHT = 1000;
    private Timer timer;
    private boolean[] keys;
    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;


    private Sprite Haiti;
    private SpaceShip1 Derek;
    private SmallAsteroid Tony;
    private MediumAsteroid Lateef;

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
    int x = (int) (Math.random() * 1000);
    int y = (int) (Math.random() * 600);


    public Main() {

        keys = new boolean[513]; //should be enough to hold any key code.

        Haiti = new RocketShip();

        Derek = new SpaceShip1(400, 200, NORTH);

        Tony = new SmallAsteroid(500, 300, NORTH);


        Lateef = new MediumAsteroid(300, 200, NORTH);


        Jack = new LargeAsteroid(10, 10, NORTH);
        Jill = new LargeAsteroid(100, 400, NORTH);
        Jeff = new LargeAsteroid(500, 100, NORTH);
        Jim = new LargeAsteroid(600, 300, NORTH);
        John = new LargeAsteroid(200, 100, NORTH);


        //bull = new Bullet();



        points = 0;
        Level = 0;
        Lives = 50;


        obstacles = new ArrayList<Sprite>();
        obstacles.add(Derek);

        obstacles.add(Jack);
        obstacles.add(Jill);
        obstacles.add(Jeff);
        obstacles.add(Jim);
        obstacles.add(John);



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
                if (keys[KeyEvent.VK_X]) { // we can try to get spacebar later.
                    //obstacles.add(bull);
                    keys[KeyEvent.VK_X] = false; //probably.
                }

                if (keys[KeyEvent.VK_P]) {
                    Lives += 50;
                    keys[KeyEvent.VK_P] = false; //probably.
                }


                //update each obstacle
                //check for collisions

                for (Sprite o : obstacles) {
                    o.update();
//                    System.out.println("check");

                    if (Haiti.intersects(o) == true) {
                        Haiti.setLoc(new Point(400, 400));
                        System.out.println("hit");
                        Lives--;
                    }


                }

                for (Sprite o : obstacles) {
                    if (o.getLoc().getX() < -5) {
                        //o.setSpeed(-o.getSpeed());
                        o.setLoc(new Point(x, y));
                    }
                    if (o.getLoc().getX() > 1005) {
                        //o.setSpeed(-o.getSpeed());
                        o.setLoc(new Point(x, y));
                    }
                }


                if (Lives == 0 || Lives < 0) {
                    timer.stop();
                }
                if (Haiti.getLoc().getX() > 1430)
                    Haiti.setLoc(new Point(0, (int) Haiti.getLoc().getY()));

                if (Haiti.getLoc().getX() < 0)
                    Haiti.setLoc(new Point(1000, (int) Haiti.getLoc().getY()));

                if (Haiti.getLoc().getY() > 1000)
                    Haiti.setLoc(new Point((int) Haiti.getLoc().getX(), 0));

                if (Haiti.getLoc().getY() < 0)
                    Haiti.setLoc(new Point((int) Haiti.getLoc().getX(), 600));


                if (Jack.getLoc().getX() > Main.FRAMEWIDTH) {
                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
                }
                if (Jack.getLoc().getX() < 0) {
                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
                }
                if (Jack.getLoc().getY() > Main.FRAMEHEIGHT)
                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
                if (Jack.getLoc().getY() < 0)
                    Jack.setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));


                if (Level == 1) {

                }

                if (Level == 2) {

                }

                if (Level == 3) {


                }


                //decrease the speed of the rocket ship


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


        g2.setColor(Color.BLUE);
        g2.drawString("Lives:" + Lives, 100, 100);
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
