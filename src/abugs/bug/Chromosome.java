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

import abugs.bug.brain.BrainGenes;
import java.util.Hashtable;

/**
 *
 * @author bicha
 */
public class Chromosome {

    Hashtable<String, Gen> genes;

    public Chromosome(){
        genes  = new Hashtable<String, Gen>();
        CreateGenes();
    }

    public void CreateGenes(){
        //Brain genes
        for(BrainGenes names : BrainGenes.values()){
            Gen gen = new Gen(names.name());
            genes.put(names.name(), gen);
        }
        for(BodyGenes names : BodyGenes.values()){
            Gen gen = new Gen(names.name());
            genes.put(names.name(), gen);
        }
    }

    public Gen getGenValue(String geneName){
        return genes.get(geneName);
    }

}
