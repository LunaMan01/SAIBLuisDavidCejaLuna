package contador;

import jade.core.Agent;

public class Main extends Agent {
    public void setup() {
        Contador contador = new Contador();
        this.addBehaviour(contador);
    }
}
