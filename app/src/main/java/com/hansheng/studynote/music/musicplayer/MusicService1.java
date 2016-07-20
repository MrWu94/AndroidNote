package com.hansheng.studynote.music.musicplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hansheng on 2016/7/20.
 */
public class MusicService1 extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    //media player
    private MediaPlayer player;
    //song list
    private ArrayList<Song> songs;
    //current position
    private int songPosn;
    //binder
    private final IBinder musicBind = new MusicBinder();
    //title of current song
    private String songTitle="";
    //notification id
    private static final int NOTIFY_ID=1;
    //shuffle flag and random
    private boolean shuffle=false;
    private Random rand;

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    @Override
    public void onCreate() {
        //create the service
        super.onCreate();
        //initialize position
        songPosn=0;
        //random
        rand=new Random();
        //create player
        player = new MediaPlayer();
        //initialize
        initMusicPlayer();
    }

    private void initMusicPlayer() {
        //set player properties
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //set listeners
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    //set the song
    public void setSong(int songIndex){
        songPosn=songIndex;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(player.getCurrentPosition()>0){
            mp.reset();
            playNext();
        }

    }
    //pass song list
    public void setList(ArrayList<Song> theSongs){
        songs=theSongs;
    }

    public  void playSong() {
        player.reset();
        Song playSong=songs.get(songPosn);
        songTitle=playSong.getTitle();

        long currrSong=playSong.getID();

        Uri trackUri= ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,currrSong);

        //set the data source
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        player.prepareAsync();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.v("MUSIC PLAYER", "Playback Error");
        mp.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Intent notIntent=new Intent(this,MusicActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder=new Notification.Builder(this);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.play)
                .setTicker(songTitle)
                .setOngoing(true)
                .setContentTitle("Playing")
                .setContentText(songTitle);
        Notification not = builder.build();
        startForeground(NOTIFY_ID, not);

    }

   public class MusicBinder extends Binder {
        MusicService1 getService() {
            return MusicService1.this;
        }
    }

    //playback methods
    public int getPosn(){
        return player.getCurrentPosition();
    }

    public int getDur(){
        return player.getDuration();
    }

    public boolean isPng(){
        return player.isPlaying();
    }

    public void pausePlayer(){
        player.pause();
    }

    public void seek(int posn){
        player.seekTo(posn);
    }

    public void go(){
        player.start();
    }
    //skip to previous track
    public void playPrev(){
        songPosn--;
        if(songPosn<0) songPosn=songs.size()-1;
        playSong();
    }
    //skip to next
    public void playNext(){
        if(shuffle){
            int newSong = songPosn;
            while(newSong==songPosn){
                newSong=rand.nextInt(songs.size());
            }
            songPosn=newSong;
        }
        else{
            songPosn++;
            if(songPosn>=songs.size()) songPosn=0;
        }
        playSong();
    }



    //toggle shuffle
    public void setShuffle(){
        if(shuffle) shuffle=false;
        else shuffle=true;
    }

}
