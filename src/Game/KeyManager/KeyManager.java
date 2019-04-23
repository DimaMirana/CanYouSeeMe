package Game.KeyManager;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyManager implements KeyListener
{
    
    private boolean key[];
    public boolean up , down , right , left , shoot;
    public boolean shotsFired = false;
    public int playerPosX = 325;
    public int playerPosY = 200;
    public int bulletPosX = playerPosX;
    public int bulletPosY = playerPosY;
    
    public KeyManager()
    {
        key = new boolean[256];
    }
    
    public void update()
    {
        up = key[KeyEvent.VK_UP];
        down = key[KeyEvent.VK_DOWN];
        right = key[KeyEvent.VK_RIGHT];
        left = key[KeyEvent.VK_LEFT];
        shoot = key[KeyEvent.VK_SPACE];
        
        if(up && playerPosY > 0)
            playerPosY -= 15;
        else if(down && playerPosY < 600-224)
            playerPosY += 15;
        else if(right && playerPosX < 750-100)
            playerPosX += 15;
        else if(left && playerPosX > 0)
            playerPosX -= 15;
        
        if(shoot && !shotsFired)
            shotsFired = true;
        
    }
    

    @Override
    public void keyPressed(KeyEvent e) 
    {
        key[e.getKeyCode()] = true;
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        key[e.getKeyCode()] = false;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
