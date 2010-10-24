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

package abugs;

import abugs.Graphics.GraphicsWorld;
import abugs.world.World;

/**
 *
 * @author bicha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World(100, 100);
        GraphicsWorld gworld = new GraphicsWorld(world);
        gworld.setVisible(true);
    }

}
