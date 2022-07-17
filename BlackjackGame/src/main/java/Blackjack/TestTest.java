package Blackjack;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class TestTest {
   private JFrame mainFrame;
   private JLabel title;
   private JLabel ligmaBalls;
   private JPanel controlPanel;

   public TestTest(){
      prepareGUI();
   }
   public static void main(String[] args){
      TestTest  swingControlDemo = new TestTest();      
      swingControlDemo.shownewGameButtonDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      title = new JLabel("", JLabel.CENTER);        
      ligmaBalls = new JLabel("",JLabel.CENTER);    
      ligmaBalls.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(title);
      mainFrame.add(controlPanel);
      mainFrame.add(ligmaBalls);
      mainFrame.setVisible(true);  
   }
   private static ImageIcon createImageIcon(String path, String description) {
      java.net.URL imgURL = TestTest.class.getResource(path);
      if (imgURL != null) {
         return new ImageIcon(imgURL, description);
      } else {            
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }   
   private void shownewGameButtonDemo(){
      title.setText("Control in action: newGameButton"); 

      //resources folder should be inside SWING folder.
      ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");

      JButton oknewGameButton = new JButton("OK");        
      JButton javanewGameButton = new JButton("Submit", icon);
      JButton cancelnewGameButton = new JButton("Cancel", icon);
      cancelnewGameButton.setHorizontalTextPosition(SwingConstants.LEFT);   

      oknewGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ligmaBalls.setText("Ok newGameButton clicked.");
         }          
      });
      javanewGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ligmaBalls.setText("Submit newGameButton clicked.");
         }
      });
      cancelnewGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ligmaBalls.setText("Cancel newGameButton clicked.");
         }
      });
      controlPanel.add(oknewGameButton);
      controlPanel.add(javanewGameButton);
      controlPanel.add(cancelnewGameButton);       

      mainFrame.setVisible(true);  
   }
}
