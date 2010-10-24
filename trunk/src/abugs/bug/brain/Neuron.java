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

package abugs.bug.brain;

/**
 *
 * @author bicha
 */
public class Neuron {
    double[] w;
    double dNet;
    double dDelta;
    double b;
    double y;
    int nConnections;


    public Neuron(int nConnections){
        this.nConnections = nConnections;
        this.w = new double[nConnections];
	for(int i = 0; i <  nConnections; i++){
		w[i] = 0;
	}
	dNet = 0;
	dDelta = 0;
    }

    public double getWeight(int index){
        return this.w[index];
    }

    public void setWeight(double w, int index){
        this.w[index] = w;
    }

    public double getb(){
        return this.b;
    }

    public void setb(double b){
        this.b = b;
    }

    public double getNet(){
        return this.dNet;
    }

    public void setNet(double dNet){
        this.dNet = dNet;
    }

    public double gety(){
        return this.y;
    }

    public void sety(double y){
        this.y = y;
    }

    public double getDelta(){
        return this.dDelta;
    }

    public void setDelta(double dDelta){
        this.dDelta = dDelta;
    }

    public int getNWeight(){
        for(int i = 0; i < this.nConnections ; i++){
		if(i < this.nConnections -1 && w[i] == 0 && w[i+1] == 0 && w[i+2] == 0)
			return i-1;
	}
	return this.nConnections;
    }

}
