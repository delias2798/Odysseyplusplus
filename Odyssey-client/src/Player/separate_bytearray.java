package Player;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class separate_bytearray {
    int blockCount;
    int block_size=24056;
    byte[] range = null;
    InputStream stream;
    music_player play = new music_player();

    public separate_bytearray(byte[] song){
        this.blockCount=(song.length+block_size-1)/block_size;
    }

    public void chunk(byte[] song){
        for(int i=1;i<blockCount;i++){
            int idx = (i-1)*block_size;
            range=Arrays.copyOfRange(song,idx,idx+block_size);
            play.play(range);

            System.out.println("Chunk " + i + ": "+Arrays.toString(range));
        }
        // Last chunk
        int end = -1;
        if (song.length % block_size == 0) {
            end = song.length;
        } else {
            end = song.length % block_size + block_size * (blockCount - 1);
        }

        range = Arrays.copyOfRange(song, (blockCount - 1) * block_size, end);
        stream= new ByteArrayInputStream(range);
        play.play(range);
        System.out.println("Chunk " + blockCount + ": "+Arrays.toString(range));
    }
}
