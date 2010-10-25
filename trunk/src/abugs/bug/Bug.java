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
package abugs.bug;

import abugs.bug.brain.Brain;
import java.util.List;

/**
 *
 * @author bicha
 */
public class Bug {

    Chromosome chromosome;
    Brain brain;
    //the bug can grow when it eats
    double growingSize = 0;

    public Bug() {
        this.chromosome = new Chromosome();
        brain = new Brain(chromosome);
    }

    public double getSize() {
        double basicSize = this.chromosome.getGenValue(BodyGenes.SIZE.name()).getValue();
        return basicSize + growingSize;
    }

    public double getVision() {
        return this.chromosome.getGenValue(BodyGenes.VISION.name()).getValue();
    }

    public double getVelocity() {
        return this.chromosome.getGenValue(BodyGenes.VELOCITY.name()).getValue();
    }

    public void getMovementDecision(List<Bug> bugsSeen) {

    }
}
