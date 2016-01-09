package com.udacity.gamedev.drawthecantorgasket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/*
TODO: Start here
The Cantor gasket is a fractal where we start with a white square. We devide that square up into a 3x3 grid of smaller squares, then remove the middle square. Finally, we repeat the process on each of the remaining 8 squares.
 */

public class DrawTheCantorGasket extends ApplicationAdapter {

    ShapeRenderer shapeRenderer;
    // TODO: Set a constant for how many recursions to draw. 5 is a good place to start
    int recursionNumber = 5;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Finds a good place to draw our fractal
        // Rectangle has members x,y for the lower left corner, and width and height
        Rectangle bounds = findLargestSquare();

        // TODO: Begin a filled shapeRenderer batch
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // TODO: Draw a white square matching the bounds
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);

        // TODO: Set the working color to black, and call punchCantorGasket with the bounds
        shapeRenderer.setColor(Color.BLACK);
        punchCantorGasket(bounds.x, bounds.y, bounds.width, recursionNumber);
        // TODO: End the batch
        shapeRenderer.end();
    }


    private void punchCantorGasket(float x, float y, float size, int recursions){
        // TODO: Base case, if recursions = 0, return
        if (recursions == 0) {
           // System.out.println("completed");
            return;
        }
        // TODO: Draw a black square in the middle square
        float midX = x + size/3;
        float midY = y + size/3;
        float width = size/3;
        float height = size/3;
        shapeRenderer.rect(midX, midY, width, height);
        // TODO: Call punchCantorGasket on all 8 other squares
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float xIs = x + (size/3)*i;
                float yIs = y + (size/3)*j;
                //shapeRenderer.rect(xIs, yIs, size/9, size/9);
               // System.out.println(xIs + " and " + yIs);
                punchCantorGasket(xIs, yIs, size/3, recursions - 1);
            }
        }
    }

    private Rectangle findLargestSquare(){
        Rectangle largestSquare = new Rectangle();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (screenWidth > screenHeight){
            largestSquare.x = (screenWidth - screenHeight)/2;
            largestSquare.y = 0;
            largestSquare.width = screenHeight;
            largestSquare.height = screenHeight;
        } else {
            largestSquare.x = 0;
            largestSquare.y = (screenHeight - screenWidth)/2;
            largestSquare.width = screenWidth;
            largestSquare.height = screenWidth;
        }
        return largestSquare;
    }
}