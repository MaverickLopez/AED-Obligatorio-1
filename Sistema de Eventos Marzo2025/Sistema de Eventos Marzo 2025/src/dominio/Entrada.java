package dominio;

public class Entrada implements Comparable<Entrada> {

    private static int UltimoId = 1;
    private int Id;
    private Evento Evento;
    private Cliente Cliente;

    public Entrada(Evento evento, Cliente cliente) {
        Id = UltimoId;
        Evento = evento;
        Cliente = cliente;
        UltimoId++;
    }

    public Evento getEvento() {
        return Evento;
    }

    public void setEvento(Evento Evento) {
        this.Evento = Evento;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public int getId() {
        return Id;
    }

    @Override
    public int compareTo(Entrada e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
