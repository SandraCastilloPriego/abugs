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

/**
 *
 * @author bicha
 */
public class Gen {
    String name;
    double value;

    public Gen(String name){
        this.name = name;
        this.value = Math.random();  
    }

    public Gen(String name, double min, double max){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getValue(){
        return value;
    }

    public void Mutate(){}

}