package proyecto1.Animaciones;

import javafx.concurrent.Task;
import javafx.scene.Group;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Excepciones.InvalidDirectionException;
import proyecto1.Trees.Node;
import proyecto1.Trees.Tree;

public class TreeEnemysAnimation {
    private static Task<Void> animation;
    private static Tree Tree = currentClass.getTreecito();

    /**
     * Starts the thread that moves the ships through the screen
     * @param gameWindow the Group of the game window for future usage
     */
    public static void AnimationStart(Group gameWindow) {

        animation = new Task<Void>() {
            @Override
            protected Void call() {
                int movements=0;
                MoveThemShips("Right");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (movements <= 3) {
                    try {
                        MoveThemShips("Down");
                        Thread.sleep(2000);
                        MoveThemShips("Left");
                        Thread.sleep(6400);
                        MoveThemShips("Down");
                        Thread.sleep(2000);
                        MoveThemShips("Right2");
                        Thread.sleep(6400);
                        movements+=1;
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        animation.setOnSucceeded(event -> {
            System.out.println("se animo");
        });
        new Thread(animation).start();
    }

    /**
     * Method for moving the ships in the direction specified
     * @param direction a String identifier on which direction the ship moves
     */
    private static void MoveThemShips(String direction){
        for (int i = 0; i <= 7; i++) { //iterates for the amount of ships that exist
            Node tryShip = Tree.find(i);
            if (tryShip != null) {
                NaveEnemiga Ship = Tree.find(i).element;
                switch(direction){
                    case "Right"->Ship.moveRight();
                    case "Down"->Ship.moveDown();
                    case "Left"->Ship.moveLeft();
                    case "Right2"->Ship.moveRight2();
                }
            }
        }
    }
}
