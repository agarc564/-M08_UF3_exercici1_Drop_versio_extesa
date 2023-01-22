package com.mygdx.dropextended;

import java.util.ArrayList;
import java.util.Random;

import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;

public class DropsHandler extends Group {

    private ArrayList<Drops> drops;
    private Drops drop;
    private long lastDropTime;
    private int  numGotes = 3;
    Sound dropSound;

    // Objecte random
    Random r;

    public DropsHandler() {

        // Creem l'ArrayList
        drops = new ArrayList<Drops>();

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

        // Afegim la primera gota a l'array i al grup
        //Drops drop = new Drops(MathUtils.random(0, 800 - 64), 480, 64, 64);

        /*drop = new Drops(MathUtils.random(0, 800 - 64), 240, 64, 64);
        Drops drop2 = new Drops(MathUtils.random(0, 800 - 64), 400, 64, 64);
        Drops drop3 = new Drops(MathUtils.random(0, 800 - 64), 150, 64, 64);
        drops.add(drop);
        drops.add(drop2);
        drops.add(drop3);
        addActor(drop);
        addActor(drop2);
        addActor(drop3);*/


    }

    public boolean collides(Bucket cubell) {

        Rectangle rectCubell = cubell.getCollisionRectangle();
        Rectangle rectGota;

        rectGota = drop.getCollisionRectangle();

        if (rectGota.overlaps(rectCubell)) {
            //dropsGathered++;
            dropSound.play();
            //iter.remove();
        }


        /*for (int i = 0; i < numGotes; i++) {
            rectGota = drops.get(i).getCollisionRectangle();
            if (rectGota.overlaps(rectCubell)) {
                //dropsGathered++;
                dropSound.play();
                //iter.remove();
            }
        }*/
           return false;
    }


}
