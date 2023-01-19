package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    public static Texture dropImage;
    public static Texture bucketImage;
    public static Sound dropSound;
    public static Music rainMusic;

    //Sons
    public static Sound explosionSound;
    public static Music music;

    public static void load() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("drop.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // Música del joc
        rainMusic.setVolume(0.2f);
        rainMusic.setLooping(true);
    }

    public static void dispose() {
        // Descartem els recursos
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();

    }
}
