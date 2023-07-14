package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;

public class temp implements ActionListener {
    MainFrame frame;
    Panel panel;
    JButton submit, switchPage, clear;
    ArrayList<JTextField> textFields = new ArrayList<>(15);
    public static Font myFont = new Font("Ink Free", Font.BOLD,40);


    temp(){
        frame = new MainFrame("Gothic 2 best build calculator");

        for(int i = 0; i<15; i++)
            textFields.add(new JTextField());


        textFields.get(0).setBounds(400, 90, 300, 100);
        textFields.get(1).setBounds(400, 210, 300, 100);
        textFields.get(2).setBounds(400, 330, 300, 100);
        textFields.get(3).setBounds(400, 450, 300, 100);
        textFields.get(4).setBounds(30, 90, 300, 100); //skil
        textFields.get(5).setBounds(30, 210, 300, 100); //weapon
        textFields.get(6).setBounds(30, 330, 300, 100); //fight
        textFields.get(7).setBounds(30, 450, 300, 100); //strength

        for(JTextField x : textFields){
            x.setFont(myFont);
            frame.add(x);
            x.setBorder(null);
            x.setBackground(new Color(20, 20, 20));
            x.setForeground(new Color(70,70,70));
            x.setHorizontalAlignment(JTextField.CENTER);
        }



        for(int i = 0; i<4; i++){
            textFields.get(i).setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
            textFields.get(i).addActionListener(this);
            textFields.get(i).setActionCommand(String.valueOf(i));
        }

        for(int i = 4; i<15; i++){
            textFields.get(i).setEditable(false);
            textFields.get(i).setFont(myFont);
            textFields.get(i).setSelectionColor(null);
        }

        textFields.get(14).setText("Gothic 2 best build calculator");
        textFields.get(14).setBounds(10,10,800,100);
        textFields.get(14).setFont(new Font("Ink Free", Font.BOLD,50));

        textFields.get(4).setText("skill points:");
        textFields.get(5).setText("Weapon damage:");
        textFields.get(6).setText("Strength:");
        textFields.get(7).setText("Fighting skills:");


        //Results
        for(int i = 8; i<14; i++){
            textFields.get(i).setBounds(0, 10+((i-8)*100), 800, 100);
            textFields.get(i).setVisible(false);
        }
        textFields.get(8).setFont(new Font("Ink Free", Font.BOLD,60));



        submit = new JButton();
        submit.setBounds(400, 620, 300, 100);
        submit.setText("submit");
        submit.setFont(new Font("Ink Free", Font.BOLD,50));
        submit.setBorder(null);
        submit.addActionListener(this);
        submit.setActionCommand("submit");
        submit.setFocusPainted(false);
        frame.add(submit);

        switchPage = new JButton();
        switchPage.setBounds(250, 620, 300, 100);
        switchPage.setText("back");
        switchPage.setFont(new Font("Ink Free", Font.BOLD,50));
        switchPage.setBorder(null);
        switchPage.addActionListener(this);
        switchPage.setActionCommand("back");
        switchPage.setVisible(false);
        switchPage.setFocusPainted(false);
        frame.add(switchPage);

        clear = new JButton();
        clear.setBounds(30, 620, 300, 100);
        clear.setText("clear");
        clear.setFont(new Font("Ink Free", Font.BOLD,50));
        clear.setFocusPainted(false);
        clear.setBorder(null);
        clear.addActionListener(this);
        clear.setActionCommand("clear");
        frame.add(clear);



        panel = new Panel();
        panel.setBackground(new Color(20, 20, 20));
        panel.setSize(800, 750);
        frame.add(panel);
        frame.pack();
    }


    public static void main(String []args) {
        new temp();


    }





    public float[] zainwestujPunkty(int sila, int walka, int oS, int oW, int oB){
        float profit;
        float [] result = new float[3];
        while (sila >= (1 + oS/30)){
            sila-=(1+oS/30);
            oS++;
        }
        while (walka >= (1 + oW/30)){
            if(oW>=100) break;
            walka-=(1+oW/30);
            oW++;
        }
        profit = (float) ((((oS + oB)*oW) + ((oS + oB) * (100-oW) *0.1)) /100);
        result[0] = profit;
        result[1] = oS;
        result[2] = oW;
        return result;
    }



    public void kalkulator(int skillPoints, int weaponDamage, int strength, int fightingSkills) throws IOException {
        int pN, oB, oS, oW;
        pN = skillPoints;
        oB = weaponDamage;
        oS = strength;
        oW = fightingSkills;


        float []statsA = new float[3];
        float []statsB;
        int i = 0;
        do{
            statsB = statsA;
            if(pN-i<0) break;
            statsA = zainwestujPunkty(pN-i, i, oS, oW, oB);
            i+=1+statsA[2]/30;
            //System.out.println(statsA[0] + " " + statsB[0]);
        }while(statsA[0] > statsB[0]);

        //System.out.println("Jak najoptymalniej moga wygladac twoje staty:");
        //System.out.println("sila: "+ statsB[1]);
        //System.out.println("Umiejetnosci walki: "+ statsB[2]);
        //System.out.println("Zwykly cios: " + (statsB[1] + oB)*0.1);
        //System.out.println("krytyczny cios: "+  (statsB[1] + oB));
        //System.out.println("Srednie obrazenia na cios: "+ ((((statsB[1] + oB)*statsB[2]) + ((statsB[1] + oB) * (100-statsB[2]) *0.1)) /100));
        textFields.get(8).setText("Best build:");
        textFields.get(9).setText("Strength: "+ statsB[1]);
        textFields.get(10).setText("Fighting skills: "+ statsB[2]);
        textFields.get(11).setText("Normal hit: " + (statsB[1] + oB)*0.1);
        textFields.get(12).setText("Critical hit: "+  (statsB[1] + oB));
        textFields.get(13).setText("Average hit damage: "+ ((((statsB[1] + oB)*statsB[2]) + ((statsB[1] + oB) * (100-statsB[2]) *0.1)) /100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("clear"))
            clear();
        else
            if(e.getActionCommand().equals("back"))
                switchPage();
            else
                if(e.getActionCommand().equals("submit"))
                    submitStats();
                else
                    switchTextFields(Integer.parseInt(e.getActionCommand()));
    }

    private void switchTextFields(int i){
        if(i>=3)
            submitStats();

        else
            textFields.get(i+1).requestFocusInWindow();
    }

    private void submitStats() {
        for(int i = 0; i<4; i++){
             if(textFields.get(i).getText().isEmpty()) textFields.get(i).setText("0");
        }
        try{
            kalkulator(Integer.parseInt(textFields.get(0).getText()),Integer.parseInt(textFields.get(1).getText()),Integer.parseInt(textFields.get(2).getText()),Integer.parseInt(textFields.get(3).getText()));
            for(int i = 0; i<8; i++)
                textFields.get(i).setVisible(false);

            for(int i = 8; i<14; i++)
                textFields.get(i).setVisible(true);

            submit.setVisible(false);
            clear.setVisible(false);
            switchPage.setVisible(true);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void switchPage(){
        for(int i = 0; i<8; i++)
            textFields.get(i).setVisible(true);

        for(int i = 8; i<14; i++)
            textFields.get(i).setVisible(false);
        submit.setVisible(true);
        clear.setVisible(true);
        switchPage.setVisible(false);
        for(int i = 0; i<4; i++){
            if(textFields.get(i).getText().equals("0"))
                textFields.get(i).setText(null);
        }
    }

    private void clear(){
        for(int i = 0; i<4; i++){
            textFields.get(i).setText(null);
        }
    }
}
