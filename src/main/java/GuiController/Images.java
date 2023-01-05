package GuiController;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Images {

    private Image Duke;
    private Image Contessa;
    private Image Captain;
    private Image Ambassador;
    private Image Assassin;

    public Images() throws FileNotFoundException {
        InputStream stream1 = new FileInputStream("src/main/resources/Noble.jpg");
        Duke=new Image(stream1);

        stream1= new FileInputStream("src/main/resources/Commander.jpg");
        Captain=new Image(stream1);

        stream1= new FileInputStream("src/main/resources/Princess.jpg");
        Contessa=new Image(stream1);

        stream1= new FileInputStream("src/main/resources/Ambassador.jpg");
        Ambassador=new Image(stream1);

        stream1= new FileInputStream("src/main/resources/Killer.jpg");
        Assassin=new Image(stream1);
    }

    public Image getImage(String s){
        switch(s){
            case "Assassin":
                return Assassin;

            case "Ambassador":
                return Ambassador;

            case "Contessa":
                return Contessa;

            case "Captain":
                return Captain;

            case "Duke":
                return Duke;

            default:
                return Duke;
        }
    }

}
