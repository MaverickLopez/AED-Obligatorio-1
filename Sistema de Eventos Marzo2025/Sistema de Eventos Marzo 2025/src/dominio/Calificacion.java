package dominio;

public class Calificacion implements Comparable<Calificacion> {

    private static int UltimoId = 1;
    private int Id;
    private int Puntaje;
    private String Comentario;

    public Calificacion(int puntaje, String comentario) {
        Id = UltimoId;
        Puntaje = puntaje;
        Comentario = comentario;
        UltimoId++;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int Puntaje) {
        this.Puntaje = Puntaje;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public int getId() {
        return Id;
    }

    @Override
    public int compareTo(Calificacion c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
