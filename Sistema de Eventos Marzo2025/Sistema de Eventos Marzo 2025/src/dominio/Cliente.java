package dominio;

import tads.ListaNodos;

public class Cliente implements Comparable<Cliente> {

    private static int UltimoId = 1;
    private int Id;
    private String Cedula;
    private String Nombre;
    private ListaNodos<Entrada> listaEntradas;

    public Cliente(String cedula, String nombre) {
        Id = UltimoId;
        Cedula = cedula;
        Nombre = nombre;
        listaEntradas = new ListaNodos();
        UltimoId++;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public ListaNodos<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(ListaNodos<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    public int getId() {
        return Id;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente c = (Cliente) obj;

        return getCedula().equals(c.getCedula());
    }

    @Override
    public String toString() {
        return getCedula() + "-" + getNombre();
    }

    @Override
    public int compareTo(Cliente c) {
        return getCedula().compareTo(c.getCedula());
    }

}
