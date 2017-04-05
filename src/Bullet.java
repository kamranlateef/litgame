/**
 * Created by kamran_lateef on 3/27/17.
 */
public class Bullet extends Sprite{
    public Bullet(int x, int y, int direction, int speed){
        super(x, y, EAST);
        setPic("bullet1.png", WEST);
        setSpeed(10);
    }
    public Bullet(){
        setPic("bullet1.png", WEST);
        setSpeed(10);
    }
    @Override
    public void update(){
        super.update();
    }


}
