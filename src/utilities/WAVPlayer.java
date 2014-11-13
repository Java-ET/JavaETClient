package utilities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class to create a WAV player.
 * The loopPlay() method creates a seperate thread and plays in a loop until it is
 * interrupted by the stopLoop() method
 * The play() method plays the WAV file once in the same thread.
 * @author JavaET
 *
 */
class MyThread extends Thread
{
	public MyThread()
	{
		setDaemon(true);
	}
}

public class WAVPlayer
{
   private String fileName;
   
   private Thread myThread;
   
   public WAVPlayer(String soundFile)
   {
      fileName = soundFile;
   }
   
   public void loopPlay()
   {
      myThread = new MyThread(){
         public void run()
         {
            while(!this.isInterrupted())
            {
            	play();
            }
         }
      };
      myThread.start();
   }
   
   public void stopLoop()
   {
      myThread.interrupt();
   }
   
   public void playOnce()
   {
	   myThread = new MyThread(){
		   public void run()
		   {
			   while(!this.isInterrupted())
			   {
				   play();
				   this.interrupt();
			   }
		   }
	   };
	   myThread.start();
   }
   
   public boolean play()
   {
      File soundFile = new File(fileName);
      
      if(!soundFile.exists())
      {
         System.err.println("Error. File not found.");
         return false;
      }
      
      AudioInputStream aiS = null;
      
      try
      {
         aiS = AudioSystem.getAudioInputStream(soundFile);
      }
      catch (UnsupportedAudioFileException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      
      AudioFormat audioFormat = aiS.getFormat();
      
      SourceDataLine audioLine = null;
      
      DataLine.Info information = new DataLine.Info(SourceDataLine.class, audioFormat);
      
      try {
         audioLine = (SourceDataLine) AudioSystem.getLine(information);
         audioLine.open(audioFormat);
      } catch (LineUnavailableException e)
      {
         e.printStackTrace();
      } catch (Exception e)
      {
         e.printStackTrace();
      }
      
      audioLine.start();
      int nBytesRead = 0;
      byte[] data = new byte[524288]; // Byte buffer - 128 Kb
      
      try {
         while (nBytesRead != -1)
         {
            nBytesRead = aiS.read(data, 0, data.length);
            if(nBytesRead >= 0)
            {
               audioLine.write(data, 0, nBytesRead);
            }
         }
      } catch (IOException e)
      {
         e.printStackTrace();
      } finally {
         audioLine.drain();
         audioLine.close();
      }
      return true;
   }
}
