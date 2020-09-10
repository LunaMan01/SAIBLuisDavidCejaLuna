package helloWorld;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Agente extends Agent {
    public void setup() {
        Comportamiento comp = new Comportamiento();
        this.addBehaviour(comp);
    }
}
