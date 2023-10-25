import greenfoot.*;

public class Character extends Actor {
    private int score = 0;
    private int lives = 3; // Jumlah nyawa karakter
    private int speed = 3; // Kecepatan karakter
    private GreenfootImage[] characterImages;
    private int animationIndex = 0;
    private int initialX; // Variabel untuk menyimpan posisi awal karakter
    private int initialY;
    private int verticalSpeed = 3; // Kecepatan vertikal karakter
    private GreenfootImage[] shootImages;
    private int shootAnimationIndex = 0;
    private boolean isShooting = false;
    private GreenfootImage[] runImages;
    private int runAnimationIndex = 0;

    
    public Character() {
        characterImages = new GreenfootImage[7];
        for (int i = 0; i < 7; i++) {
            characterImages[i] = new GreenfootImage("images/IDL/IDL0" + i +".png");
        }
        setImage(characterImages[0]);
        
        shootImages = new GreenfootImage[7];
        for (int i = 0; i < 7; i++) {
            shootImages[i] = new GreenfootImage("images/ATTACK/ATTACK" + i + ".png");
        }
        
        runImages = new GreenfootImage[7]; 
        for (int i = 0; i < 7; i++) {
            runImages[i] = new GreenfootImage("images/RUN/RUN" + i + ".png");
          }

    }

      public void act() {
    moveCharacter();
    checkForCollision();
    checkGameOver();
    
    if (Greenfoot.isKeyDown("right")) {
        // Saat karakter bergerak ke kanan, animasi "RUN" akan dimainkan
        animateRun();
    } else {
        // Jika tidak ada input panah kanan, animasi karakter tetap idle ("IDL")
        animateCharacter();
    }
    
    if (Greenfoot.isKeyDown("space")) {
        // Saat tombol space ditekan, karakter menyerang
        isShooting = true;
    } else {
        isShooting = false; // Ketika tombol tidak ditekan, karakter berhenti menembak
    }

    animateShoot(); // Panggil metode animateShoot()
    
    if (isShooting) {
        animateShoot(); // Panggil metode animateShoot()
    }
}


    public void checkForCollision() {
        Actor object = getOneIntersectingObject(ObjectSpecial.class);
        if (object != null) {
            increaseScore();
            getWorld().removeObject(object);
        }

        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            loseLife();
            getWorld().removeObject(enemy);
        }
        
        Actor alien = getOneIntersectingObject(ObjectSpecial.class);
        if (alien != null) {
        loseLife(); // Metode untuk mengurangi nyawa
        }
    }
    
    public void moveCharacter() {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
        setLocation(getX(), getY() - verticalSpeed); // Gerakan karakter ke atas
        }
         if (Greenfoot.isKeyDown("down")) {
        setLocation(getX(), getY() + verticalSpeed); // Gerakan karakter ke bawah
        }
        
    }
    
     public void resetCharacterPosition() {
        // Mengembalikan karakter ke posisi awal
        setLocation(initialX, initialY);
    }

    public void increaseScore() {
        score += 1;
        getWorld().showText("Score: " + score, 50, 25);
    }

    public void loseLife() {
    lives--; // Mengurangi Jumlah nyawa
    getWorld().showText("Lives: " + lives, 50, 50);
    if (lives <= 0) {
        World world = getWorld(); // Dapatkan objek World
        world.showText("Game Over - You Lose!", world.getWidth() / 2, world.getHeight() / 2);
        Greenfoot.stop();
        }
    }


    public void checkGameOver() {
        if (score >= 100) {
            getWorld().showText("Game Over - You Win!", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop();
        }
    }
    
    public void animateCharacter() {
        if (animationIndex < characterImages.length - 1) {
            animationIndex++; // Pindah ke gambar berikutnya dalam urutan animasi
        } else {
            animationIndex = 0; // Kembali ke awal setelah mencapai gambar terakhir
        }
        setImage(characterImages[animationIndex]); // Atur gambar karakter sesuai indeks animasi
    }
    
    public void animateShoot() {
      if (isShooting) {
        setImage(shootImages[shootAnimationIndex]);
        shootAnimationIndex = (shootAnimationIndex + 1) % shootImages.length;
      }
    }
    
    public void animateRun() {
       setImage(runImages[runAnimationIndex]);
       runAnimationIndex = (runAnimationIndex + 1) % runImages.length;
    }

}