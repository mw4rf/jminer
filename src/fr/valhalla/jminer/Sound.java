package fr.valhalla.jminer;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Sound {
	
	public static final String bwaaah = "bwaaah.wav";
	
	public static synchronized void mineExploded() {
		new Thread(new Runnable() {
			public void run() {
				try {
					// Init sound tools
					InputStream is = Main.class.getResourceAsStream(Sound.bwaaah);
					InputStream bis = new BufferedInputStream(is); // buffer the stream to avoid the "mark/reset not supported" IOException
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(bis);
					AudioFormat audioFormat = inputStream.getFormat();
					DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
					SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
					// Play sound
					byte tempBuffer[] = new byte[10000];
					sourceDataLine.open(audioFormat);
					sourceDataLine.start();
					int cnt;
					while ((cnt = inputStream.read(tempBuffer, 0,
							tempBuffer.length)) != -1) {
						if (cnt > 0) {
							sourceDataLine.write(tempBuffer, 0, cnt);
						}
					}
					sourceDataLine.drain();
					sourceDataLine.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					//e.printStackTrace();
				}
			}
		}).start();
	}

}
