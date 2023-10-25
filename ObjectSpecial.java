import greenfoot.*;

public class ObjectSpecial extends Actor {
    public ObjectSpecial() {
        setImage("alien.png");
    }

    public void act() {
        checkForCollision();
         move(3); // Menggerakkan objek alien ke depan dengan kecepatan 2 piksel per aksi
        if (isAtEdge()) {
            turn(180); // Jika objek alien mencapai tepi layar, balikkan arahnya (180 derajat)
        }
    }

    public void checkForCollision() {
    Actor character = getOneIntersectingObject(Character.class);
      if (character != null) {
        ((Character) character).increaseScore();
        getWorld().removeObject(this);
      }
    
    Actor object = getOneIntersectingObject(ObjectSpecial.class);
      if (object != null) {
        ((ObjectSpecial) object).die(); // Panggil metode die() saat tembakan karakter mengenai alien
        return; // Hentikan eksekusi kode selanjutnya, karena alien sudah mati.
      }
    }

    
    public void die() {
    GreenfootImage deathImage = new GreenfootImage("death.png");
    setImage(deathImage);
    
    // Hapus alien dari dunia setelah selesai animasi
    getWorld().removeObject(this);
    }
}


