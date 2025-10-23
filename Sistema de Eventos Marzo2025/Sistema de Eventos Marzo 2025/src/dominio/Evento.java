package dominio;

import java.time.LocalDate;
import tads.ListaNodos;

public class Evento implements Comparable<Evento> {

    private static int UltimoId = 1;
    private int Id;
    private String Codigo;
    private String Descripcion;
    private double PuntajePromedio;
    private int AforoNecesario;
    private LocalDate Fecha;
    private Sala Sala;

    //Momentaneo: Aca va una pila
    private ListaNodos<Entrada> entradas;
    
    private int cantEntradasVendidas;
    private int cantEntradasDisponibles;
    
    //Momentaneo: Aca va una cola
    private ListaNodos<Cliente> listaEspera;

    private ListaNodos<Calificacion> calificaciones;

    public Evento(String codigo, String descripcion, int aforoNecesario, Sala sala, LocalDate fecha) {
        Id = UltimoId;
        Codigo = codigo;
        Descripcion = descripcion;
        PuntajePromedio = 0;
        AforoNecesario = aforoNecesario;
        Fecha = fecha;
        Sala = sala;
        this.entradas = new ListaNodos();
        cantEntradasVendidas = 0;
        cantEntradasDisponibles = aforoNecesario;
        this.listaEspera = new ListaNodos();
        this.calificaciones = new ListaNodos();
        UltimoId++;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getAforoNecesario() {
        return AforoNecesario;
    }

    public void setAforoNecesario(int AforoNecesario) {
        this.AforoNecesario = AforoNecesario;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    public Sala getSala() {
        return Sala;
    }

    public void setSala(Sala Sala) {
        this.Sala = Sala;
    }

    public ListaNodos<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(ListaNodos<Entrada> entradas) {
        this.entradas = entradas;
    }

    public ListaNodos<Cliente> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(ListaNodos<Cliente> listaEspera) {
        this.listaEspera = listaEspera;
    }

    public ListaNodos<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ListaNodos<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public int getCantEntradasVendidas() {
        return cantEntradasVendidas;
    }

    public void setCantEntradasVendidas(int cantEntradasVendidas) {
        this.cantEntradasVendidas = cantEntradasVendidas;
    }

    public int getCantEntradasDisponibles() {
        return cantEntradasDisponibles;
    }

    public void setCantEntradasDisponibles(int cantEntradasDisponibles) {
        this.cantEntradasDisponibles = cantEntradasDisponibles;
    }

    public double getPuntajePromedio() {
        return PuntajePromedio;
    }

    public void setPuntajePromedio(double PuntajePromedio) {
        this.PuntajePromedio = PuntajePromedio;
    }

    public int getId() {
        return Id;
    }

    @Override
    public boolean equals(Object obj) {
        Evento e = (Evento) obj;

        return getCodigo().equals(e.getCodigo());
    }

    @Override
    public String toString() {
        return getCodigo() + "-" + getDescripcion() + "-"
                + getSala().getNombre() + "-" + getCantEntradasDisponibles() + "-" + getCantEntradasVendidas();
    }

    @Override
    public int compareTo(Evento e) {
        return getCodigo().compareTo(e.getCodigo());
    }
}
