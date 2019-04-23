package Game.Game;

import Game.DisplaySettings.displayGame;
import Game.GUIpage.connectingGUI;
import Game.GUIpage.playAgain;
import Game.GUIpage.startPage;
import Game.ImageLoader.imageLoader;
import Game.KeyManager.KeyManager;
import Game.Sound.musicPlayer;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Game implements Runnable 
{
    private String title;
    private int height;
    private int width;
    private boolean running = false ;
    private boolean anim = true;
    public int  playerPosX , playerPosY;
    public int bulletPosX , bulletPosY;
    public int enemyBulletX , enemyBulletY = -15 ;
    
    public boolean  playerOne = false, 
                    playerTwo = false;
    
    private boolean winner = false,
                    loser = false;
    private boolean connected = false;
    private boolean enemyBulletOnScreen = false;
    private int life = 6;
    
    private startPage start;
    public KeyManager keyMan;
    private BufferStrategy bs;
    private BufferedImage image1 , image2 , bullet , enemyBullet;
    private BufferedImage winnerImage , loserImage ;
    private BufferedImage alive , dead;
    private Graphics g;
    public displayGame display;
    public JFrame frame;
    private Thread thread;
    private Thread t1;
    private connectingGUI c;
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private musicPlayer MP;
    private playAgain playagain;
    
    
    public Game( String title, int width , int height , startPage start) 
    {
        try {
            this.title = title;
            this.height = height;
            this.width = width;
            this.start = start;
            keyMan = new KeyManager();
            MP = new musicPlayer();
            
            connect();
            
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void connect() throws Exception 
    {
         socket = new Socket("localhost", PORT);
         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         out = new PrintWriter(socket.getOutputStream(), true);
         
    }
    
     public void play() throws Exception 
    {
        String response;
        try 
        {
            response = in.readLine();
            if (response.startsWith("WELCOME")) 
            {
                System.out.println("Welcome from server");
            }
            while (true) 
            {
                response = in.readLine();
                
                if(response.startsWith("PlayerOne"))
                {
                    System.out.println("Player One has been connected");
                    playerOne = true;
                    playerTwo = false;
                }
                
                if(response.startsWith("PlayerTwo") )
                {
                    System.out.println("Player Two has been connected");
                    playerTwo = true;
                    playerOne = false;
                }
                
                
                if(enemyBulletOnScreen == false)
                {
                    if(playerOne)
                    {
                        if(response.startsWith("Player+Two"))
                        { 
                            enemyBulletX = Integer.parseInt(response.substring(10, response.length()));
                            enemyBulletY = -15;
                            enemyBulletOnScreen = true;
                        }
                    }

                   if(playerTwo)
                   {
                       
                        if(response.startsWith("Player+One"))
                        {
                            enemyBulletX = Integer.parseInt(response.substring(10, response.length()));
                            enemyBulletOnScreen = true;
                            enemyBulletY = -15;
                        }    
                    }

                 }
                
                
                if (response.equals("WINNER")) 
                {
                    winner = true;
                    MP.backGroundMusic(false);
                    //Thread.sleep(5000);
                    //display.getFrame().dispose();
                    //start.setVisible(true);
                    playagain.setLocationRelativeTo(null);
                    playagain.show();


                    break;
                } 
                
                if (response.equals("LOSER")) 
                {
                    loser = true;
                    MP.backGroundMusic(false);
                    //Thread.sleep(5000);
                    //display.getFrame().dispose();
                    //start.setVisible(true);
                    playagain.setLocationRelativeTo(null);
                    playagain.show();
                    break;
                } 
                
                else if (response.equals("All players connected")) 
                {
                    c.dispose();
                    display.setVisible(true);
                    MP.backGroundMusic(true);
                    System.out.println(response);
                }
            }
        }
        finally 
        {
            socket.close();
        }
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
    public void initializeGame()
    {
        
        display = new displayGame(title, width, height);
        playagain = new playAgain(start , display.getFrame());
        display.getFrame().addKeyListener(keyMan);
        
        
        image1 = imageLoader.loadImage("/images/plane1.png");
        image2 = imageLoader.loadImage("/images/plane2.png");
        bullet = imageLoader.loadImage("/images/bullet.png");
        enemyBullet = imageLoader.loadImage("/images/revBullet.png");
        winnerImage = imageLoader.loadImage("/images/winner.png");
        loserImage = imageLoader.loadImage("/images/loser.png");
        alive = imageLoader.loadImage("/images/life.png");
        dead = imageLoader.loadImage("/images/dead.png");
    
    }
    
   

    
    public void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        
        draw();
        
        bs.show();
        g.dispose();
    }
    
    public void draw()
    {
        if(keyMan.shotsFired)
        {
            g.drawImage(bullet, bulletPosX, bulletPosY, null);
        }
        if(anim)
            g.drawImage(image1, playerPosX, playerPosY, null);
        else
            g.drawImage(image2, playerPosX, playerPosY, null); 
        
        if(enemyBulletOnScreen)
            g.drawImage(enemyBullet, enemyBulletX , enemyBulletY , null);
        
        
        if(winner)
            g.drawImage(winnerImage, 0 , 0 , null);
        if(loser)
            g.drawImage(loserImage, 0 , 0 , null);
        
        if(!winner && !loser)
        {
            if(life == 6)
            {
                g.drawImage(alive, 0, 0, null);
                g.drawImage(alive, 40, 0, null);
                g.drawImage(alive, 80, 0, null);
            }
            else if(life == 4)
            {
                g.drawImage(alive, 0, 0, null);
                g.drawImage(alive, 40, 0, null);
                g.drawImage(dead, 80, 0, null);
            }
            else if(life == 2)
            {
                g.drawImage(alive, 0, 0, null);
                g.drawImage(dead, 40, 0, null);
                g.drawImage(dead, 80, 0, null);        
            }
        }
    }
    
    public void update()
    {
         keyMan.update();
         playerPosX = keyMan.playerPosX;
         playerPosY = keyMan.playerPosY;
         
        enemyBulletY += 25;
        if(enemyBulletY >= 550)
            enemyBulletOnScreen = false;
         
        if(enemyBulletX >= playerPosX-20 && enemyBulletX <= playerPosX+110 && 
                enemyBulletY >= playerPosY && enemyBulletY <= playerPosY+50)
        {
            life -= 1;
            display.colorEffect();
            if(life == 0)
                out.println("LOSER");
            enemyBulletOnScreen = false;
        }
        
        
         if(keyMan.shotsFired)
         {
             bulletPosY-= 25;
             
             if(bulletPosY < -10)
             {
                keyMan.shotsFired = false;
                if(playerOne)
                {
                    out.println("Player One " + bulletPosX);                   
                }
                if(playerTwo)
                {
                    out.println("Player Two " + bulletPosX);                  
                }
             }
         }
         else
         {
            bulletPosX = playerPosX+30;
            bulletPosY = playerPosY;
         }
    }
    
    
    @Override
    public void run() 
    {
        int fps = 60;
        double timePerFrame = 1000000000 / fps;
        double deltaTime = 0;
        long now;
        long lastTime = System.nanoTime();
        long animTimer = 0;
        
        initializeGame();
        c = new connectingGUI();
        t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    play();// code goes here.
                } catch (Exception ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
             }   
            });  
            t1.start();
               
        while(running)
        {
           
            now = System.nanoTime();
            deltaTime += (now - lastTime) / timePerFrame ;
            animTimer += now - lastTime;
            lastTime = now;
            
            if(deltaTime > 1)
            {
                update();
                render();
                deltaTime--;
            }
            // image animation changing
            if(animTimer > 100000000)
            {
                anim = !anim;
                animTimer = 0;
            }
        }
        stop();
    }
    
    
    public synchronized void start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        
        thread.start();
    }
    
    public synchronized void stop()
    {
        if(!running)
            return;
        
        running = false;
        
        try 
        {
            thread.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}