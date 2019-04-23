
package Game.Sound;

import java.io.InputStream;
import sun.audio.AudioPlayer;



public class musicPlayer
{
    
    AudioPlayer MGP = AudioPlayer.player;
    InputStream backGround;
    
    public void backGroundMusic(boolean play)
    {
        if(play)
        {
            backGround = getClass().getResourceAsStream("rocket.wav");
            MGP.start(backGround);
        }
        if(!play)
            MGP.stop(backGround);
    }
}
