package extintoresfire1.BD;

import Datos.Conexion;
import Datos.ConexionAbstracta;
import Datos.ConexionPostgres;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConexionBD {

    static Thread hiloInternet, hiloTimer;
    static long tiempoCalculoEntrada=4000;
    static boolean ok = true;

    public static void ConexionInternet() {
        FileInputStream in = null;
        try {
            Properties configInternet = new Properties();
            in = new FileInputStream("config.internet.properties");
            configInternet.load(in);
            in.close();

            ConexionAbstracta conexionInternet = new ConexionPostgres(
                    configInternet.getProperty("base_datos"),
                    configInternet.getProperty("servidor"),
                    Integer.parseInt(configInternet.getProperty("puerto")),
                    configInternet.getProperty("usuario"),
                    configInternet.getProperty("clave")
            );
            conexionInternet.Conectar();
            Conexion.conexionI = conexionInternet;
            if (conexionInternet.HayError()) {
                System.out.println(conexionInternet.getDescripcion());
            }
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static void ConexionLocal() {
        FileInputStream in = null;
        try {
            Properties configLocal = new Properties();
            in = new FileInputStream("config.local.properties");
            configLocal.load(in);
            in.close();

            ConexionAbstracta conexionLocal = new ConexionPostgres(
                    configLocal.getProperty("base_datos"),
                    configLocal.getProperty("servidor"),
                    Integer.parseInt(configLocal.getProperty("puerto")),
                    configLocal.getProperty("usuario"),
                    configLocal.getProperty("clave")
            );
            conexionLocal.Conectar();
            Conexion.conexionL = conexionLocal;
            if (conexionLocal.HayError()) {
                System.out.println(conexionLocal.getDescripcion());
            }
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static void ConexionUnida() {
        hiloInternet = new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream in = null;
                try {
                    Properties configInternet = new Properties();
                    in = new FileInputStream("config.internet.properties");
                    configInternet.load(in);
                    in.close();

                    ConexionAbstracta conexionInternet = new ConexionPostgres(
                            configInternet.getProperty("base_datos"),
                            configInternet.getProperty("servidor"),
                            Integer.parseInt(configInternet.getProperty("puerto")),
                            configInternet.getProperty("usuario"),
                            configInternet.getProperty("clave")
                    );
                    conexionInternet.Conectar();
                    if (!ok) {
                        return;
                    }
                    Conexion.conexionI = conexionInternet;
                    if (conexionInternet.HayError()) {
                        System.out.println("FIN-NO");
                        System.out.println(conexionInternet.getDescripcion());
                        ok = false;
                        ConexionLocal();
                        return;
                    }
                    hiloTimer.interrupt();
                    System.out.println("FIN-SIRVIÓ");
                } catch (IOException | NumberFormatException ex) {
                    System.err.println(ex.getMessage());
                    try {
                        in.close();
                    } catch (Exception e) {
                    }
                }
            }
        });
        hiloTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hiloTimer.sleep(tiempoCalculoEntrada);
                    if (!ok) {
                        return;
                    }
                    System.out.println("Se acabó");
                    ConexionLocal();
                    ok = false;
                    hiloTimer.interrupt();
                } catch (InterruptedException ex) {
                }
            }
        });
        
        hiloInternet.start();
        hiloTimer.start();

    }

}
