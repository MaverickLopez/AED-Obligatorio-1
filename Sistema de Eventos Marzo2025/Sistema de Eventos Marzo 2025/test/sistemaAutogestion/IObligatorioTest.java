/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaOk() {
        Retorno r = miSistema.registrarSala("Alfonso", 50);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError1() {
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.registrarSala("Zorrilla", 80);
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarSala("Padre", 50);
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarSalaError2() {
        Retorno r = miSistema.registrarSala("Padre", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarSala("Carlos", -10);
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testEliminarSalaOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.eliminarSala("Alfonso");
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.eliminarSala("Mario");
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testEliminarSalaError1() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.eliminarSala("Matias");
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.eliminarSala("Pedro");
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);

        Retorno r = miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12401", "Star Wars 2", 500, LocalDate.now().minusDays(2));
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError1() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12401", "Como entrenar a tu dragon", 50, LocalDate.now().minusDays(7));

        Retorno r = miSistema.registrarEvento("12400", "Star Wars 2", 500, LocalDate.now().minusDays(2));
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12401", "Star Wars 3", 300, LocalDate.now().minusDays(4));
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError2() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12401", "Como entrenar a tu dragon", 50, LocalDate.now().minusDays(7));

        Retorno r = miSistema.registrarEvento("12402", "Star Wars 2", 0, LocalDate.now().minusDays(2));
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12403", "Star Wars 3", 0, LocalDate.now().minusDays(4));
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError3() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12403", "Star Wars 4", 80, LocalDate.now());

        Retorno r = miSistema.registrarEvento("12401", "Star Wars 2", 400, LocalDate.now());
        assertEquals(Retorno.error3().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12402", "Star Wars 3", 50, LocalDate.now());
        assertEquals(Retorno.error3().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteOk() {
        Retorno r = miSistema.registrarCliente("54849161", "Maverick Lopez");
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("33105350", "Lourdes Patron");
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.registrarCliente("3548", "Carlos Marimar");
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("9845615", "Pedro Place");
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.registrarCliente("54849161", "Carlos Marimar");
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("33105350", "Pedro Place");
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testListarSalasOk() {
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.listarSalas();
        System.out.println(r.valorString);
        assertEquals("Sala Padre-980#Sala Mario-580#Sala Zorrilla-700",
                r.valorString);
    }

    @Test
    public void testListarEventosOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        miSistema.registrarEvento("gh124", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("a48b5", "Star Wars 2", 500, LocalDate.now());
        miSistema.registrarEvento("o48b5", "Star Wars 3", 80, LocalDate.now());

        Retorno r = miSistema.listarEventos();
        System.out.println(r.valorString);
        assertEquals("a48b5-Star Wars 2-Mario-500-0#gh124-Star Wars-Padre-150-0#o48b5-Star Wars 3-Zorrilla-80-0",
                r.valorString);
    }

    @Test
    public void testListarClientesOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("15489162", "Calos Martinez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.listarClientes();
        System.out.println(r.valorString);
        assertEquals("15489162-Calos Martinez#33105350-Lourdes Patron#54849161-Maverick Lopez",
                r.valorString);
    }

    @Test
    public void testEsSalaOptimaOk() {
        String[][] sala = {
            {"#", "#", "#", "#", "#"},
            {"#", "O", "O", "X", "#"},
            {"#", "O", "X", "X", "#"},
            {"#", "O", "O", "X", "#"},
            {"#", "#", "#", "#", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#", "#"},
            {"#", "O", "O", "X", "X", "#"},
            {"#", "O", "O", "X", "O", "#"},
            {"#", "O", "O", "X", "O", "#"},
            {"#", "#", "#", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("Es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("Es Optimo").resultado, r2.resultado);
    }

    // Ninguna columna cumple
    @Test
    public void testEsSalaOptimaError1() {
        String[][] sala = {
            {"#", "#", "#", "#", "#"},
            {"#", "O", "X", "X", "#"},
            {"#", "X", "O", "X", "#"},
            {"#", "X", "X", "O", "#"},
            {"#", "#", "#", "#", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#"},
            {"#", "X", "X", "X", "#"},
            {"#", "X", "O", "X", "#"},
            {"#", "X", "X", "X", "#"},
            {"#", "X", "O", "X", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("No es Optimo").resultado, r2.resultado);
    }

    // Solo una columna cumple
    @Test
    public void testEsSalaOptimaError2() {
        String[][] sala = {
            {"#", "#", "#", "#"},
            {"#", "O", "X", "#"},
            {"#", "O", "O", "#"},
            {"#", "X", "X", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#", "#"},
            {"#", "X", "O", "X", "O", "#"},
            {"#", "O", "X", "X", "X", "#"},
            {"#", "O", "X", "O", "X", "#"},
            {"#", "O", "X", "O", "X", "#"},
            {"#", "#", "#", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("No es Optimo").resultado, r2.resultado);
    }

    // Sin columnas validas
    @Test
    public void testEsSalaOptimaError3() {
        String[][] sala = {
            {"#", "#", "#"},
            {"#", "#", "#"},
            {"#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);
    }
}
