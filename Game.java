//color text hex code=323232
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener {
    private BufferedImage back;
    private ImageIcon background;
    private ImageIcon backgroundsmall;
    private ImageIcon title;
    private ImageIcon starttext;
    private ImageIcon LIVES;
    private ImageIcon Points;
    private ImageIcon wave1;
    private ImageIcon wave2;
    private ImageIcon wave3;
    private ImageIcon firerate;
    private ImageIcon guidebut;
    private ImageIcon chooseyourcharacter;
    private ImageIcon InstructionCharacterSelect;
    private ImageIcon mToCharacter;
    private ImageIcon Restart;
    private ImageIcon youwin;
    private ImageIcon youloose;
    private boolean start;
    private boolean gameStarted;
    private boolean manual;
    private boolean spacebar;
    private boolean pd;
    private boolean firerates;
    private boolean InsturctionScreen;
    private boolean CharacterSelection;
    private String characternamepng;
    private String characternamepng2;
    private String characterpm1;
    private String characterpm2;
    private String charactersound;
   
    //characters
    private Pictures brute;
    private Pictures warrior;
    private Pictures wizard;
    private Pictures theif;
    //character stats
    private Pictures warriorstats;
    private Pictures wizardstats;
    private Pictures brutestats;
    private Pictures theifstats;
    //characterselected gif
    private ImageIcon warriorselectedtxt;
    private ImageIcon wizardselectedtxt;
    private ImageIcon bruteselectedtxt;
    private ImageIcon theifselectedtxt;
    private ImageIcon btogoback;
    private ImageIcon bossidlegif;
    private ImageIcon bossattackgif;
    private ImageIcon onegif;
    private ImageIcon twogif;
    private ImageIcon threegif;
    private ImageIcon fourgif;
    private ImageIcon Ctoselect;
    private ImageIcon option1gif;
    private ImageIcon option2gif;
    private boolean collide;
    private Sound p;
    private double time;

    private int key;
    private int characterselected;
    private Pictures player;
    private Pictures player2;
    private ArrayList<PlayerMissle> playerMissiles;
    private ArrayList<PlayerMissle> playerMissiles2;
    private ArrayList<WizardSuper> wizardsuper;
    private ArrayList<WizardSuper> wizardsuper2;

    private ArrayList<LotsaGhosts> Ghosts;
    private ArrayList<Boss> Boss;
    private ArrayList<LotsaGhosts2> Ghosts2;
    private long currentTime;
    private long startTime;
    private long endTime;
    private double elapsedTime;
    private boolean stopTime;
    private int lives;
    private int liveslost;
    private int points;
    private int bosslives;
    private int wizardpointcharge;
    private boolean playMusic;
    private boolean stop;
    private long lastFiredTime = 0;
    private long reloadDelay = 150;
    private long music0 =0;
    private long musicdelay = 10000000;
    private boolean musicPlayed = false;
    private Pictures select1;
    private boolean selectiongif;
    private boolean bigscreen;
    private boolean bruteselected;
    private boolean wizardselected;
    private boolean warriorselected;
    private boolean theifselected;
    private boolean confirmselection;
    private boolean playerUpdated;
    private boolean option1;
    private boolean option2;
    private File saveFile;
    
    public Game() {
        p = new Sound();
        new Thread(this).start();
        background = new ImageIcon("background.png");
        btogoback = new ImageIcon("btogoback.gif");
        onegif = new ImageIcon("1.gif");
        twogif = new ImageIcon("2.gif");
        threegif = new ImageIcon("3.gif");
        fourgif = new ImageIcon("4.gif");
        saveFile= new File("savedfile2.0.txt");
        warriorselectedtxt = new ImageIcon("warriorselected.gif");
        wizardselectedtxt = new ImageIcon("wizardselected.gif");
        bruteselectedtxt = new ImageIcon("bruteselected.gif");
        theifselectedtxt = new ImageIcon("theifselected.gif");
        Ctoselect = new ImageIcon("pressCtoselect.png");
        backgroundsmall = new ImageIcon("backgroundsmall.png");
        title = new ImageIcon("Title1.png");
        starttext = new ImageIcon("starttext.gif");
        LIVES = new ImageIcon("LIVES.png");
        wave1 = new ImageIcon("wave1.gif");
        wave2 = new ImageIcon("wave2.gif");
        wave3 = new ImageIcon("wave3.gif");
        firerate = new ImageIcon("firerate.gif");
        Restart = new ImageIcon("restart.gif");
        guidebut = new ImageIcon("guidebut.gif");
        chooseyourcharacter = new ImageIcon("ChooseYourCharacter.png");
        mToCharacter = new ImageIcon("mToCharacter.gif");
        characternamepng = new String("");
        characternamepng2 = new String("");
        characterpm1 = new String("");
        characterpm2 = new String("");
        charactersound = new String("");

        youwin = new ImageIcon("youwin.gif");
        youloose = new ImageIcon("youloose.gif");
        brute = new Pictures("brute.png", 422, 250, 0, 0, 20, 40);
        brutestats = new Pictures("brutestats.gif", 422, 250, 0, 0, 20, 40);
        warriorstats = new Pictures("warriorstats.gif", 422, 250, 0, 0, 20, 40);
        theifstats = new Pictures("theifstats.gif", 422, 250, 0, 0, 20, 40);
        wizard = new Pictures("wizard.png", 422, 250, 0, 0, 20, 40);
        wizardstats = new Pictures("wizardstats.gif", 422, 250, 0, 0, 20, 40);
        select1 = new Pictures("select1.gif", 422, 250, 0, 0, 20, 40);
        warrior = new Pictures("warrior.png", 422, 250, 0, 0, 20, 40);
        theif = new Pictures("theif.png", 422, 250, 0, 0, 20, 40);
        bossidlegif = new ImageIcon("bossidle.gif");
        bossattackgif = new ImageIcon("bossattack.gif");
        option1gif = new ImageIcon("option1.gif");
        option2gif = new ImageIcon("option2.gif");
        Points = new ImageIcon("points.png");
        player = new Pictures("", 422, 250, 0, 0, 20, 40);
        InstructionCharacterSelect = new ImageIcon("InstructionCharacterSelect.gif");
        playerMissiles = new ArrayList<>();
        playerMissiles2 = new ArrayList<>();
        wizardsuper = new ArrayList<>();
        wizardsuper2 = new ArrayList<>();
        bruteselected=false;
        wizardselected=false;
        warriorselected=false;
        theifselected=false;
        selectiongif= false;
        playerUpdated = false;
        option1= false;
        option2=true;
        //bigscreen=false;
        start = true;
        firerates = false;
        InsturctionScreen = false;
        CharacterSelection = false;
        spacebar = false;
        key = -1;
        gameStarted = true;
        manual = true;
        Boss = setBoss();
        Ghosts = setGhosts();
        Ghosts2 = setGhosts2();
        this.addKeyListener(this);
        p.playmusic("mario.wav");
        pd = true;
        currentTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        elapsedTime = 0;
        stopTime = false;
        lives = 1;
        liveslost=0; 
        points = 0;
        bosslives = 15;
        wizardpointcharge = 0;
        stop = false;
        playMusic = true;
        confirmselection=false;
        setFocusable(true);
    }
    
    private ArrayList<Boss> setBoss() {
        ArrayList<Boss> temp = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new Boss(150, 110));
            }
        }
        return temp;
    }
    private ArrayList<LotsaGhosts> setGhosts() {
        ArrayList<LotsaGhosts> temp = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new LotsaGhosts((j * 75) - 500, i * 50));
                temp.add(new LotsaGhosts((j * 75) - 2150, i * 50));
                temp.add(new LotsaGhosts((j * 75) - 4400, i * 50));
            }
        }
        return temp;
    }

    private ArrayList<LotsaGhosts2> setGhosts2() {
        ArrayList<LotsaGhosts2> temp = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new LotsaGhosts2((j * 75) + 864, i * 50));
                temp.add(new LotsaGhosts2((j * 75) + 2414, i * 50));
                temp.add(new LotsaGhosts2((j * 75) + 4764, i * 50));
            }
        }
        return temp;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(5);
                move();
                repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playSoundWithVolume(String soundFilePath, float volume) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
    
           
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            
            gainControl.setValue(volume); 
    
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createFile(){
        try{
        if(saveFile.createNewFile()){
            System.out.println("syccessfykky created file");
        }
        else{
            System.out.println("file already exists");
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }
    }
    public void writeToFile(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(saveFile);
            if(points==30){
                myWriter.write("win");
            }
            else{
                myWriter.write("youhave"+points);
            }
            myWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
}
public void readFile(){
    Scanner sc;
    try {
        sc = new Scanner(saveFile);
        while(sc.hasNext()){
            System.out.println(sc.next());
    } 
}catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
}

    public void paint(Graphics g) {
        
        super.paint(g);
        Graphics2D twoDgraph = (Graphics2D) g;
        collisionwizardsuper1();
        collisionwizardsuper2();
        collisionboss();
        collision1();
        collision2();
     

        collisionPlayer();
        collisionPlayer2();

        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        Graphics g2d = back.createGraphics();
        g2d.clearRect(0, 0, getSize().width, getSize().height);

        if (spacebar && pd) {
            
        }
        
        for (PlayerMissle pp : playerMissiles) {
            if (player.getPic().equals(characternamepng) || player.getPic().equals(characternamepng2)) {
                pp.setX(-50);
            }
        }

        for (PlayerMissle pp2 : playerMissiles2) {
            if (player.getPic().equals(characternamepng2) || player.getPic().equals(characternamepng)) {
                pp2.setX(50);
                player.getx();
            }
        }
        for (WizardSuper ws : wizardsuper) {
            if (player.getPic().equals(characternamepng) || player.getPic().equals(characternamepng2)) {
                ws.setX(-25);
                
            }
        }
        for (WizardSuper ws2 : wizardsuper2) {
            if (player.getPic().equals(characternamepng2) || player.getPic().equals(characternamepng)) {
                ws2.setX(25);
                
            }
        }
        if (!start) {
            InsturctionScreen=false;
            CharacterSelection=false;
            g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            g2d.drawImage(title.getImage(), 370, 0, 300, 100, this);
            g2d.drawImage(new ImageIcon(player.getPic()).getImage(), player.getx(), player.gety(), 200, 200, this);
            
            drawGhosts(g2d);
            drawGhosts2(g2d);
            currentTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        elapsedTime = 0;
        stopTime = false;
        }
        if (manual == false) {
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(title.getImage(), 0, 100, 1000, 250, this);
            g2d.drawImage(mToCharacter.getImage(), 100, 250, 775, 150, this);
        }
        if (start && gameStarted) {
            
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(title.getImage(), 0, 100, 1000, 250, this);
            g2d.drawImage(mToCharacter.getImage(), 100, 250, 775, 150, this);
        }
        if(CharacterSelection && option2==true){
            selectiongif=true;  
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(onegif.getImage(), brute.getx()-400, 160, 35, 50, this);
            g2d.drawImage(twogif.getImage(), wizard.getx()-60, 160, 50, 50, this);
            g2d.drawImage(threegif.getImage(), warrior.getx()+240, 160, 50, 50, this);
            g2d.drawImage(chooseyourcharacter.getImage(), 0, 35, 500, 71, this);
            g2d.drawImage(InstructionCharacterSelect.getImage(), 500, 0, 500, 145, this);
            g2d.drawImage(new ImageIcon(brute.getPic()).getImage(), brute.getx()-340, brute.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(warrior.getPic()).getImage(), warrior.getx()+290, warrior.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(wizard.getPic()).getImage(), wizard.getx(), wizard.gety()-100, 200, 200, this);
            g2d.drawImage(option1gif.getImage(), 500, 350, 450, 100, this);
        }
       
        if(CharacterSelection && option1==true){
            selectiongif=true;  
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(twogif.getImage(), wizard.getx()-400, 160, 50, 50, this);
            g2d.drawImage(threegif.getImage(), warrior.getx()-60, 160, 50, 50, this);
            g2d.drawImage(fourgif.getImage(), theif.getx()+240, 160, 50, 50, this);
            g2d.drawImage(chooseyourcharacter.getImage(), 0, 35, 500, 71, this);
            g2d.drawImage(InstructionCharacterSelect.getImage(), 500, 0, 500, 145, this);
            g2d.drawImage(new ImageIcon(warrior.getPic()).getImage(), warrior.getx(), warrior.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(wizard.getPic()).getImage(), wizard.getx()-340, wizard.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(theif.getPic()).getImage(), theif.getx()+290, theif.gety()-100, 200, 200, this);
            g2d.drawImage(option2gif.getImage(), 0, 350, 450, 100, this);
        }
        //selection gif
        //System.out.println(player.getx());
        //System.out.println(player.gety());
        //brute
        if(key==49 && selectiongif==true){
        if(option1==false){
            bruteselected=true;
            wizardselected=false;
            warriorselected=false;
            theifselected=false;
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(new ImageIcon(brute.getPic()).getImage(), brute.getx()-340, 25, 450, 400, this);
            g2d.drawImage(new ImageIcon(brutestats.getPic()).getImage(), brutestats.getx()+110, 0, 450, 300, this);
            g2d.drawImage(btogoback.getImage(), brutestats.getx()+185, 350, 300, 75, this);
            g2d.drawImage(Ctoselect.getImage(), brutestats.getx()+140, 275, 400, 100, this);
        }
        else{
            bruteselected=true;
            wizardselected=false;
            warriorselected=false;
            theifselected=false;
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(new ImageIcon(brute.getPic()).getImage(), brute.getx()-340, 25, 450, 400, this);
            g2d.drawImage(new ImageIcon(brutestats.getPic()).getImage(), brutestats.getx()+110, 0, 450, 300, this);
            g2d.drawImage(btogoback.getImage(), brutestats.getx()+185, 350, 300, 75, this);
            g2d.drawImage(Ctoselect.getImage(), brutestats.getx()+140, 275, 400, 100, this);
        }
        }
        //wizard
        if(key==50 && selectiongif==true){
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(), 422, select1.gety()-100, 170, 200, this);
            bruteselected=false;
            wizardselected=true;
            warriorselected=false;
            theifselected=false;
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(new ImageIcon(wizard.getPic()).getImage(), wizard.getx()-340, 25, 450, 400, this);
            g2d.drawImage(new ImageIcon(wizardstats.getPic()).getImage(), wizardstats.getx()+110, 0, 450, 300, this);
            g2d.drawImage(btogoback.getImage(), brutestats.getx()+185, 350, 300, 75, this);
            g2d.drawImage(Ctoselect.getImage(), brutestats.getx()+140, 275, 400, 100, this);
        }
        //warrior
        if(key==51 && selectiongif==true){
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(), 722, select1.gety()-100, 170, 200, this);
            bruteselected=false;
            wizardselected=false;
            warriorselected=true;
            theifselected=false;
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(new ImageIcon(warrior.getPic()).getImage(), warrior.getx()-340, 25, 450, 400, this);
            g2d.drawImage(new ImageIcon(warriorstats.getPic()).getImage(), warriorstats.getx()+110, 0, 450, 300, this);
            g2d.drawImage(btogoback.getImage(), brutestats.getx()+185, 350, 300, 75, this);
            g2d.drawImage(Ctoselect.getImage(), brutestats.getx()+140, 275, 400, 100, this);
        }
        //theif
        if(key==52 && selectiongif==true){
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(), 722, select1.gety()-100, 170, 200, this);
            bruteselected=false;
            wizardselected=false;
            warriorselected=false;
            theifselected=true;
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(new ImageIcon(theif.getPic()).getImage(), theif.getx()-340, 25, 450, 400, this);
            g2d.drawImage(new ImageIcon(theifstats.getPic()).getImage(), theifstats.getx()+110, 0, 450, 300, this);
            g2d.drawImage(btogoback.getImage(), brutestats.getx()+185, 350, 300, 75, this);
            g2d.drawImage(Ctoselect.getImage(), brutestats.getx()+140, 275, 400, 100, this);
        }
        
        //selection confirm
        if (key==83){
            confirmselection=true;
            
        }
        if(key==67 && bruteselected==true){
            
         if(option1==true){
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(onegif.getImage(), brute.getx()-400, 160, 35, 50, this);
            g2d.drawImage(twogif.getImage(), wizard.getx()-60, 160, 50, 50, this);
            g2d.drawImage(threegif.getImage(), warrior.getx()+240, 160, 50, 50, this);
            g2d.drawImage(chooseyourcharacter.getImage(), 0, 35, 500, 71, this);
            g2d.drawImage(InstructionCharacterSelect.getImage(), 500, 0, 500, 145, this);
            g2d.drawImage(new ImageIcon(brute.getPic()).getImage(), brute.getx()-340, brute.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(warrior.getPic()).getImage(), warrior.getx()+290, warrior.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(wizard.getPic()).getImage(), wizard.getx(), wizard.gety()-100, 200, 200, this);
            g2d.drawImage(option1gif.getImage(), 500, 350, 450, 100, this);
                g2d.drawImage(bruteselectedtxt.getImage(), 0, 350, 400, 100, this);
                g2d.drawImage(new ImageIcon(select1.getPic()).getImage(), brute.getx()-360, brute.gety()-120, 250, 250, this);
           
        }
        if(option2==true){
            g2d.drawImage(bruteselectedtxt.getImage(), 0, 350, 400, 100, this);
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(), brute.getx()-360, brute.gety()-120, 250, 250, this);

        }
    }
        if(bruteselected==true && confirmselection==true && playerUpdated==false){
            characternamepng = new String("brute.png");
            characternamepng2 = new String("brute2.png");
            characterpm1 = new String("brutepm1.png");
            characterpm2 = new String("brutepm2.png");
            charactersound = new String("brutepm.wav");
            lives = 6;
            reloadDelay = 240;
            player = new Pictures(characternamepng, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
            playerUpdated= true;
        }
      

        if(key==67 && wizardselected==true){
            
            if(option1==true){
                g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  wizard.getx()-360, wizard.gety()-120, 250, 250, this);
                g2d.drawImage(wizardselectedtxt.getImage(), 500, 350, 400, 100, this);
           
        }
        if(option2==true){
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  wizard.getx()-25, wizard.gety()-120, 250, 250, this);
            g2d.drawImage(wizardselectedtxt.getImage(), 0, 350, 400, 100, this);

        }
    }
         if(wizardselected==true && confirmselection==true && playerUpdated==false){
            characternamepng = new String("wizard.png");
            characternamepng2 = new String("wizard2.png");
            characterpm1 = new String("wizardpm1.png");
            characterpm2 = new String("wizardpm2.png");
            charactersound = new String("wizardpm.wav");
            lives = 4;
            reloadDelay=210;
            player = new Pictures(characternamepng, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
            playerUpdated= true;
            
        }
        if(key==67 && warriorselected==true){
            
             if(option1==true){
                g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  warrior.getx()-25, warrior.gety()-120, 275, 275, this);
                g2d.drawImage(warriorselectedtxt.getImage(), 500, 350,  this);
           
        }
        if(option2==true){
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  warrior.getx()+250, warrior.gety()-120, 275, 275, this);
            g2d.drawImage(warriorselectedtxt.getImage(), 0, 350, this);

        }
    }
        if(warriorselected==true && confirmselection==true && playerUpdated==false){
            characternamepng = new String("warrior.png");
            characternamepng2 = new String("warrior2.png");
            characterpm1 = new String("warriorpm1.png");
            characterpm2 = new String("warriorpm2.png");
            charactersound = new String("warriorpm.wav");
            lives = 4;
            reloadDelay= 180;
            player = new Pictures(characternamepng, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
            playerUpdated= true;
            
        }
        if(key==67 && theifselected==true){
           
            if(option1==true){
            
            g2d.drawImage(theifselectedtxt.getImage(), 500, 350, 400, 100, this);
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  theif.getx()+250, theif.gety()-120, 275, 275, this);
        }
        if(option2==true){
            g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(twogif.getImage(), wizard.getx()-400, 160, 50, 50, this);
            g2d.drawImage(threegif.getImage(), warrior.getx()-60, 160, 50, 50, this);
            g2d.drawImage(fourgif.getImage(), theif.getx()+240, 160, 50, 50, this);
            g2d.drawImage(chooseyourcharacter.getImage(), 0, 35, 500, 71, this);
            g2d.drawImage(InstructionCharacterSelect.getImage(), 500, 0, 500, 145, this);
            g2d.drawImage(new ImageIcon(warrior.getPic()).getImage(), warrior.getx(), warrior.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(wizard.getPic()).getImage(), wizard.getx()-340, wizard.gety()-100, 200, 200, this);
            g2d.drawImage(new ImageIcon(theif.getPic()).getImage(), theif.getx()+290, theif.gety()-100, 200, 200, this);
            g2d.drawImage(option2gif.getImage(), 0, 350, 450, 100, this);
            g2d.drawImage(theifselectedtxt.getImage(), 500, 350, 400, 100, this);
            g2d.drawImage(new ImageIcon(select1.getPic()).getImage(),  theif.getx()+250, theif.gety()-120, 275, 275, this);

        }
    }
       if(theifselected==true && confirmselection==true && playerUpdated==false){
           characternamepng = new String("theif.png");
           characternamepng2 = new String("theif2.png");
           characterpm1 = new String("theifpm1.gif");
           characterpm2 = new String("theifpm1.gif");
           charactersound = new String("theifpm.wav");
           lives = 4;
           reloadDelay=120;
           player = new Pictures(characternamepng, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
           playerUpdated= true;
           
       }

        if(InsturctionScreen){
            
g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(guidebut.getImage(), 100, 0, 750, 400, this);
            g2d.drawImage(starttext.getImage(), 200, 375, 600, 95, this);
        }
        if (!start) {
            if(firerates){
            g2d.drawImage(firerate.getImage(), 350, 150, 300, 50, this);
        }
    }

        if (!playerMissiles.isEmpty()) {
            drawPlayerMissiles(g2d);
        }
        if (!playerMissiles2.isEmpty()) {
            drawPlayerMissiles2(g2d);
        }
        if (!wizardsuper.isEmpty()) {
            drawWizardsuper(g2d);
        }
        if (!wizardsuper2.isEmpty()) {
            drawWizardsuper2(g2d);
        }

        if (!Ghosts.isEmpty() && !start) {
            if ((System.currentTimeMillis() - currentTime) % 75 == 0) {
                currentTime = System.currentTimeMillis();
            }

            g2d.setFont(new Font("Roboto", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("       " + lives, 350, 110);
            g2d.drawImage(LIVES.getImage(), 320, 68, 125, 50, this);
            g2d.drawImage(Points.getImage(), 535, 68, 150, 50, this);
            g2d.drawString("       " + points, 590, 110);
        }
        if (!Boss.isEmpty() && !start) {
            if ((System.currentTimeMillis() - currentTime) % 75 == 0) {
                currentTime = System.currentTimeMillis();
            }

            g2d.setFont(new Font("Roboto", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("       " + lives, 350, 110);
            g2d.drawImage(LIVES.getImage(), 320, 68, 125, 50, this);
            g2d.drawImage(Points.getImage(), 535, 68, 150, 50, this);
            g2d.drawString("       " + points, 590, 110);
        }
        if ( !Ghosts2.isEmpty() && !start) {
            if ((System.currentTimeMillis() - currentTime) % 75 == 0) {
                currentTime = System.currentTimeMillis();
            }

            ;
            g2d.setFont(new Font("Roboto", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("       " + lives, 350, 110);
            g2d.drawImage(LIVES.getImage(), 320, 68, 125, 50, this);
            g2d.drawImage(Points.getImage(), 535, 68, 150, 50, this);
            g2d.drawString("       " + points, 590, 110);
        }
        if (points + liveslost  >= 0 && points + liveslost  < 14 && !start) {
            for (LotsaGhosts2 inv : Ghosts2) {
                for (LotsaGhosts env : Ghosts) {
            g2d.drawImage(wave1.getImage(), 430, 120, 150, 50, this);
                   
        }
    }
}
if ( points + liveslost >= 14 && points + liveslost < 28) {
    g2d.drawImage(wave2.getImage(), 430, 120, 150, 50, this);
    for (LotsaGhosts2 inv : Ghosts2) {
        inv.setX(inv.getX() - 1 ); 
    }
    for (LotsaGhosts env : Ghosts) {
        env.setX(env.getX() + 1); 
    }
}

if (points + liveslost  >= 28 && points + liveslost  < 42) {
    g2d.drawImage(wave3.getImage(), 430, 120, 150, 50, this);
    for (LotsaGhosts2 inv : Ghosts2) {
        inv.setX(inv.getX() - 2 ); 
    }
    for (LotsaGhosts env : Ghosts) {
        env.setX(env.getX() + 2); 
    }
}
if (points + liveslost  >= 42 ) {
    drawBoss(g2d);
    
}

if (points + liveslost  >= 43 ) {
    
    
g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
    g2d.drawImage(youwin.getImage(), 0, 150, 1000, 190, this);
    g2d.drawImage(Restart.getImage(), 360, 400, 300, 50, this);
    g2d.setColor(Color.WHITE);
    g2d.drawString("       " + lives, 280, 135);
        g2d.drawImage(LIVES.getImage(), 225, 0, 375, 150, this);
    if (!musicPlayed) { 
        if (!stopTime) {
            endTime = System.currentTimeMillis();
            stopTime = true;
            if (currentTime - music0 > musicdelay) {
                if (playMusic) {
                    p.playmusic("level-win-6416.wav");
                    currentTime = currentTime; 
                }
                musicPlayed = true; 
            }
            elapsedTime = (endTime - startTime) / 1000.0;
        }
    }
}
        if (lives <= 0) {
            for (LotsaGhosts2 inv : Ghosts2) {
                inv.setX(inv.getX() + 1000 ); 
            }
            for (LotsaGhosts env : Ghosts) {
                env.setX(env.getX() - 1000); 
            }
            
g2d.drawImage(backgroundsmall.getImage(), 0, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
g2d.drawImage(backgroundsmall.getImage(), 490, 0, backgroundsmall.getIconWidth(), backgroundsmall.getIconHeight(), this);
            g2d.drawImage(youloose.getImage(), 0, 100, 1000, 250, this);
            g2d.drawImage(Restart.getImage(), 360, 400, 300, 50, this);
            if (!musicPlayed) { 
            if (!stopTime) {
                endTime = System.currentTimeMillis();
                stopTime = true;
                if (currentTime - music0 > musicdelay) {
                    if (playMusic) {
                        p.playmusic("wah-wah-sad-trombone-6347.wav");
                        currentTime = currentTime; 
                    }
                    musicPlayed = true; 
                }
                elapsedTime = (endTime - startTime) / 1000.0;
            }
        }
        }
    

        twoDgraph.drawImage(back, 0, 0, null);

        for (LotsaGhosts e : Ghosts) {
            for (LotsaGhosts2 p : Ghosts2) {
                if (e.getY() + e.getH() == player.gety() && p.getY() + p.getH() == player.gety()) {
                    g2d.clearRect(0, 0, getWidth(), getHeight());
                    g2d.setFont(new Font("Times New Roman", Font.BOLD, 50));
                    g2d.drawString("YOU LOSE :(", 500, 200);
                    if (playMusic) {
                        // p.playmusic("wah-wah-sad-trombone-6347.wav");
                        // p.playmusic("stop");
                    }
                    if (!stopTime) {
                        endTime = System.currentTimeMillis();
                        stopTime = true;
                    }
                    elapsedTime = (endTime - startTime) / 1000.0;
                    g2d.drawString("It took you " + elapsedTime + " seconds to lose!", 300, 500);
                }
            }
        }
    }

    public void move() {
        player.move();

        for (PlayerMissle pm : playerMissiles) {
            pm.move();
        }

        for (PlayerMissle pm2 : playerMissiles2) {
            pm2.move();
        }
        for (WizardSuper ws : wizardsuper) {
            ws.move();
        }
        for (WizardSuper ws2 : wizardsuper2) {
            ws2.move();
        }

        
    }

    private void drawPlayerMissiles(Graphics g2d) {
        for (PlayerMissle pm : playerMissiles) {
            g2d.drawImage(pm.getImg().getImage(), pm.getX(), pm.getY(), 60, 60, this);
        }
    }
    private void drawBoss(Graphics g2d) {
        for (Boss b : Boss) {
            g2d.drawImage(b.getImg().getImage(), b.getX(), b.getY(), 500, 500, this);
            //.move();
        }
    }
    private void drawGhosts(Graphics g2d) {
        for (LotsaGhosts g : Ghosts) {
            g2d.drawImage(g.getImg().getImage(), g.getX(), g.getY(), 175, 100, this);
            g.move();
        }
    }

    private void drawGhosts2(Graphics g2d) {
        for (LotsaGhosts2 p : Ghosts2) {
            g2d.drawImage(p.getImg().getImage(), p.getX(), p.getY(), 175, 100, this);
            p.move2();
        }
    }

    private void drawPlayerMissiles2(Graphics g2d) {
        for (PlayerMissle pm2 : playerMissiles2) {
            g2d.drawImage(pm2.getImg().getImage(), pm2.getX(), pm2.getY(), 60, 60, this);
        }
    }
    private void drawWizardsuper(Graphics g2d) {
        for (WizardSuper ws : wizardsuper) {
            g2d.drawImage(ws.getImg().getImage(), ws.getX(), ws.getY(), 60, 60, this);
        }
    }
    private void drawWizardsuper2(Graphics g2d) {
        for (WizardSuper ws2 : wizardsuper2) {
            g2d.drawImage(ws2.getImg().getImage(), ws2.getX(), ws2.getY(), 60, 60, this);
        }
    }

    private void collision1() {
        for (int i = 0; i < playerMissiles.size(); i++) {
            for (int j = 0; j < Ghosts.size(); j++) {
                PlayerMissle pm = playerMissiles.get(i);
                pm.move();
               // System.out.println(pm.getX());
                if (pm.getX() <= -70 || pm.getX() >= 900) {
                    playerMissiles.remove(i);
                }
                if (playerMissiles.get(i).GhostsCollision(Ghosts.get(j))) {
                    Ghosts.remove(Ghosts.get(j));
                    points++;
                    wizardpointcharge++;
                    if (playMusic) {
                        
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && bruteselected==true) {
                        playSoundWithVolume("brutepmcol.wav", -20.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && warriorselected==true) {
                        playSoundWithVolume("warriorpmcol.wav", -10.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    playerMissiles.remove(playerMissiles.get(i));
                    for (LotsaGhosts inv : Ghosts) {
                        
                    }
                }
            }
        }
    }
    private void collisionboss() {
        for (int i = 0; i < playerMissiles.size(); i++) {
            for (int j = 0; j < Boss.size(); j++) {
                PlayerMissle pm = playerMissiles.get(i);
                pm.move();
               
                if (pm.getX() <= -70 || pm.getX() >= 900) {
                    playerMissiles.remove(i);
                }
                if (playerMissiles.get(i).BossCollision(Boss.get(j))) {
                    System.out.println("BOSSHITHHITHITHITHI");
                    Boss.remove(Boss.get(j));
                    points++;
                    wizardpointcharge++;
                   
                    
                }
                if (pm.getX()==250) {
                    System.out.println(pm.getX());
                    Boss.remove(Boss.get(j));
                    points++;
                    wizardpointcharge++;
                   
                   
                }
            }
        }
    }
    private void collisionwizardsuper1() {
        for (int i = 0; i < wizardsuper.size(); i++) {
            for (int j = 0; j < Ghosts.size(); j++) {
                WizardSuper ws = wizardsuper.get(i);
                ws.move();
               // System.out.println(pm.getX());
                if (ws.getX() <= -70 || ws.getX() >= 900) {
                    wizardsuper.remove(i);
                }
                if (wizardsuper.get(i).GhostsCollision(Ghosts.get(j))) {
                    Ghosts.remove(Ghosts.get(j));
                    points++;
                    wizardpointcharge++;
                   
                    for (LotsaGhosts inv : Ghosts) {
                        
                    }
                }
            }
        }
    }
    private void collisionwizardsuper2() {
        if(!wizardsuper2.isEmpty() && !Ghosts2.isEmpty())
        for (int i = 0; i < wizardsuper2.size(); i++) {
            for (int j = 0; j < Ghosts2.size(); j++) {
                WizardSuper ws2 = wizardsuper2.get(i);
                ws2.move();
                if (ws2.getX() <= 0 || ws2.getX() >= 900) {
                    wizardsuper2.remove(i);
                }
                if (wizardsuper2.get(i).GhostsCollision(Ghosts2.get(j))) {
                    Ghosts2.remove(Ghosts2.get(j));
                    points ++;
                    wizardpointcharge++;
                    if (playMusic) {
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && bruteselected==true) {
                        playSoundWithVolume("brutepmcol.wav", -20.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && warriorselected==true) {
                        playSoundWithVolume("warriorpmcol.wav", -10.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && theifselected==true) {
                        playSoundWithVolume("theifpmcol.wav", -10.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    
                    for (LotsaGhosts2 inv : Ghosts2) {
                        
                    }
                }
            }
        }
    }
    private void collision2() {
        if(!playerMissiles2.isEmpty() && !Ghosts2.isEmpty())
        for (int i = 0; i < playerMissiles2.size(); i++) {
            for (int j = 0; j < Ghosts2.size(); j++) {
                PlayerMissle pm2 = playerMissiles2.get(i);
                pm2.move();
                if (pm2.getX() <= 0 || pm2.getX() >= 900) {
                    playerMissiles2.remove(i);
                }
                if (playerMissiles2.get(i).GhostsCollision(Ghosts2.get(j))) {
                    Ghosts2.remove(Ghosts2.get(j));
                    points ++;
                    wizardpointcharge++;
                    if (playMusic) {
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && bruteselected==true) {
                        playSoundWithVolume("brutepmcol.wav", -20.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && warriorselected==true) {
                        playSoundWithVolume("warriorpmcol.wav", -10.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    if (playMusic && theifselected==true) {
                        playSoundWithVolume("theifpmcol.wav", -10.0f);
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    playerMissiles2.remove(playerMissiles2.get(i));
                    for (LotsaGhosts2 inv : Ghosts2) {
                        
                    }
                }
            }
        }
    }
  
    private void collisionPlayer() {
        for (int i = 0; i < Ghosts.size(); i++) {
            if (Ghosts.get(i).playerCollision(player)) {
                lives--;
                liveslost++;
                if (playMusic) {
                    p.playmusic("ow.wav");
                    p.playmusic("stop");
                }
                System.out.println("collision");
                System.out.println(player.gety());
                Ghosts.remove(Ghosts.get(i));
            }
            
        }
    }
    
    private void collisionPlayer2() {
        for (int i = 0; i < Ghosts2.size(); i++) {
            if (Ghosts2.get(i).playerCollision(player)) {
                lives--;
                liveslost++;
                System.out.println("collision");
                System.out.println(player.gety());
                Ghosts2.remove(Ghosts2.get(i));
            }
            
        }
    }
    

    private boolean checkWall() {
        for (LotsaGhosts inv : Ghosts) {
            if (inv.getX() < 0 || inv.getX() + inv.getW() >= 1350) {
                return true;
            }
        }
        return false;
    }
    
        
        
    private void moveGhosts() {
        if (checkWall() == true) {
            for (LotsaGhosts inv : Ghosts) {
                inv.reverseHorz();
                inv.setY(inv.getY() + 5);
            }
        }

        for (LotsaGhosts inv : Ghosts) {
            inv.Hmove();
        }

        for (LotsaGhosts2 inv : Ghosts2) {
            inv.Hmove2();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        System.out.println(key);

        if (key == 82) {  // Reset the game state
            Ghosts.clear(); 
            Ghosts2.clear(); 
            points = 0;
            lives = 4;
            liveslost = 0;
            start = true;
            gameStarted = true;
            manual = true;
            wizardselected= false;
            warriorselected =false;
            bruteselected=false;
            theifselected=false;
            player = new Pictures("", 422, 250, 0, 0, player.getW(),player.getH());
            playerMissiles.clear();
            playerMissiles2.clear();
            currentTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            endTime = System.currentTimeMillis();
            elapsedTime = 0;
            stopTime = false;
            musicPlayed = false;
            Boss = setBoss();
            Ghosts = setGhosts();
            Ghosts2 = setGhosts2();
 
        }
        if (key == 78) {
           InsturctionScreen=true;
         }
         if(key==77){
            CharacterSelection=true;
            
         }
        if (key == 83) {
            selectiongif=false;
            start = false;
            gameStarted = false;
        }
       
        if (key == 70 && !start) { 
        if (reloadDelay == 0) {
            reloadDelay = 150;
            firerates=false;
        } else {
            reloadDelay = 0;
            firerates=true;
        }

    }

        if (key == 32) {
            long currentTime = System.currentTimeMillis();
            if (playMusic) {
                
                
            }
            
            if (currentTime - lastFiredTime > reloadDelay && !start ) { 
                if (player.getPic().equals(characternamepng)) {
                    playerMissiles.add(new PlayerMissle(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon(characterpm2)));
                    p.playmusic(charactersound);
                } else {
                    playerMissiles2.add(new PlayerMissle(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon(characterpm1)));
                    p.playmusic(charactersound);
                }
                lastFiredTime = currentTime; 
            }
        }

        if (key == 65) {
            spacebar = true;
        }
        if(key == 38){
            if(wizardselected==true && wizardpointcharge >=10 ){
                System.out.println("wizardcharge");
                wizardpointcharge=0;
                
                if (currentTime - lastFiredTime > reloadDelay && !start ) { 
                    if (player.getPic().equals(characternamepng)) {
                        wizardsuper.add(new WizardSuper(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon("wizardsuper2.gif")));
                        p.playmusic(charactersound);
                    } else {
                        wizardsuper2.add(new WizardSuper(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon("wizardsuper2.gif")));
                        p.playmusic(charactersound);
                    }
                    lastFiredTime = currentTime; 
                }
            
            }
            
        }
        
        if (key == 39 && start == false) {
            player.setDx(6);
          move();
          player = new Pictures(characternamepng2, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
            pd = false;
            option1=true;
            option2=false;
        }
        if (key == 39 ) {
            option1=true;
            option2=false;
        }
        ///change dx by using character name
        if (key == 37 && start == false) {
            player.setDx(-6);
            move();
            player = new Pictures(characternamepng, player.getx(), player.gety(), player.getdx(), player.getdy(), player.getW(), player.getH());
            pd = true;
            option1=false;
            option2=true;
        }
        if (key == 37 ) {
            option1=false;
            option2=true;
        }
    }

    public void keyReleased(KeyEvent e) {

        key = e.getKeyCode();
        if (key == 39) {
            player.setDx(0);
            move();
        }

        if (key == 37) {
            player.setDx(0);
            move();
        }

        if (key == 40) {
        }
        if (key == 83) {
        }
        if (key == 32) {
        }
        if (key == 65) {
        }
        if (key == 69) {
        }
        if (key == 70 ) {
            

        }
        if (key == 80 ) {
           points=43;
        }
        
        
        
        
         
            if (key == 82) { 
                
            }
        }
    }
