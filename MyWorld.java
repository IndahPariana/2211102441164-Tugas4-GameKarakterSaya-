import greenfoot.*;
import java.util.List;

public class MyWorld extends World {
    private GreenfootSound startSound = new GreenfootSound("sounds/sound0.mp3");
    private int currentLevel = 1;
    
    public MyWorld() {
        super(1200, 700, 1);
        prepare();
    }
    
    public void act() {
        if (getObjects(ObjectSpecial.class).isEmpty()) {
            if (currentLevel < 3) { // Ganti angka ini sesuai jumlah level
                currentLevel++;
                addObject(new LevelComplete(currentLevel), getWidth() / 2, getHeight() / 2);
                Greenfoot.delay(100);
                nextLevel();
            } else {
                showText("Game Over - You Win!", getWidth() / 2, getHeight() / 2);
                Greenfoot.stop();
            }
        }
    }
    
    public void prepare() {
        addObject(new Character(), getWidth() / 2, getHeight() - 30);
        addObject(new ObjectSpecial(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        playStartSound();
    }
    
    public void nextLevel() {
        removeObjects(getObjects(ObjectSpecial.class));
        removeObjects(getObjects(LevelComplete.class));
        prepare();
    }
    
    public void playStartSound() {
        startSound.play();
    }
}
