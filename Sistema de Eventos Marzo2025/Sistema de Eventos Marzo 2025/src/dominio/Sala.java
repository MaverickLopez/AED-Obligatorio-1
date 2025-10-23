package dominio;

import java.time.LocalDate;
import tads.ListaNodos;

public class Sala implements Comparable<Sala> {

    private static int UltimoId = 1;
    private int Id;
    private String Nombre;
    private int Capacidad;
    private ListaNodos<Evento> listaEventos;

    public Sala(String nombre, int capacidad) {
        Id = UltimoId;
        Nombre = nombre;
        Capacidad = capacidad;
        this.listaEventos = new ListaNodos();
        UltimoId++;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public ListaNodos<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(ListaNodos<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public int getId() {
        return Id;
    }

    @Override
    public boolean equals(Object obj) {
        Sala s = (Sala) obj;

        return getNombre().equals(s.getNombre());
    }

    public boolean ocupada(LocalDate fecha) {
        boolean ret = false;

        for (int i = 0; i < listaEventos.cantidadElementos() && ret == false; i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getFecha().getMonthValue() == fecha.getMonthValue()
                    && e.getFecha().getDayOfWeek() == fecha.getDayOfWeek()) {
                ret = true;
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        return "Sala " + getNombre() + "-" + getCapacidad();
    }

    @Override
    public int compareTo(Sala s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
