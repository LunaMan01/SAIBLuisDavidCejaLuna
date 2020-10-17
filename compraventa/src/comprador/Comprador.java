package comprador;

import behaviours.RequestPerformer;
import interfaces.CompradorInterfaz;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class Comprador extends Agent {
    private String titulo;
    private AID[] agentesVendedores;
    private int ticker_timer = 10000;
    private Comprador comprador = this;
    private CompradorInterfaz compradorInterfaz;

    private Object[] libroC;

    protected void setup() {
        compradorInterfaz = new CompradorInterfaz(this);
        compradorInterfaz.mostrar();

        System.out.println("Buyer agent " + getAID().getName() + " is ready");
    }

    public void isReady() {
        System.out.println(getLibroC() + "!!!!!!!!!!!!");
        libroC = getLibroC();
        if (libroC != null && libroC.length > 0) {
            titulo = (String) libroC[0];
            System.out.println("Book: " + titulo);

            addBehaviour(new TickerBehaviour(this, ticker_timer) {
                protected void onTick() {
                    System.out.println("Trying to buy " + titulo);

                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("book-selling");
                    template.addServices(sd);

                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);
                        System.out.println("Found the following seller agents:");
                        agentesVendedores = new AID[result.length];
                        for (int i = 0; i < result.length; i++) {
                            agentesVendedores[i] = result[i].getName();
                            System.out.println(agentesVendedores[i].getName());
                        }

                    } catch (FIPAException fe) {
                        fe.printStackTrace();
                    }

                    myAgent.addBehaviour(new RequestPerformer(comprador));
                }
            });
        } else {
            System.out.println("No target book title specified");
            doDelete();
        }
    }

    protected void takeDown() {
        System.out.println("Buyer agent " + getAID().getName() + " terminating");
    }

    public AID[] getAgentesVendedores() {
        return agentesVendedores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setLibroC(Object[] libroC) {
        this.libroC = libroC;
    }

    public Object[] getLibroC() {
        return libroC;
    }
}
