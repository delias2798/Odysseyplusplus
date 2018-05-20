package Player;

import javazoom.jl.player.Player;

import org.apache.commons.io.IOUtils;


import java.io.*;

public class music_player {//librerias jlayer, jgroups
    InputStream bis;
    public static void main(String[] args) {
        String song="/home/toshiba/Música/grito.mp3";
        music_player player = new music_player();
        player.play(player.build_song(song));



    }
    public music_player(){}

    public byte[] build_song(String song){
        try{
            File file = new File(song);
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            byte[] byte_song = IOUtils.toByteArray(bis);
            return byte_song;
            //separate_bytearray chunk = new separate_bytearray(byte_song);
            //chunk.chunk(byte_song);
            //play(byte_song);
            /*Player player = new Player(bis);
            player.play();*/

        }catch (Exception e){return null;}
    }

    public void play(byte[] song) {
        try {
            bis=new ByteArrayInputStream(song);
            Player player = new Player(bis);
            player.play();
        }catch (Exception e){}
    }

    }


