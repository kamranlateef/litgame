public class RocketShip extends Sprite {

    public RocketShip(){
        super( 300, 300, NORTH );

        setPic("RocketShip.png", NORTH);
        //moves the height of the image.
        setSpeed(this.getBoundingRectangle().height);



    }

    @Override
    public void update(){


        super.update();


    }






}