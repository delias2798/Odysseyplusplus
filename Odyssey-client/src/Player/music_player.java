package Player;

import javazoom.jl.player.Player;

import org.apache.commons.io.IOUtils;


import java.io.*;

class music_player {//librerias jlayer, jgroups
    InputStream bis;
    public static void main(String[] args) {
        String song="/home/toshiba/Música/test2.mp3";
        music_player player = new music_player();
        player.build_song(song);


    }

    public void build_song(String song){
        try{
            File file = new File(song);
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            byte[] byte_song = IOUtils.toByteArray(bis);
            separate_bytearray chunk = new separate_bytearray(byte_song);
            chunk.chunk(byte_song);
            //play(byte_song);
            /*Player player = new Player(bis);
            player.play();*/

        }catch (Exception e){}
    }

    public void play(byte[] song) {
        try {
            bis=new ByteArrayInputStream(song);
            Player player = new Player(bis);
            player.play();
        }catch (Exception e){}
    }

    }


