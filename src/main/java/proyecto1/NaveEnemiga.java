package proyecto1;

import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NaveEnemiga{

    public NaveEnemiga(int x, int y, Group juego) throws FileNotFoundException{
        ImageView nave = new ImageView(Imagenes.getInstancia().getUfo1());
        nave.setX(x);
        nave.setY(y);
        juego.getChildren().add(nave);
    }
}