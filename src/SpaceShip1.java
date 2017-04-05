import java.awt.*;

/**
 * Created by matthew_ludwig on 2/14/17.
 */
public class SpaceShip1 extends Obstacle {

    public SpaceShip1(int x, int y, int direction) {
        super(x, y, NORTH);
        setPic("SpaceShip 2.png", NORTH);
        setSpeed(3);


    }


    @Override
    public void update() {


        if(getLoc().getX() > Main.FRAMEWIDTH) {
            setLoc(new Point((int) Math.random() * Main.FRAMEWIDTH, (int) (Math.random() * Main.FRAMEHEIGHT)));
        }
        if(getLoc().getX()<0){
            setLoc(new Point((int)Math.random()*Main.FRAMEWIDTH, (int)(Math.random()*Main.FRAMEHEIGHT)));
        }
        if (getLoc().getY()>Main.FRAMEHEIGHT)
            setLoc(new Point((int)Math.random()*Main.FRAMEWIDTH, (int)(Math.random()*Main.FRAMEHEIGHT)));
        if (getLoc().getY()<0)
            setLoc(new Point((int)Math.random()*Main.FRAMEWIDTH, (int)(Math.random()*Main.FRAMEHEIGHT)));


        super.update();




    }
}
