import Hibernate.entity.Ciclista;
import Hibernate.entity.Equipo;
import Hibernate.entity.Palmares;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    static Session session;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String respuesta = "";

    public static void main(String[] args) {
        System.out.println("Bienvenido a la vuelta ciclista de Progresa");

        menu();




    }

    private static void menu() {
        do {

            System.out.println("--- Elige una opción ---");
            System.out.println("1. Insertar");
            System.out.println("2. Actualizar");
            System.out.println("3. Ver");
            System.out.println("4. Borrar");
            System.out.println("5. Salir");

            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                respuesta = br.readLine();

                switch (respuesta){
                    case "1" -> insertar();
                    case "2" -> actualizar();
                    case "3" -> ver();
                    case "4" -> borrar();
                    default -> {
                        if (!respuesta.equals("5")) System.out.println("Error: introduce un valor númerico");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            session.getTransaction().commit();
            session.close();

        }while (!respuesta.equals(""));
    }

    private static void borrar() throws IOException {

        List<Equipo> equipos = session.createQuery("from Equipo ",Equipo.class).list();//Recogemos la lista de equipos de la BD
        for (Equipo e: equipos) {
            System.out.println("Id: "+e.getId()+ " | Nombre: "+e.getNombre());
        }
        System.out.println("Dime el id del equipo que quieres eliminar");
        int idEquipo = Integer.parseInt(br.readLine());
        Equipo equipo = session.get(Equipo.class,idEquipo);

        session.remove(equipo);

    }

    private static void ver() {
        List<Equipo> equipos = session.createQuery("from Equipo ",Equipo.class).list();//Recogemos la lista de equipos de la BD
        for (Equipo e: equipos) {
            System.out.println(e.toString());
        }
    }

    private static void actualizar() throws IOException {

        do {
            System.out.println("Selecciona una opción");
            System.out.println("1. Actualizar Equipo");
            System.out.println("2. Actualizar ciclista");
            System.out.println("3. Salir");
            respuesta = br.readLine();

            switch (respuesta){
                case "1" -> modificarEquipo();
                case "2" -> modificarCiclista();
                default -> {
                    if (!respuesta.equals("3")) System.out.println("La respuesta debe ser 1, 2 o 3");
                }
            }
        }while (!respuesta.equals("3"));


    }

    private static void modificarCiclista() throws IOException {
        List<Ciclista> ciclistas = session.createQuery("from Ciclista ",Ciclista.class).list();//Recogemos la lista de equipos de la BD
        for (Ciclista c: ciclistas) {
            System.out.println("Id: "+c.getId()+ " | Nombre: "+c.getNombre());
        }
        System.out.println("Selecciona el id del ciclista a modificar");
        int idCiclista = Integer.parseInt(br.readLine());

        Ciclista ciclista = session.get(Ciclista.class,idCiclista); //Recogemos el objeto de la consulta anterior con el id

        System.out.println("Dime el nombre: ");
        String nombre = br.readLine();
        System.out.println("Dime los apellidos: ");
        String apellidos = br.readLine();

        ciclista.setNombre(nombre);
        ciclista.setApellidos(apellidos);

        session.merge(ciclista); //Actualiza los cambios en la BD

    }

    private static void modificarEquipo() throws IOException {
        List<Equipo> equipos = session.createQuery("from Equipo ",Equipo.class).list();//Recogemos la lista de equipos de la BD
        for (Equipo e: equipos) {
            System.out.println("Id: "+e.getId()+ " | Nombre: "+e.getNombre());
        }
        System.out.println("Selecciona el id del equipo a modificar");
        int idEquipo = Integer.parseInt(br.readLine());

        Equipo equipo = session.get(Equipo.class,idEquipo);

        System.out.println("Dime el nombre: ");
        String nombre = br.readLine();
        System.out.println("Dime el patrocinador: ");
        String patrocinador = br.readLine();

        equipo.setNombre(nombre);
        equipo.setPatrocinador(patrocinador);

        session.merge(equipo);//PARA actualizar los cambios

    }

    private static void insertar() throws IOException {
        do {
            System.out.println("¿Qué deseas insertar?");
            System.out.println("1. Equipo");
            System.out.println("2. Ciclista");
            System.out.println("3. Salir");
            respuesta = br.readLine();

            switch (respuesta){
                case "1" -> insertarEquipo();
                case "2" -> insertarCiclista(null);
                default -> {
                    if (!respuesta.equals("3")) System.out.println("La respuesta debe ser 1, 2 o 3");
                }
            }
        }while (!respuesta.equals("3"));
    }

    private static Ciclista insertarCiclista(Equipo equipo) throws IOException {
        Equipo equipoA = equipo;
        if (equipo == null){ //Si equipo es null es q hemos elegido la opcion 2 y solo queremos insertar un ciclista (no venimos de equipo)
            List<Equipo> equipos = session.createQuery("from Equipo ",Equipo.class).list();//Recogemos la lista de equipos de la BD
            for (Equipo e: equipos) {
                System.out.println("Id: "+e.getId()+ " | Nombre: "+e.getNombre());
            }
            System.out.println("Selecciona el id del equipo:");
            String idEquipo = br.readLine();
            equipoA = session.get(Equipo.class, idEquipo);
        }

        //Ciclista
        System.out.println("Dime el nombre del ciclista");
        String nombre = br.readLine();
        System.out.println("Dime los apellidos del ciclista");
        String apellidos = br.readLine();


        //Palmares
         System.out.println("Dime el número de vueltas ganadas");
        int numVueltas = Integer.parseInt(br.readLine());
        System.out.println("Dime el número de maillots");
        int numMaillots = Integer.parseInt(br.readLine());

        Palmares palmares = new Palmares(numVueltas, numMaillots); //Creamos palmares
        Ciclista ciclista = new Ciclista(nombre,apellidos,palmares);//Creamos ciclista con el palmares

        palmares.setCiclista(ciclista);//Le asignamos el ciclista al palmares
        ciclista.setEquipo(equipoA);//Le asignamos el equipo al ciclista


        if (equipo == null){//SI vienen de insertar ciclista, actualizamos en el equipo la lista de cilistas
            equipoA.getCiclistas().add(ciclista);
        }

        session.persist(ciclista);//Añadimos el ciclista a la BD


        return ciclista;
    }

    private static void insertarEquipo() throws IOException {
        //Equipo
        System.out.println("Dime el nombre del equipo");
        String nombreEquipo = br.readLine();
        System.out.println("Dime el nombre del patrocinador del equipo");
        String patrocinador = br.readLine();

        Equipo equipo = new Equipo(nombreEquipo,patrocinador);
        //session.persist(equipo);
        String sino = "";
        boolean primero = true;
        do {
            System.out.println("¿Deseas insertar un ciclista? (si/no)");
            sino = br.readLine();
            if (sino.equalsIgnoreCase("si")){
                Ciclista ciclista = insertarCiclista(equipo); //Creamos ciclista
                if (primero){ //Comprobamos si es el primer ciclista del equipo en la lista, ya q no hemos inicializado la lista en el constructor
                    Set<Ciclista> ciclistas = new HashSet<>(); //creamos la lista q tiene el equipo ya que si el equipo es nuevo no esta inicializda
                    ciclistas.add(ciclista);//añadimos el ciclista a la lista
                    equipo.setCiclistas(ciclistas);
                    primero = false;
                }else {
                    equipo.getCiclistas().add(ciclista); //Si no es el primero simplement lo añadimos
                }
            }
        }while(sino.equals("si"));

        session.persist(equipo);//Lo añadimos a la bd
      //  session.merge(equipo); //Actualizamos el equipo con los ciclistas q hemos hecho
    }
}