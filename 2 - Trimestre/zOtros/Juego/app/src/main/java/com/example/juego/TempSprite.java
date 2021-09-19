package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

public class TempSprite {

    private float x;
    private float y;
    private Bitmap bmp;
    private int life = 15;
    private List<TempSprite> temps;

    public TempSprite(List<TempSprite> temps, VistaJuego vistaJuego,
                      float x, float y,Bitmap bmp) {

        this.x = Math.min(Math.max(x - bmp.getWidth() /2, 0),
                vistaJuego.getWidth() - bmp.getWidth());
        this.y = Math.min(Math.max(y - bmp.getHeight() /2,0),
                vistaJuego.getHeight() - bmp.getHeight());
        this.temps = temps;
        this.bmp = bmp;
    }

    public void onDraw (Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }

    private void update (){
        if (--life < 1){
            temps.remove(this);
        }
    }
}

