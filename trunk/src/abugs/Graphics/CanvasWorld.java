/*
 * Copyright 2008-2010 The sinkCell Development Team
 *
 * This file is part of sinkCell.
 *
 * sinkCell is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * sinkCell is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * sinkCell; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package abugs.Graphics;

import abugs.bug.Bug;
import abugs.world.World;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author sandra
 */
public class CanvasWorld extends Canvas {

    private int canvasSize;
    private World world;
    Graphics offgc;
    Image offscreen = null;

    public CanvasWorld(World world) {
        canvasSize = 600;
        this.world = world;
        this.setSize(canvasSize, canvasSize);
        this.setBackground(Color.white);
    }

    @Override
    public void update(Graphics g) {
        try {
            offscreen = createImage(canvasSize, canvasSize);
            offgc = offscreen.getGraphics();

            List<Bug> population = this.world.getPopulation();
            System.out.println(population.size());

            int size = world.getWorldSize();

            double cellSize = this.canvasSize / size;

            for (Bug bug : population) {
                int n_x = (int) (bug.getx() * canvasSize) / size;
                int n_y = (int) (bug.gety() * canvasSize) / size;
                System.out.println(bug.getVelocity());
                if (bug.getVelocity() == 0) {

                    offgc.setColor(Color.GREEN);
                    offgc.fillRect(n_x, n_y, (int) cellSize, (int) cellSize);
                    offgc.setColor(Color.black);
                    offgc.drawRect(n_x, n_y, (int) cellSize, (int) cellSize);
                }

                if (bug.getVelocity() != 0) {
                    offgc.setColor(Color.red);
                    offgc.fillRect(n_x, n_y, ((int) cellSize) - 1, ((int) cellSize) - 1);
                }

            }
            // transfer offscreen to window
            g.drawImage(offscreen, 0, 0, this);
            world.cicle();

        } catch (Exception e) {
        }

    }
}



