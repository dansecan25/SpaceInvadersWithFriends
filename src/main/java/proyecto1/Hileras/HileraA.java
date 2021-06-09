package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.Ventanas.VentanaDeJuego;


/**
 * Hilera de naves tipo A
 */
public class HileraA implements Hilera{
    private static final ListFactory listFactory = (ListFactory) FactoryProvider.getFactory("List");

    /**
     * Constructor de la hilera
     */
    public HileraA(){
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        Lista<NaveEnemiga> listaA= listFactory.create("Simple");

        currentClass.setClass("A", listaA,null);
        listaA.agregarUltimo(new NaveEnemiga(110, 100, juego, 0));
        listaA.agregarUltimo(new NaveEnemiga(220, 100, juego, 1));
        listaA.agregarUltimo(new NaveEnemiga(330, 100, juego, 2));
        listaA.agregarUltimo(new NaveEnemiga(440, 100, juego, 3));
        listaA.agregarUltimo(new NaveEnemiga(550, 100, juego, 4));
        double posBoss= Math.random()*4;
        int posJefe= (int) posBoss;

        NaveEnemiga naveJefe= listaA.obtenerDato(posJefe);
        naveJefe.toBoss();
    }
}
