/*
 * Copyright 2010
 * This file is part of XXXXXX.
 * XXXXXX is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * XXXXXX is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * XXXXXX; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package abugs.world;

import abugs.bug.Bug;
import java.util.List;
import java.util.Random;

/**
 *
 * @author bicha
 */
public class World {

    Cell[][] cells;
    Random rand;
    int numberOfBugs;
    int numberOfCells;
    int cellsPerSide;

    public World(int cellsPerSide, int numberOfBugs) {
        this.cellsPerSide = cellsPerSide;
        this.numberOfBugs = numberOfBugs;
        this.numberOfCells = cellsPerSide * cellsPerSide;

        this.rand = new Random();
        cells = new Cell[cellsPerSide][];
        for (int i = 0; i < cellsPerSide; i++) {
            cells[i] = new Cell[cellsPerSide];
        }
        for (int i = 0; i < numberOfBugs; i++) {
            Bug bug = new Bug();
            this.addBug(bug);
        }
    }

    public void addBug(Bug bug) {
        boolean isInside = false;
        int cont = 0;
        while (isInside) {
            int X = this.rand.nextInt(this.cellsPerSide);
            int Y = this.rand.nextInt(this.cellsPerSide);
            if (cells[X][Y].bugFits(bug)) {
                cells[X][Y].addBug(bug);
                isInside = true;
            }
            cont++;
            if (cont > numberOfCells) {
                break;
            }
        }
    }

    public void cicle() {
        move();
        for (Cell[] cellx : cells) {
            for (Cell cell : cellx) {
                cell.update();
            }
        }
    }

    private void move() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                this.selectNewCell(cells[x][y], x, y);
            }
        }
    }

    private void selectNewCell(Cell cell, int x, int y) {
        List<Bug> bugs = cell.getBugs();

        for (Bug bug : bugs) {
            double vision = bug.getVision();
       //     List<Bug> bugsSeen = this.bugsSeen(x, y, newx, newy,  vision);
       //     double velocity = bug.getVelocity();
            
           // bug.getMovementDecision(bugsSeen);

        }
    }


   /* private List<Bug> bugsSeen(int x, int y, int newx, newy, double vision){

    }*/
}
