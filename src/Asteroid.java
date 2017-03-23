import java.awt.*;

/**
 * Created by matthew_ludwig on 2/16/17.
 */
public class Asteroid extends Obstacle {


    public Asteroid (int x, int y, int direction){

        super(x, y, WEST);

    }



    public void update(){
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
