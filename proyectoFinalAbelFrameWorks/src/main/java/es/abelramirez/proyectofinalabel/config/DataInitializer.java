package es.abelramirez.proyectofinalabel.config;

import es.abelramirez.proyectofinalabel.models.entities.*;
import es.abelramirez.proyectofinalabel.models.enums.*;
import es.abelramirez.proyectofinalabel.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ObjetoRepository objetoRepository;
    private final PersonajeRepository personajeRepository;
    private final EnemigoRepository enemigoRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final MapaRepository mapaRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección de dependencias
    public DataInitializer(CategoriaRepository categoriaRepository,
                           ObjetoRepository objetoRepository,
                           PersonajeRepository personajeRepository,
                           EnemigoRepository enemigoRepository,
                           JugadorRepository jugadorRepository,
                           PartidaRepository partidaRepository,
                           MapaRepository mapaRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoriaRepository = categoriaRepository;
        this.objetoRepository = objetoRepository;
        this.personajeRepository = personajeRepository;
        this.enemigoRepository = enemigoRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository;
        this.mapaRepository = mapaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Verificamos si ya hay datos para no duplicar
        if (personajeRepository.count() > 0) {
            System.out.println("⚠️ La base de datos ya contiene datos. Omitiendo carga inicial.");
            return;
        }

        System.out.println("---- 🚀 INICIANDO CARGA DE DATOS (THE BINDING OF ISAAC) ----");

        // -----------------------------------------------------------
        // 2. CREAR CATEGORÍAS
        // -----------------------------------------------------------
        Categoria catPasivos = new Categoria();
        catPasivos.setNombreCategoria("Objetos Pasivos");

        Categoria catActivos = new Categoria();
        catActivos.setNombreCategoria("Objetos Activos");

        Categoria catFamiliares = new Categoria();
        catFamiliares.setNombreCategoria("Familiares");

        categoriaRepository.saveAll(Arrays.asList(catPasivos, catActivos, catFamiliares));

        // -----------------------------------------------------------
        // 3. CREAR OBJETOS
        // -----------------------------------------------------------
        Objeto brimstone = new Objeto();
        brimstone.setNombre("Brimstone");
        brimstone.setDescripcion("Rayo de sangre cargado que atraviesa enemigos.");
        brimstone.setCategoria(catPasivos);

        Objeto d6 = new Objeto();
        d6.setNombre("The D6");
        d6.setDescripcion("Permite cambiar (reroll) los objetos de la sala actual.");
        d6.setCategoria(catActivos);

        Objeto godhead = new Objeto();
        godhead.setNombre("Godhead");
        godhead.setDescripcion("Lágrimas con aura dañina que persiguen a los enemigos.");
        godhead.setCategoria(catPasivos);

        Objeto brotherBobby = new Objeto();
        brotherBobby.setNombre("Brother Bobby");
        brotherBobby.setDescripcion("Un amigo fantasmal que dispara lágrimas normales.");
        brotherBobby.setCategoria(catFamiliares);

        objetoRepository.saveAll(Arrays.asList(brimstone, d6, godhead, brotherBobby));

        // -----------------------------------------------------------
        // 4. CREAR PERSONAJES
        // -----------------------------------------------------------
        Personaje isaac = new Personaje();
        isaac.setNombre("Isaac");
        isaac.setNumCorazones(3);
        isaac.setTipoCorazon(TipoCorazon.CORAZON_ROJO);
        isaac.setAtaque(3.5);
        isaac.setVelocidad(1.0);
        isaac.setVelocidadLagrimas(1.0);
        isaac.setRango(23.75);

        Personaje azazel = new Personaje();
        azazel.setNombre("Azazel");
        azazel.setNumCorazones(3);
        azazel.setTipoCorazon(TipoCorazon.CORAZON_OSCURO);
        azazel.setAtaque(5.5);
        azazel.setVelocidad(1.25);
        azazel.setVelocidadLagrimas(0.5);
        azazel.setRango(18.0);

        Personaje theLost = new Personaje();
        theLost.setNombre("The Lost");
        theLost.setNumCorazones(0); // Muere de un golpe
        theLost.setTipoCorazon(TipoCorazon.CORAZON_AZUL);
        theLost.setAtaque(3.5);
        theLost.setVelocidad(1.0);
        theLost.setVelocidadLagrimas(1.0);
        theLost.setRango(23.75);

        personajeRepository.saveAll(Arrays.asList(isaac, azazel, theLost));

        // -----------------------------------------------------------
        // 5. CREAR ENEMIGOS (BOSSES)
        // -----------------------------------------------------------
        Enemigo monstro = new Enemigo();
        monstro.setNombreEnemigo("Monstro");
        monstro.setVida(250L);
        monstro.setTipo(TipoEnemigo.JEFE_PISO);

        Enemigo mom = new Enemigo();
        mom.setNombreEnemigo("Mom");
        mom.setVida(600L);
        mom.setTipo(TipoEnemigo.JEFE_FINAL);

        Enemigo duke = new Enemigo();
        duke.setNombreEnemigo("Duke of Flies");
        duke.setVida(110L);
        duke.setTipo(TipoEnemigo.JEFE_PISO);

        enemigoRepository.saveAll(Arrays.asList(monstro, mom, duke));

        // -----------------------------------------------------------
        // 6. CREAR JUGADORES
        // -----------------------------------------------------------
        Jugador abel = new Jugador();
        abel.setNombre("Abel");
        abel.setEmail("abel@isaac.com");
        abel.setPassword(passwordEncoder.encode("1234")); // <--- AHORA SÍ TIENE PASS
        abel.setRoles("ADMIN"); // Asegúrate de tener este campo en tu entidad o lógica

        Jugador tester = new Jugador();
        tester.setNombre("ProGamer");
        tester.setEmail("gamer@test.com");
        tester.setPassword(passwordEncoder.encode("1234")); // <--- PASS
        tester.setRoles("USER");

        jugadorRepository.saveAll(Arrays.asList(abel, tester));

        jugadorRepository.saveAll(Arrays.asList(abel, tester));

        // -----------------------------------------------------------
        // 7. CREAR PARTIDAS (Transacciones y Relaciones N:M)
        // -----------------------------------------------------------

        // --- Partida 1: Abel gana con Isaac ---
        Partida partida1 = new Partida();
        partida1.setJugador(abel);
        partida1.setPersonaje(isaac);
        partida1.setFechaPartida(LocalDateTime.now().minusDays(2)); // Fue hace 2 días
        partida1.setTipoJuego(TipoJuego.NORMAL);
        partida1.setEstadoJugador(EstadoJugador.VICTORIA);

        // Relación N:M -> Añadimos objetos conseguidos
        partida1.getObjetos().add(d6);
        partida1.getObjetos().add(godhead);

        // Relación N:M -> Añadimos enemigos derrotados
        partida1.getEnemigos().add(monstro);
        partida1.getEnemigos().add(mom);

        partidaRepository.save(partida1);

        // --- Partida 2: Tester pierde con Azazel ---
        Partida partida2 = new Partida();
        partida2.setJugador(tester);
        partida2.setPersonaje(azazel);
        partida2.setFechaPartida(LocalDateTime.now().minusHours(5)); // Fue hoy
        partida2.setTipoJuego(TipoJuego.DIFICIL);
        partida2.setEstadoJugador(EstadoJugador.MUERTO);

        // Cogió Brimstone pero murió contra el Duque
        partida2.getObjetos().add(brimstone);
        partida2.getEnemigos().add(duke);

        partidaRepository.save(partida2);

        // -----------------------------------------------------------
        // 8. CREAR MAPAS (CORREGIDO)
        // -----------------------------------------------------------
        Mapa salaTesoro = new Mapa();
        salaTesoro.setNombre("Sala del Tesoro - Sótano 1");
        salaTesoro.setTipoSala(TipoSala.SOTANO);

        // --- CORRECCIÓN AQUÍ ---
        // Como 'objetos' es una lista, no podemos usar setObjeto().
        // Debemos añadirlo a la lista existente:
        salaTesoro.getObjetos().add(brimstone);

        // O alternativamente: salaTesoro.setObjetos(Arrays.asList(brimstone));

        mapaRepository.save(salaTesoro);

        System.out.println("---- ✅ CARGA DE DATOS COMPLETADA CON ÉXITO ----");

        System.out.println("---- ✅ CARGA DE DATOS COMPLETADA CON ÉXITO ----");
    }
}
