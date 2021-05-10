// make sure to add Squint as a library in your project settings!
// with intellij, right-click and "add library"
import squint.GUIManager;
import java.awt.*;
import java.io.File;

public class ImageViewer extends GUIManager {

    public void buttonClicked()
    {

    }

    public static void main(String[] args)
    {
        GUIManager gm = new GUIManager();
        gm.createWindow(500, 500,"Test Window");
    }
}

