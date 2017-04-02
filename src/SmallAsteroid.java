/**
 * Created by matthew_ludwig on 2/15/17.
 */
public class SmallAsteroid extends Asteroid {

    public SmallAsteroid (int x, int y, int direction){
        super(x, y, WEST);
        setPic("aster.png", WEST);
        setSpeed(20);

    }


    @Override
    public void update() {


        super.update();


    }



}
