package helloWorld;

import jade.core.behaviours.Behaviour;

public class Comportamiento extends Behaviour {
    @Override
    public void action() {
        System.out.println("Hello World!");
    }

    @Override
    public boolean done() {
        return true;
    }
}
