package Blackjack;
import java.awt.*;
import javax.swing.*;   

public class Main {

    private static JFrame frame;

    public static void main(String args[]){
        frame = new JFrame("Blackjack Game");
        frame.getContentPane().setBackground(Color.decode("#17a100"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        new Menu(frame);
     }
}