// make sure to add Squint as a library in your project settings!
// with intellij, right-click and "add library"
import squint.GUIManager;
import squint.SImage;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageViewer extends GUIManager {
    private final int WINDOW_WIDTH  = 500, WINDOW_HEIGHT = 600;
    private String mode;

    // A slider to control the number of brightness levels used
    private JSlider leveler = new JSlider( 1, 256, 256 );
    private JLabel levelerLabel = new JLabel( "Slider level: " + leveler.getValue() );

    // Button to request loading a new image
    private JButton loadImage = new JButton( "Load Image" );

    // Button to move to brightness-adjusting mode
    private JButton brightnessMode = new JButton( "Adjust Brightness" );

    // Button to move to quantization mode
    private JButton quantizeMode = new JButton("Quantize");

    // Dialog box used to select an image to load
    private JFileChooser chooser = new JFileChooser( new File( System.getProperty("user.dir") + "/AllImages") );

    // The underlying picture to be displayed
    private SImage picture;

    // The image displayed after requested transformation of "picture"
    private SImage displayed;

    // Label used to display "displayed"
    private JLabel displayImage = new JLabel( "", SwingConstants.CENTER );

    public ImageViewer() {
        // Create window to hold all the components
        this.createWindow( WINDOW_WIDTH, WINDOW_HEIGHT );
        mode = "";

        contentPane.setLayout( new BorderLayout() );
        contentPane.add( new JScrollPane( displayImage ), BorderLayout.CENTER );

        JPanel controlPane = new JPanel();
        controlPane.setLayout( new GridLayout( 4, 1) );

        JPanel levelerPane = new JPanel();
        levelerPane.add( levelerLabel );
        levelerPane.add( leveler );
        controlPane.add( levelerPane );

        controlPane.add( loadImage );
        controlPane.add( brightnessMode );
        controlPane.add( quantizeMode );


        contentPane.add( controlPane, BorderLayout.SOUTH );
    }

    // Adjust image display when slider is moved
    public void sliderChanged() {
        int sliderValue = leveler.getValue();
        levelerLabel.setText("Slider level: " + sliderValue);
        int[][] pixels = picture.getPixelArray();



        displayed = new SImage(pixels);
        displayImage.setIcon(displayed);
    }



    // Load an image when the button is clicked
    public void buttonClicked( JButton which ) {
        if ( which == loadImage ) {
            if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION ) {
                setPic( new SImage( chooser.getSelectedFile().getAbsolutePath() ) );
            }
        }
        else if ( which == brightnessMode ) {
            mode = "brightness";
        }
        else if (which == quantizeMode) {
            mode = "quantize";
        }
    }

    // Change the picture to be displayed
    private void setPic( SImage newPic ) {
        picture = newPic;

        displayImage.setIcon( picture );
    }

    public static void main(String[] args) {
        ImageViewer iv = new ImageViewer();
    }
}

