/*
 * JavaFX with Swift program
 */
package javafxswingapplicationshapes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Tudor Ianos
 */
public class JavaFXSwingApplicationShapes extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                System.err.println(e);
            }
            
            JFrame frame = new JFrame("Drawing Shapes using JavaFX 2 in Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JApplet applet = new JavaFXSwingApplicationShapes();
            applet.init();
            
            frame.setContentPane(applet.getContentPane());
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            applet.start();
        });
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
        // create the list of circles, both full and empty
        LinkedList<Circle> listOfCircles = new LinkedList<>();
        // create the list of empty rectangles both full and empty
        LinkedList<Rectangle> listOfRectangles = new LinkedList<>();
        // creating a label
        Text text = new Text("Follow here messages!");
        //setting the position of the text 
        text.setX(20); 
        text.setY(10); 
        //Drawing a Circle 
        Circle circle = new Circle(300, 135, 100); 
        circle.setFill(Color.DARKSLATEBLUE); 
        circle.setStroke(Color.BLACK);
        circle.setCenterX(300); 
        circle.setCenterY(100);
        // Rectangle 
        Rectangle rectangle = new Rectangle (200,100);
        rectangle.setFill(Color.LAWNGREEN); 
        circle.setStroke(Color.BLACK);
        // radio buttons
        //Toggle group of radio buttons       
        ToggleGroup groupTypeOfShape = new ToggleGroup(); 
        RadioButton fullRadio = new RadioButton("full"); 
        fullRadio.setToggleGroup(groupTypeOfShape); 
        RadioButton emptyRadio = new RadioButton("empty"); 
        emptyRadio.setToggleGroup(groupTypeOfShape);
        // chopice box
        //Choice box for location 
        ChoiceBox shapesChoiceBox = new ChoiceBox(); 
        shapesChoiceBox.getItems().addAll
         ("Circle", "Rectangle"); 
        // create button full
        Button btnFull = new Button();
        btnFull.setText("Full");
        btnFull.setOnAction((ActionEvent eventButtonFullPushed) -> {
            System.out.println("Button Full pushed!");
            // read inputs combination and add to specific list of empty objects
        });
        // create button Empty
        Button btnEmpty = new Button();
        btnFull.setText("Empty");
        btnFull.setOnAction((ActionEvent eventButtonFullPushed) -> {
            System.out.println("Button Empty pushed!");
            // read inputs combination and add to specific list of full objects
        });
        // set buttons positions
        StackPane.setMargin(btnEmpty, new Insets(50, 50, 50, 50));
        StackPane.setMargin(btnFull, new Insets(50, 50, 50, 50));
        // add objects to root - here should both lists to be drawn
        //StackPane root = new StackPane(); 
        TilePane root = new TilePane();
        //Retrieving the observable list of the Stack Pane 
        ObservableList list = root.getChildren(); 
        //Adding all the nodes to the pane 
        list.addAll(circle, rectangle, text, btnFull, btnEmpty);
        
        fxContainer.setScene(new Scene(root));
    }
    
}
