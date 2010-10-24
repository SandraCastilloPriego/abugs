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
public class NeuralNet {

    Neuron[] nNet;
    double d;
    double LearningRate;
    double[] x;
    int iNInput;
    int iNLayer1;
    int iNLayer2;
    int iNExit;

    public void SetNeuronalNet(int iNInput, int iNLayer1, int iNLayer2, int iNExit) {
        this.iNInput = iNInput;
        this.iNLayer1 = iNLayer1;
        this.iNLayer2 = iNLayer2;
        this.iNExit = iNExit;

        d = 1;
        LearningRate = 0.01;
        NewNet();
    }

    private void NewNet() {
        int iNeuron = iNLayer1 + iNLayer2 + iNExit;
        nNet = new Neuron[iNeuron];
        for (int i = 0; i < iNeuron; i++) {
            nNet[i] = new Neuron(iNeuron);
        }

        DoNewNet();
    }

    private void IntroX(double[] x) {
        this.x = x;
    }

    private void DoNewNet() {

        double N = 1 / (double) iNInput;
        double Z;
        if ((iNLayer1 + iNLayer2) == 0) {
            Z = 1;
        } else {
            Z = Math.pow((double) (iNLayer1 + iNLayer2), N);
        }

        double dScale = 0.7 * Z;

        for (int s = 0; s < (iNLayer1 + iNLayer2 + iNExit); s++) {

            double dRand = dScale * (Math.random() / (100000000 + 1.0));

            float fSigned = (float) (1.0 * (Math.random() / (100000000 + 1.0)));

            if (fSigned < 0.5) {
                nNet[s].setb(dRand);
            } else {
                nNet[s].setb(dRand * (-1));
            }


            if (s < iNLayer1) {
                Weight(iNInput, s, dScale);
            } else if (s < (iNLayer1 + iNLayer2)) {
                Weight(iNLayer1, s, dScale);
            } else if (s < (iNLayer1 + iNLayer2 + iNExit)) {
                Weight(iNLayer2, s, dScale);
            }
        }
    }

    private void Weight(int iNInput, int iNNeuron, double dScale) {
        double dAverage = 0;
        double[] dRandAverage = new double[1000];


        for (int e = 0; e < iNInput; e++) {

            double dRand = dScale * (Math.random() / (100000000 + 1.0));
            float fSigned = (float) (1.0 * (Math.random() / (100000000 + 1.0)));

            if (fSigned < 0.5) {
                dRand *= (-1);
            }

            dRandAverage[e] = dRand;
            dAverage += dRand;
        }

        for (int e = 0; e < iNInput; e++) {
            double dWeight = (dRandAverage[e] / dAverage) * dScale;
            nNet[iNNeuron].setWeight(dWeight, e);
        }
    }

    void BackProp() {
        double dDer;

        for (int s = (iNLayer1 + iNLayer2); s < (iNLayer1 + iNLayer2 + iNExit); s++) {
            dDer = ((1 + nNet[s].gety()) * (1 - nNet[s].gety())) * 0.5;
            //Delta=(d-y)*f'(net);
            nNet[s].setDelta((d - nNet[s].gety()) * dDer);
        }

        for (int s = iNLayer1, f = 0; s < (iNLayer1 + iNLayer2); s++, f++) {
            dDer = ((1 + nNet[s].gety()) * (1 - nNet[s].gety())) * 0.5;
            nNet[s].setDelta(dDer * (nNet[(iNLayer1 + iNLayer2 + iNExit - 1)].getDelta() * nNet[(iNLayer1 + iNLayer2 + iNExit - 1)].getWeight(f)));
        }

        for (int s = 0, f = 0; s < iNLayer1; s++, f++) {
            dDer = ((1 + nNet[s].gety()) * (1 - nNet[s].gety())) * 0.5;
            double dSum = 0;
            for (int e = iNLayer1; e < (iNLayer1 + iNLayer2); e++) {
                dSum += (nNet[e].getDelta() * nNet[e].getWeight(f));
            }
            nNet[s].setDelta(dSum * dDer);
        }
    }

    void Update() {
        int cape;


        for (int s = (iNLayer1 + iNLayer2); s < (iNLayer1 + iNLayer2 + iNExit); s++) {
            cape = 0;
            for (int e = 0; e < iNLayer2; e++) {
                cape = iNLayer2 - e;
                nNet[s].setWeight(nNet[s].getWeight(e) + (LearningRate * nNet[s].getDelta() * nNet[(iNLayer1 + iNLayer2) - cape].gety()), e);
                //printf("actualizacion peso: %f\n", nNet[s].GetWeight(e));
            }
            nNet[s].setb(nNet[s].getb() + (LearningRate * nNet[s].getDelta()));
        }

        for (int s = iNLayer1; s < (iNLayer1 + iNLayer2); s++) {
            cape = 0;
            for (int e = 0; e < iNLayer1; e++) {
                cape = iNLayer1 - e;
                nNet[s].setWeight(nNet[s].getWeight(e) + (LearningRate * nNet[s].getDelta() * nNet[(iNLayer1 - cape)].gety()), e);
                //printf("actualizacion peso: %f\n", nNet[s].GetWeight(e));
            }
            nNet[s].setb(nNet[s].getb() + (LearningRate * nNet[s].getDelta()));
        }

        for (int s = 0; s < iNLayer1; s++) {
            for (int e = 0; e < iNInput; e++) {
                nNet[s].setWeight(nNet[s].getWeight(e) + (LearningRate * nNet[s].getDelta() * x[e]), e);
                //printf("actualizacion peso: %f\n", nNet[s].GetWeight(e));
            }
            nNet[s].setb(nNet[s].getb() + (LearningRate * nNet[s].getDelta()));
        }
    }

    void BackPropNet() {
        int cape = 0;

        for (int s = 0; s < iNLayer1; s++) {

            for (int e = 0; e < iNInput; e++) {
                nNet[s].setNet(nNet[s].getNet() + (x[e] * nNet[s].getWeight(e)));
            }
            nNet[s].setNet(nNet[s].getNet() + nNet[s].getb());
            //función de activación = sigmoidea bipolar
            nNet[s].sety((2.0 / (1 + Math.exp(nNet[s].getNet() * (-1)))) - 1);
        }

        for (int s = iNLayer1; s < (iNLayer1 + iNLayer2); s++) {
            for (int e = 0; e < iNLayer1; e++) {
                cape = iNLayer1 - e;
                nNet[s].setNet(nNet[s].getNet() + (nNet[iNLayer1 - cape].gety() * nNet[s].getWeight(e)));
            }
            nNet[s].setNet(nNet[s].getNet() + nNet[s].getb());
            nNet[s].sety((2 / (1 + Math.exp(nNet[s].getNet() * (-1)))) - 1);
        }

        for (int s = (iNLayer1 + iNLayer2); s < (iNLayer1 + iNLayer2 + iNExit); s++) {
            cape = 0;
            for (int e = 0; e < iNLayer2; e++) {
                cape = iNLayer2 - e;
                nNet[s].setNet(nNet[s].getNet() + (nNet[((iNLayer1 + iNLayer2) - cape)].gety() * nNet[s].getWeight(e)));
            }
            nNet[s].setNet(nNet[s].getNet() + nNet[s].getb());
            nNet[s].sety((2 / (1 + Math.exp(nNet[s].getNet() * (-1)))) - 1);
        }
    }

    void SetRate(double LearningRate) {
        this.LearningRate = LearningRate;
    }

    double GetRate() {
        return LearningRate;
    }

    double Gety() {
        return nNet[((iNLayer1 + iNLayer2 + iNExit) - 1)].gety();
    }

    void Setd(double d) {
        this.d = d;
    }

    Neuron GetNeuron(int i) {
        return nNet[i];
    }

    int GetNNeuronas() {
        return (iNLayer1 + iNLayer2 + iNExit);
    }
    /* void WeightCopy(NeuronalNet[] Net)
    {
    int iMax;
    for(int i = 0; i < (iNLayer1 + iNLayer2 + iNExit); i++){
    if(i < iNLayer1) iMax = iNInput;
    else if(i >= iNLayer1 && i < (iNLayer1 + iNLayer2)) iMax = iNLayer1;
    else if(i >= (iNLayer1 + iNLayer2)) iMax = iNLayer2;

    for(int e = 0; e < iMax; e++){
    nNet[i].SetWeight(Net.GetNeuron(i).GetWeight(e),e);
    }
    nNet[i].Setb(Net.GetNeuron(i).Getb());

    }
    }*/
}
