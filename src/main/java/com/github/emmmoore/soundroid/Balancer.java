package com.github.emmmoore.soundroid;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.lang.model.util.ElementScanner14;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This class can set and get the tonal balancer of a media player
 */

public class Balancer {
  /**
   * Stores a reference to the MediaPlayer being balanced
   */
  MediaPlayer player;
  /**
   * Stores the left and right balance (not necessarily that of the left and right speaker)
   */
  double xBalance;
  /**
   * Stores the angle between the balance's right and the right speaker
   */
  double theta;

  /**
   * Creates a Balancer object for a SoundroidTrack, and initializes the current balance
   * @param st
   */
  public Balancer(SoundroidTrack st) {
    player = st.getMediaPlayer();
    balanceX = player.getBalance();
  }

  /**
   * Sets a standard stereo balance, where 0 
   * @param xBal
   */
  public void setXBalance(double xBal) {
    if (xBal > 1) {
      xBalance = 1;
    } else if (xBal < -1) {
      xBalance = -1;
    } else {
      xBalance = xBal;
    }

    player.setBalance(xBalance);
  }

  /**
   * Rotates the current balance 
   * @param th
   */
  public void rotateBalance(double th) {
    xBalance *= Math.cos(th);
    theta = th;
  }
  
   
  
  public static float[] compass(){ //method that will return azimuth (angular distance from north) direction in both radians and degrees (return an array list)
    private Sensor mAccelerometer;//instatiating objects available from java android sensor class
    private Sensor mMagnetometer;
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;
    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private float mCurrentDegree = 0f;
    private float[] finalDirection = new float [2];
    
  SensorManager.getRotationMatrix(mR, null, mLastAccelerometer, mLastMagnetometer);//getting rotation from sensor manager class
            SensorManager.getOrientation(mR, mOrientation);
            float azimuthInRadians = mOrientation[0];
            float azimuthInDegrees = (float)(Math.toDegrees(azimuthInRadians)+360)%360;
            finalDirection[0] = azimuthInRadians;//adding directions to "final direction" array
            finalDirection[1] = azimuthInDegrees;
    
    return finalDirection;
  }//close compass method
}//close balancer class 
