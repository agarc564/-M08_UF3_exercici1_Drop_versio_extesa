package com.mygdx.dropextended;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {

    final Drop game;

    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    private Stage stage;
    // Per obtenir el batch de l'stage
    private Batch batch;
    private Bucket cubell;
    private Drops gota;


    public GameScreen(final Drop game) {
        this.game = game;

        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);


        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(800, 480, camera);

        // Creem l'stage i assignem el viewport
        stage = new Stage(viewport);

        batch = stage.getBatch();

        // Creem els actors
        cubell = new Bucket(800 / 2 - 64 / 2, 20, 64, 64);
        //gota = new Drops(MathUtils.random(0, 800 - 64), 480, 64, 64);

        // Afegim els actors a l'stage
        stage.addActor(cubell);
        //stage.addActor(dropsHandler);

        //Exemple GET request
        String URL = "http://www.google.es";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        //Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url("http://www.google.es").content("q=libgdx&example=example").build();
        Net.HttpRequest httpGetRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(URL).content("q=libgdx&example=example").build();

        //Exemple POST request
        Net.HttpRequest httpPOST = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPOST.setUrl(URL);
        httpPOST.setContent("q=libgdx&example=example");

        //Crea un ResponseListener
        Net.HttpResponseListener httpResponseListener = new Net.HttpResponseListener() {

            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    //do something when the request gets resut back
                Gdx.app.log("MSG", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                //do something when it fails
            }

            @Override
            public void cancelled() {
                 //do something when the request get cancelled
            }
        };
        Gdx.net.sendHttpRequest(httpGetRequest, httpResponseListener);

        /*Gdx.net.sendHttpRequest(httpPOST, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("MSG", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("LOGIN", "was NOT successful!");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("LOGIN", "was cancelled!");
            }
        });*/
    }



    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000){
            gota = new Drops(MathUtils.random(0, 800 - 64), 480, 64, 64);
            stage.addActor(gota);
            lastDropTime = TimeUtils.nanoTime();
        }

        stage.draw();
        stage.act(delta);

        if (gota.collides(cubell)) {
            dropsGathered++;
            dropSound.play();
        }
    }

    public Bucket getCubell() {
        return cubell;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        rainMusic.play();
    }

    @Override
    public void hide() {
        //rainMusic.pause();
    }

    @Override
    public void pause() {
        //rainMusic.pause();
    }

    @Override
    public void resume() {
        //rainMusic.play();
    }

    @Override
    public void dispose() {
        //dropImage.dispose();
        //bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }

}
