import greenfoot.*;

public class koin extends Actor {
    private int scoreValue = 10; // Nilai skor yang diberikan oleh koin

    public koin() {
        setImage("koin.jpg");
    }

    public void act() {
        checkForCollision();
    }

    public void checkForCollision() {
    Actor character = getOneIntersectingObject(Character.class);
    if (character != null) {
        ((Character) character).increaseScore(); // Menghapus argumen
        getWorld().removeObject(this); // Hapus koin
    }
    }
 
}
