import java.awt.*;

public class RocketShip extends Sprite {

    public RocketShip(){
        super( 300, 300, NORTH );

        setPic("RocketShip 2.png", NORTH);
        //moves the height of the image.
//        setSpeed(this.getBoundingRectangle().height);
        //setSpeed(0)


    }
//    public void Bullet(){
//        setPic("bullet.png", NORTH);
//    }






        @Override
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