package main;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    Panel(){

    }


    public void setSize(int x, int y){
        setPreferredSize(new Dimension(x, y));
    }
}
