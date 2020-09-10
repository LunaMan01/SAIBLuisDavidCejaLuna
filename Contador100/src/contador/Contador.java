package contador;

import jade.core.behaviours.Behaviour;

public class Contador extends Behaviour {
    int count = 0;

    @Override
    public void action() {
        System.out.println(count);
        count++;
    }

    @Override
    public boolean done() {
        if (count < 101)
            return false;
        else
            return true;
    }
}
