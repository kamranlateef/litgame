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

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    private Timer timer;
    private boolean[] keys;
    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;


    private Sprite Haiti;
    private SpaceShip1 Derek;
    private SmallAsteroid Tony;
    private MediumAsteroid Lateef;
    private LargeAsteroid Jack;

    private int Level;
    private int Lives;
    private ArrayList<Sprite> obstacles;






    public Main(){

        keys = new boolean[513]; //should be enough to hold any key code.

        Haiti = new RocketShip();
        Derek = new SpaceShip1(400, 400, NORTH);
        Tony = new SmallAsteroid(500, 500, NORTH);
        Lateef = new MediumAsteroid(300, 200, NORTH);
        Jack = new LargeAsteroid(100, 100, NORTH);


        Level = 0;
        Lives = 5;



        obstacles = new ArrayList<Sprite>();
        obstacles.add(Derek);

        obstacles.add(Jack);


        obstacles.add(Tony);
        obstacles.add(Lateef);


        System.out.println();





        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


//                move rocketship in direction



                    if(keys[KeyEvent.VK_W] = true) {
                        Haiti.setDir(Haiti.getDir());
                        Haiti.setSpeed(10);
                        Haiti.update();
//                   keys[KeyEvent.VK_W] = false; //probably.
                    }






                //update each obstacle
                //check for collisions

                for(Sprite o: obstacles){
                    o.update();
//                    System.out.println("check");

                    if(Haiti.intersects(o) == true){
                        Haiti.setLoc(new Point(400, 400));
                        System.out.println("hit");
                        Lives--;
                    }


                }


                if(Lives == 0 || Lives < 0){
                    timer.stop();
                }
                if (Haiti.getLoc().getX()>1000)
                    Haiti.setLoc(new Point(0, (int)Haiti.getLoc().getY()));

                if (Haiti.getLoc().getX()<0)
                    Haiti.setLoc(new Point(1000, (int)Haiti.getLoc().getY()));




                if(Level == 1){

                }

                if(Level == 2){

                }

                if(Level == 3){


                }


                //decrease the speed of the rocket ship
                if(keys[KeyEvent.VK_S]){
                    //Haiti.setDir(Sprite.SOUTH);
                    Haiti.setSpeed(Haiti.getSpeed()-5);
                    Haiti.update();
                    //keys[KeyEvent.VK_S] = false; //probably.
                }


                //rotate the rocketship left
                if(keys[KeyEvent.VK_A]){
                    Haiti.rotateBy(20);
                    Haiti.update();
                    keys[KeyEvent.VK_A] = false; //probably.
                }


                //rotate rocketship right
                if(keys[KeyEvent.VK_D]){
                    Haiti.rotateBy(-20);
                    Haiti.update();
                    keys[KeyEvent.VK_D] = false; //probably.
                }


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


        for(Sprite o: obstacles){ //for each of the Sprites in the arrayList obstacles draw it
            o.draw(g2);
        }




        g2.setColor(Color.BLUE);
        g2.drawString("Lives:" + Lives, 300, 300 );
        g2.drawString("Level:" + Level, 300, 320 );


        Haiti.draw(g2);





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
