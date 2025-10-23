package sistemaAutogestion;

import dominio.Cliente;
import dominio.Entrada;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;
import tads.ListaNodos;

public class Sistema implements IObligatorio {

    // Momentaneo: Aca va una pila
    private ListaNodos<Entrada> pilaEntradas;

    private ListaNodos<Cliente> listaClientes;
    private ListaNodos<Sala> listaSalas;
    private ListaNodos<Evento> listaEventos;

    public Sistema() {
        // Momentaneo: Aca va una pila
        pilaEntradas = new ListaNodos();

        listaClientes = new ListaNodos();
        listaSalas = new ListaNodos();
        listaEventos = new ListaNodos();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        // Momentaneo: Aca va una pila
        pilaEntradas = new ListaNodos();

        listaClientes = new ListaNodos();
        listaSalas = new ListaNodos();
        listaEventos = new ListaNodos();
        System.out.println(Retorno.ok().resultado);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        Sala s = new Sala(nombre, capacidad);

        if (listaSalas.cantidadElementos() != 0) {

            if (listaSalas.existeElemento(s)) {
                System.out.println(Retorno.error1().resultado);
                return Retorno.error1();
            } else if (s.getCapacidad() <= 0) {
                System.out.println(Retorno.error2().resultado);
                return Retorno.error2();
            } else {
                listaSalas.agregarInicio(s);
                System.out.println(Retorno.ok().resultado);
                return Retorno.ok();
            }

        } else if (s.getCapacidad() <= 0) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        } else {
            listaSalas.agregarInicio(s);
            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        Sala s = new Sala(nombre, 100);

        if (!listaSalas.existeElemento(s)) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        } else {
            boolean encontrado = false;

            for (int i = 0; i < listaSalas.cantidadElementos() && !encontrado; i++) {
                Sala salaBuscada = listaSalas.obtenerElemento(i);

                if (s.equals(salaBuscada)) {
                    listaSalas.eliminarEnPos(i);
                }
            }
            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        if (aforoNecesario > 0) {

            for (int i = 0; i < listaSalas.cantidadElementos(); i++) {
                Sala salaBuscada = listaSalas.obtenerElemento(i);
                boolean ocupada = salaBuscada.ocupada(fecha);

                if (salaBuscada.getCapacidad() >= aforoNecesario && !ocupada) {
                    Evento e = new Evento(codigo, descripcion, aforoNecesario, salaBuscada, fecha);

                    if (listaEventos.cantidadElementos() == 0 || !listaEventos.existeElemento(e)) {
                        listaEventos.agregarOrdenado(e);

                        ListaNodos<Evento> eventosAux = salaBuscada.getListaEventos();
                        eventosAux.agregarInicio(e);
                        salaBuscada.setListaEventos(eventosAux);

                        return Retorno.ok();
                    } else {
                        System.out.println(Retorno.error1().resultado);
                        return Retorno.error1();
                    }
                }
            }

            System.out.println(Retorno.error3().resultado);
            return Retorno.error3();
        } else {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {

        if (cedula.length() == 8) {

            // Aca valido que la cedula no tenga letras o simbolos
            for (int i = 0; i < cedula.length(); i++) {
                char c = cedula.charAt(i);
                if (c < '0' || c > '9') {
                    System.out.println(Retorno.error1().resultado);
                    return Retorno.error1();
                }
            }
            
            // Si no tiene letas o simbolos crea el cliente y continua
            Cliente c = new Cliente(cedula, nombre);

            if (listaClientes.cantidadElementos() == 0) {
                listaClientes.agregarInicio(c);
                System.out.println(Retorno.ok().resultado);
                return Retorno.ok();
            } else {

                if (!listaClientes.existeElemento(c)) {
                    listaClientes.agregarOrdenado(c);
                    System.out.println(Retorno.ok().resultado);
                    return Retorno.ok();
                } else {
                    System.out.println(Retorno.error2().resultado);
                    return Retorno.error2();
                }
            }

        } else {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        // evento.cantEntradasVendidas++;
        // evento.cantEntradasDisponibles--;
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaSalas.cantidadElementos(); i++) {
            Sala s = listaSalas.obtenerElemento(i);
            if (i < listaSalas.cantidadElementos() - 1) {
                cadena += s.toString() + "#";
            } else {
                cadena += s.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno listarEventos() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (i < listaEventos.cantidadElementos() - 1) {
                cadena += e.toString() + "#";
            } else {
                cadena += e.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno listarClientes() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (i < listaClientes.cantidadElementos() - 1) {
                cadena += c.toString() + "#";
            } else {
                cadena += c.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        int contColumnasOptimas = 0;

        for (int i = 0; i < vistaSala.length; i++) {
            int contLibres = 0;
            int maxOcupadosConsecutivos = 0;
            int contOcupadosConsecutivos = 0;

            for (int j = 0; j < vistaSala[i].length; j++) {

                if (vistaSala[i][j] == "X") {
                    contLibres++;
                    if (maxOcupadosConsecutivos < contOcupadosConsecutivos) {
                        maxOcupadosConsecutivos = contOcupadosConsecutivos;
                    }
                    contOcupadosConsecutivos = 0;

                } else if (vistaSala[i][j] == "O") {
                    contOcupadosConsecutivos++;
                }

            }

            if (maxOcupadosConsecutivos > contLibres) {
                contColumnasOptimas++;
            }
        }

        if (contColumnasOptimas >= 2) {
            System.out.println("Es óptimo");
            return Retorno.ok("Es óptimo");
        } else {
            System.out.println("No es óptimo");
            return Retorno.ok("No es óptimo");
        }
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }

}
