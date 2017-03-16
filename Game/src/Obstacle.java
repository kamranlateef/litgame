/**
 * Created by michael_hopps on 2/14/17.
 */
public class Obstacle extends Sprite {


    public Obstacle(int x, int y, int direction){ //, int dir){ ? some go left, some right.
        super(x, y, EAST);


    }

    public void update(){
        super.update();

    }







      /*
    You could modify this class to be a parent to each obstacle,
    and have Car and Lawnmower extend it.
    OR you could not do that, and have Car extend Sprite, etc.
    I'm not sure which is "better" here, so do what makes sense to you.
     */

    /*
    I'd recommend that each of your obstacles
    have images the same height as the frog.
    It will make movement easier and hit detection cleaner.
     */







}
