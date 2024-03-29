package proyecto1.Ventanas;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import proyecto1.Client;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Musica.ReproductorMusica;
import proyecto1.network.ClientSession;
import proyecto1.protocolo.Protocol;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientLoadMenuWindow {
    private boolean estadoMusica = true;
    public static String host = "127.0.0.1";
    public static int port = 9000;
    public static Socket clientSocket = null;
    public static ClientSession clientSession = null;
    /**
     * Instantiates a new Cargar ventana principal.
     *
     * @param root               the root
     * @param rectanguloCreditos the rectangulo creditos
     * @param Lobby              the lobby
     */
    public ClientLoadMenuWindow(Group root, Rectangle rectanguloCreditos, Stage Lobby, ReproductorMusica reproductorMusica) {

        root.getChildren().remove(rectanguloCreditos);

        //Se crea imagen Start
        ImageView img = new ImageView(Imagenes.getInstancia().getBotonStart());

        //Se crea imagen Titulo
        ImageInput titulo = new ImageInput(Imagenes.getInstancia().getTitulo());
        titulo.setX(167);
        titulo.setY(200);

        Rectangle tituloRectan= new Rectangle();
        tituloRectan.setEffect(titulo);
        root.getChildren().add(tituloRectan);

        //Imagen de la nave animada--------------------------------------------------------------------------------------------------------
        ImageInput naveUsuario = new ImageInput(Imagenes.getInstancia().getNaveAnimacion());
        Rectangle nave = new Rectangle(); //crea un rectangulo, nodo donde se insertará la imagen
        naveUsuario.setX(20); //posicione en x
        naveUsuario.setY(600); //posicion en y

        nave.setEffect(naveUsuario); //se le da al rectangulo la imagen
        TranslateTransition translate = new TranslateTransition(); //se inicia a crear la animacion
        translate.setByX(630); //destino final de la nave al moverse
        translate.setDuration(Duration.millis(1200)); //tiempo que dura trasladandose
        translate.setCycleCount(500); //cantitad de veces que se repite el traslado
        translate.setAutoReverse(true); //se regresa a la posicion original
        translate.setNode(nave); //se le asigna al rectangulo el efecto de animacion translate
        translate.play(); //se le da inicio a la animacion
        root.getChildren().add(nave);

        //Game window button------------------------------------------------------------------------------------------------------
        Button juegoInicia=new Button();
        juegoInicia.setOnAction(e -> {
            Lobby.hide(); //se esconde la ventana principal
            try {
                ClientWindow clientWindow = new ClientWindow(Lobby);


                //host = "192.168.86.24";
                //host = "127.0.0.1";
                clientSocket = new Socket(host, port);

                System.out.println("conectando al server . . . ");

                clientSession = new ClientSession(clientSocket);

                Thread clientSessionThread = new Thread(clientSession);

                clientSessionThread.start();

                Scanner scanner = new Scanner(System.in);

                System.out.println("presione enter para terminar");

                //VentanaDeJuego.iniciarVentanaDeJuego(Lobby); //se abre la ventana de juego
                //Client.clientSession.clientWriteMessage(Protocol.CMD_START, "P1");
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        juegoInicia.setLayoutX(265);
        juegoInicia.setLayoutY(420);
        juegoInicia.setGraphic(img); //Se le da al boton la imagen
        juegoInicia.setWrapText(true); //La verdad no se
        root.getChildren().add(juegoInicia); //crea el boton el canvas
        // crear boton sonido
        ImageView imu = new ImageView(Imagenes.getInstancia().getBotonMusicaON());
        Button music = new Button();
        music.setOnAction(e->{
            cambiarEstadoMusica();
            if (estadoMusica){
                reproductorMusica.reproducir();
                System.out.println("playing");
            } else{
                reproductorMusica.detener();
                System.out.println("not playing");
            }
        });
        music.setLayoutY(10);
        music.setLayoutX(10);
        music.setGraphic(imu);
        root.getChildren().add(music);
    }

    public boolean cambiarEstadoMusica(){
        estadoMusica = !estadoMusica;
        return estadoMusica;
    }
}
