package Blackjack;
import java.awt.*;
import javax.swing.*;   

public class Main {

    private static JFrame frame;

    public static void main(String args[]){
        frame = new JFrame("Blackjack Game");
        frame.getContentPane().setBackground(Color.decode("#17a100"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        new Menu(frame);
     }
}
