package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.Ventanas.VentanaDeJuego;

import java.io.FileNotFoundException;

/**
 * La hilera Básica solo tiene una hilera de naves uniformes.
 */
public class HileraBasic implements Hilera{
    private static final ListFactory listFactory = (ListFactory) FactoryProvider.getFactory("List");
    /**
     * Instancia Case Basica
     */
    public HileraBasic(){
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        Lista<NaveEnemiga> listaBasic = listFactory.create("Simple");
        listaBasic.agregarUltimo(new NaveEnemiga(110, 100, juego, 0));
        listaBasic.agregarUltimo(new NaveEnemiga(220, 100, juego, 1));
        listaBasic.agregarUltimo(new NaveEnemiga(330, 100, juego, 2));
        listaBasic.agregarUltimo(new NaveEnemiga(440, 100, juego, 3));
        listaBasic.agregarUltimo(new NaveEnemiga(550, 100, juego, 4));
        currentClass.setClass("BA", listaBasic, null);
    }
}