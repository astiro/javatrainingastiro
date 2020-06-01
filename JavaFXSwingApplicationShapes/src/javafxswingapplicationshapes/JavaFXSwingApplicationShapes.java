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
 * Class JavaFXSwingApplicationShapes extinde JApplet.
 * Applet este prima tehnologie utilizata de SUN pentru a creea continut dinamic,
 * incluzand aici si desenarea de obiecte geopmetrice, in browsere, Netscape
 * fiind la acel moment singurul browser pe piata. Tehnologia era integrata 
 * direct in browser, in asa fel incat Java era considerat limbaj nativ pentru 
 * web. Evolutia ulterioara a frameworkurilor de reprezentare a fat-client, a
 * inclus AWT initial, cu componente usoare si grele pentru ordonarea pe axa z,
 * a profunzimii si apoi Swing, fiind urmata imediat de JFC care ulterior avea
 * sa incluida Swing pentru compatbiilitatea in migrarea vechiilor GUI in Web.
 * Clasa din care deriva clasa noastra, este o versiune extinsa a 
 * java.applet.Applet, clasa care adauga suport pentru arhitectura 
 * bazata pe componente JFC/Swing. Detalii despre utilizare pot fi gasite aici:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/applet.html
   Clasa JApplet are mici incopatibilitati cu clasa java.applet.Applet. 
   JApplet contine un obiect JRootPane ca si copil unic. 
   contentPane trebuie sa fie parintele oricarui copil al lui JApplet. 
   Pentru o mai usoara intelegere si utilizare, metodele add(...), remove(..) 
 * si setLayout(...) au fost suprascrise in asa fel incat sa delege catre 
 * contentPane toate actiunile. Astfel se p[oate scrie:

       applet.add(child);
 
   astfel copilul fiind adaugat direct in contentPane, care va fi intotdeauna
   * nenull. Incercarea de ai da valoarea null va genera o exceptie. 
   contentPane implicit va avea un obiect BorderLayout manager asociat. 
   Detalii despre cum se adauga, se sterge sau se seteaza un obiect JApplet pot
   fi gasite in documentatia obiectului RootPaneContainer aici: 
   https://docs.oracle.com/javase/7/docs/api/javax/swing/RootPaneContainer.html
   Descrierea completa referitoare la diferitele proprietati ale containerelor
   JRootPane: contentPane, glassPane, si layeredPane pot fi gasite aici:
   https://docs.oracle.com/javase/7/docs/api/javax/swing/JRootPane.html - aici
   sunt prezentate detalii inclusiv grafice despre organizarea arhitecturii. 
   
 * @author Tudor Ianos
 */
public class JavaFXSwingApplicationShapes extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    /**
     public class JFXPanel extends JComponent
    JFXPanel este o componenta care incapsuleaza continutul JavaFX in aplicatiile
    Swing. 
    Continutul ce trebuie afisat este specificat prin apelul metodei
    setScene(javafx.scene.Scene) care are ca parametru obiectul JavaFX Scene. 
    Dupa ce "scena" este definita, se face un repaint automat. 
    Toate evenimentele si focusul sunt transmise catre scena in mod transparent
    pentru developer..
    Sunt insa si restrictii pentru JFXPanel, restrictii cu este urmatoarea: 
    * Ca si component Swing, obiectul trebuie sa fie accesat NUMAI de catre event 
    * dispatch thread cu exceptia metodei setScene(javafx.scene.Scene), 
    * ce poate fi apelata ori de catre dispatchul de evenimente al threadului
    * ori din threadul aplicatiei JavaFX.

    Un exemplu tipic de cod despre cum se utilizeaza JFXPanel:

     public class Test {

         private static void initAndShowGUI() {
             // This method is invoked on Swing thread
             JFrame frame = new JFrame("FX");
             final JFXPanel fxPanel = new JFXPanel();
             frame.add(fxPanel);
             frame.setVisible(true);

             Platform.runLater(new Runnable() {
                 @Override
                 public void run() {
                     initFX(fxPanel);
                 }
             });
         }

         private static void initFX(JFXPanel fxPanel) {
             // This method is invoked on JavaFX thread
             Scene scene = createScene();
             fxPanel.setScene(scene);
         }

         public static void main(String[] args) {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     initAndShowGUI();
                 }
             });
         }
     }
     */
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // apelul metodei invokeLater() conform cu patternul de mai sus
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                System.err.println(e);
            }
            // creearea obiectului JFrame: doua linkuri sunt utile pentru detalii: 
            // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html si
            // https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
            JFrame frame = new JFrame("Drawing Shapes using JavaFX 2 in Swing");
            // setarea actiunii Close implicite
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creearea obiectuilui JApplet - vedeti comentariul de clasa si linkurile asociate
            JApplet applet = new JavaFXSwingApplicationShapes();
            // initializarea obiectului de tip JApplet
            applet.init();
            // setarea contentPane - vedeti comentariul de clasa
            frame.setContentPane(applet.getContentPane());
            // setarea atributelor frameului
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // lansarea appletului
            applet.start();
        });
    }
    /**
     Suprascierea metodei de initializare - vedeti patternul de mai sus.
     * care creeaza fxContainer
     */
    @Override
    public void init() {
        // creeare nou panel - pentru contentul din Frame
        fxContainer = new JFXPanel();
        // setarea dimensiunilor acestuia la valorile predefinite - de aici le putem 
        // modifica daca dorim
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        // adaugare mod de adaugare fata de borderul layoutului - centrat
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    /**
     Creearea continutului scenei
     */
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
        // setarea acestei scene ca si continut pentru fxContainer()
        fxContainer.setScene(new Scene(root));
    }
    
}
