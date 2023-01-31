import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

    import org.mariadb.jdbc.Driver;
import org.mariadb.jdbc.client.result.ResultSetMetaData;


public class Ejercicios {
    JDBC base = new JDBC();

    /**
     * * EJERCICIO 1
     * * método que permita consultar alumnos que contengan una cadena de caracteres
     * * en su nombre
     * * Además deberá visualizar el número de resultados obtenidos
     * 
     */
    public void Ejercicio1() {
        int numeroDeResultados = 0;
        base.conectar("add", "localhost", "root", "");

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = "select nombre from alumnos where nombre like '%'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                numeroDeResultados++;
            }

            System.out.println("El número de resultados es: " + numeroDeResultados);
        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 2 PARTE 1
     * * Dar de alta alumnos
     * 
     * @param alumnos array de todos los alumnos que hay que agregar
     */
    public void Ejercicio2_InsertarAlumnos(Alumno[] alumnos) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            int filasAfectadas = 0;
            for (int i = 0; i < alumnos.length; i++) {
                String query = "INSERT INTO alumnos VALUES (null,'" + alumnos[i].getNombre() + "', '"
                        + alumnos[i].getApellidos() + "','" + alumnos[i].getAltura() + "', '" + alumnos[i].getAula()
                        + "' )";
                filasAfectadas = sta.executeUpdate(query);
                System.out.println("Filas insertadas: " + filasAfectadas);
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 2 PARTE 2
     * * Dar de alta asignaturas
     * 
     * @param cod        codigo de la asignatura
     * @param asignatura nombre de la asignatura
     */
    public void Ejercicio2_InsertarAsignatura(int cod, String asignatura) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            String query = "INSERT INTO asignaturas values ('" + cod + "', '" + asignatura + "')";

            int filasAfectadas = sta.executeUpdate(query);
            System.out.println("Filas insertadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 3 PARTE 1
     * * Dar de baja un alumno
     * 
     * @param nombre nombre del alumno que desea eliminar
     */
    public void Ejercicio3_EliminarAlumno(int codigo) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            String query = String.format("DELETE FROM alumnos WHERE codigo=%d", codigo);

            int filasAfectadas = sta.executeUpdate(query);
            System.out.println("Filas eliminadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 3 PARTE 2
     * * Dar de baja una asignatura
     * 
     * @param nombre nombre del alumno que desea eliminar
     */
    public void Ejercicio3_EliminarAsignatura(int codigo) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            String query = String.format("DELETE FROM asignaturas WHERE cod=%d", codigo);

            int filasAfectadas = sta.executeUpdate(query);
            System.out.println("Filas eliminadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 4 PARTE 1
     * * Modificación de uno de los alumnos
     * 
     * @param alumno alumno al que se le van a cambiar los datos
     * @param codigo codigo del alumnos al que se le desean cambiar los datos
     */
    public void Ejercicio4_ModificarAlumnos(Alumno alumno, int codigo) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            String query = String.format(
                    "UPDATE alumnos SET nombre='%s', apellidos='%s', altura=%d, aula=%d WHERE codigo=" + codigo,
                    alumno.getNombre(), alumno.getApellidos(), alumno.getAltura(), alumno.getAula());

            System.out.println(query);
            int filasAfectadas = sta.executeUpdate(query);
            System.out.println("Filas eliminadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 4 PARTE 2
     * * Modificación de el nombre de una de las asignaturas
     * 
     * @param nombre nombre que se le asignara a la nueva asignatura
     * @param codigo codigo de la asignatura a la que se le desea cambiar el nombre
     */
    public void Ejercicio4_ModificarAsignatura(String nombre, int codigo) {
        base.conectar("add", "localhost", "root", "");

        try (Statement sta = this.base.conexion.createStatement()) {
            String query = String.format("UPDATE alumnos SET nombre='%s' WHERE cod=" + codigo, nombre);

            System.out.println(query);
            int filasAfectadas = sta.executeUpdate(query);
            System.out.println("Filas eliminadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 5 PARTE 1
     * * Mostrar el nombre de las aulas en las que se encuentran alumnos
     */
    public void Ejercicio5_Aulas() {
        base.conectar("add", "localhost", "root", "");

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = "SELECT aulas.nombreAula FROM aulas JOIN alumnos ON aulas.numero = alumnos.aula GROUP BY alumnos.aula";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nombreAula"));
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 5 PARTE 2
     * * Nombre de los alumnos, de las asignaturas y notas de aquellos alumnos que
     * han aprobado alguna asignatura
     */
    public void Ejercicio5_Datos() {
        base.conectar("add", "localhost", "root", "");

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = "SELECT alumnos.nombre, asignaturas.nombre, notas.nota FROM alumnos JOIN notas ON alumnos.codigo = notas.alumno JOIN asignaturas ON asignaturas.cod = notas.asignatura WHERE notas.nota >= 5";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(String.format("%15s %40s %5s", rs.getString("alumnos.nombre"),
                        rs.getString("asignaturas.nombre"), rs.getInt("nota")));
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 5 PARTE 3
     * * Mostrar nombre de las asignaturas sin alumnos
     */
    public void Ejercicio5_Asignaturas() {
        base.conectar("add", "localhost", "root", "");

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = "SELECT nombre FROM asignaturas WHERE cod NOT IN (SELECT notas.asignatura FROM notas)";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /***
     * * EJERCICIO 6 PARTE 1 (no preparada)
     * * Realizar un método que consulte que el nombre de un alumno contenga cierto
     * patrón y que la altura
     * * sea mayor que un valor
     * 
     * @param nombre nombre al que deberá compara las consultas
     * @param patron patron del que debe buscar el nombre
     * @param altura altura que debe superar los alumnos de la consulta
     */
    public void Ejercicio6(String nombre, String patron, int altura) {
        base.conectar("add", "localhost", "root", "");

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = String.format("SELECT nombre FROM alumnos WHERE nombre LIKE '%s' and altura >%d", patron,
                    altura);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (nombre.equals(rs.getString("nombre"))) {
                    System.out.println(
                            "El nombre con el patron: " + patron + " y una altura mayor que " + altura + " si existe.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    public PreparedStatement ps = null;

    /**
     * * EJERICCIO 6 (preparada)
     * 
     * @param patron
     * @param altura
     */
    public void Ejercicio6_Preparada(String patron, int altura) {
        base.conectar("add", "localhost", "root", "");

        try {
            String query = "select nombre from alumnos where nombre like ? and altura > ?";
            if (this.ps == null) {
                this.ps = this.base.conexion.prepareStatement(query);
            }
            ps.setString(1, patron);
            ps.setInt(2, altura);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {
                System.out.println(resu.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("error: " + e.getSQLState());
        }

    }

    /**
     * * EJERCICIO 8
     * * Crear una función que agregue una columna en la tabla
     * 
     * @param tabla       tabla en la que se agregara la columna
     * @param columna     el nombre de la columna que se va a agregar
     * @param tipoDeDato  el tipo de dato que será la columna
     * @param propiedades propiedades que llevará la el tipo de dato
     */
    public void Ejercicio8(String tabla, String columna, String tipoDeDato, String[] propiedades) {
        base.conectar("add", "localhost", "root", "");

        String propiedad = "";
        for (String p : propiedades) {
            propiedad += p + " ";
        }

        try (Statement stmt = this.base.conexion.createStatement()) {
            String query = String.format("ALTER TABLE %s ADD COLUMN %s %s %s", tabla, columna, tipoDeDato, propiedad);
            ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
        } finally {
            base.CerrarConexion();
        }
    }

    /**
     * * EJERCICIO 9 PARTE A
     * * Obtención de distintos datos de el driver
     */
    public void Ejercicio9_A() {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();

            System.out.println("Nombre del dirver: " + dbmd.getDriverName());
            System.out.println("Version del driver: " + dbmd.getDriverVersion());
            System.out.println("Conección URL: " + dbmd.getURL());
            System.out.println("Usuario : " + dbmd.getUserName());
            System.out.println("Nombre del SGBD: " + dbmd.getDatabaseProductName());
            System.out.println("Version del SGBD: " + dbmd.getDatabaseProductVersion());

            System.out.println("Palabras reservadas:");
            String[] reservedWords = dbmd.getSQLKeywords().split(",");
            for (String reservedWord : reservedWords) {
                System.out.println(" - " + reservedWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * * EJERCICIO 9 PARTE B
     * * Mostrar todos los catalogs de una base de datos
     */
    public void Ejercicio9_B() {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();

            ResultSet rs = dbmd.getCatalogs();
            while (rs.next()) {
                System.out.println("Catalogs: " + rs.getString("TABLE_CAT"));
            }
            this.base.conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * EJERCICIO 9 PARTE C
     * * Mostrar todas las tablas de la base de datos con el tipo de tabla que es
     * cada una
     */
    public void Ejercicio9_C() {
        DatabaseMetaData dbmt;
        ResultSet tablas;
        base.conectar("add", "localhost", "root", "");

        try {
            dbmt = this.base.conexion.getMetaData();
            tablas = dbmt.getTables("add", null, null, null);
            while (tablas.next()) {
                System.out.println(
                        String.format("%s %s", tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));

            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
        }
    }

    /**
     * * EJERCICIO 9 PARTE D
     * * Mostrar todas las vistas de la base de datos
     */
    public void Ejercicio9_D() {
        DatabaseMetaData dbmt;
        ResultSet vistas;
        base.conectar("add", "localhost", "root", "");

        try {
            dbmt = this.base.conexion.getMetaData();
            vistas = dbmt.getTables("add", null, "%", new String[] { "VIEW" });

            while (vistas.next()) {
                System.out.println(
                        String.format("Vista %s", vistas.getString("TABLE_NAME")));

            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
        }
    }

    /**
     * * EJERCICIO 9 PARTE E
     * * Combinación de los apartados b y c
     * * mostrando todos los catalogs y todas las tablas
     */
    public void Ejercicio9_E() {
        DatabaseMetaData dbmt;
        base.conectar("add", "localhost", "root", "");

        try {
            dbmt = this.base.conexion.getMetaData();

            ResultSet catalogs = dbmt.getCatalogs();
            ResultSet tablas = dbmt.getTables(null, null, "%", null);
            while (catalogs.next()) {
                System.out.println("Catalogs: " + catalogs.getString("TABLE_CAT"));
            }
            while (tablas.next()) {
                System.out.println(
                        String.format("Tablas: %s %s", tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo datos " + e.getLocalizedMessage());
        }
    }

    /**
     * * EJERCICIO 9 PARTE F
     * * Muestra los procedimiento d la base de datos
     * 
     * @param bd base de datos de la que se mostraran los procedimientos
     */
    public void Ejercicio9_F(String bd) {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();

            ResultSet rs = dbmd.getProcedures(bd, null, "%");
            while (rs.next()) {
                System.out.println("Procedimiento: " + rs.getString("PROCEDURE_NAME"));
            }
            this.base.conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * EJERCICIO 9 PARTE G
     * * Consulta datos de las tablas que comienzan por a de la base de datos ADD
     */
    public void Ejercicio9_G() {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" });

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");

                if (tableName.startsWith("a")) {
                    ResultSet columns = dbmd.getColumns("add", null, tableName, "%");

                    while (columns.next()) {
                        int pos = columns.getInt("ORDINAL_POSITION");
                        String dbNombre = columns.getString("TABLE_CAT");
                        String tbNombre = columns.getString("TABLE_NAME");
                        String colNombre = columns.getString("COLUMN_NAME");
                        String colTipo = columns.getString("TYPE_NAME");
                        int tamaño = columns.getInt("COLUMN_SIZE");
                        int nullable = columns.getInt("NULLABLE");
                        System.out.println(String.format(
                                "\nposicion: %s \nbd: %s \ntabla: %s \ncolumna: %s \ntipo de dato de la columna: %s \nTamaño: %d \nEs nullabel: %b",
                                pos, dbNombre, tbNombre, colNombre, colTipo, tamaño,
                                (nullable == DatabaseMetaData.columnNullable)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * EJERCICIO 9 PARTE H
     * * Muestra las claves primarias y foraneas que existen en las tablas
     */
    public void Ejercicio9_H() {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();

            ResultSet rs = dbmd.getPrimaryKeys("add", null, "%");
            ResultSet rs2 = dbmd.getExportedKeys("add", null, "%");
            while (rs.next()) {
                System.out.println(String.format("Clave Primaria: %s ", rs.getString("COLUMN_NAME")));
            }
            while (rs2.next()) {
                System.out.println(String.format("Clave Foranea: %s ", rs2.getString("FKCOLUMN_NAME")));
            }

            this.base.conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * * EJERCICIO 10
     * * Muestra datos sobre los resultados de la consulta "select *, nombre as non
     * from alumnos"
     */
    public void Ejercicio10() {
        try {
            base.conectar("add", "localhost", "root", "");
            Statement stmt = this.base.conexion.createStatement();

            ResultSet rs = stmt.executeQuery("select *, nombre as non from alumnos");

            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Columna: " + metaData.getColumnName(i));
                System.out.println("Alias: " + metaData.getColumnLabel(i));
                System.out.println("Tipo de Dato: " + metaData.getColumnTypeName(i));
                System.out.println("Auto Incremento: " + metaData.isAutoIncrement(i));
                System.out.println("Permite Nulos: " + (metaData.isNullable(i) == 1 ? "false" : "true").toString());
                System.out.println();
            }

        } catch (SQLException e) {
            e.getSQLState();
        }
    }

    public void Ejercicio11() {
        try {
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();
            // ResultSet resultSet = dbmd.getDrivers();

            // while (resultSet.next()) {
            //   String driverName = resultSet.getString("DriverName");
            //   System.out.println(driverName);
            // }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Ejercicio13(){
        try{
            base.conectar("add", "localhost", "root", "");
            DatabaseMetaData dbmd = this.base.conexion.getMetaData();

            

        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Ejercicios ejer = new Ejercicios();

        // Class.forName("com.mysql.jdbc.Driver");
        // * EJERCICIO 1*/
        // ejer.Ejercicio1();

        // * EJERCICIO 2*/
        // Alumno primer = new Alumno("Juan", "Alvarado", 190, 20);
        // Alumno segundo = new Alumno("Valeria", "Rivera", 150, 21);
        // Alumno[] vAlumnos = { primer, segundo };

        // ejer.Ejercicio2_InsertarAlumnos(vAlumnos);

        // ejer.Ejercicio2_InsertarAsignatura(10, "mates");

        // * EJERCICIO 3*/
        // ejer.Ejercicio3_EliminarAlumno("Juan");
        // ejer.Ejercicio3_EliminarAlumno("Valeria");

        // ejer.Ejercicio3_EliminarAsignatura("mates");

        // * EJERCICIO 4*/
        // Alumno prueba = new Alumno("Julio", "Cortazar", 139, 21);
        // ejer.Ejercicio4_ModificarAlumnos(prueba, 9);

        // ejer.Ejercicio4_ModificarAsignatura("fisica", 4);

        // * EJERCICIO 5*/
        // ejer.Ejercicio5_Aulas();
        // ejer.Ejercicio5_Datos();
        // ejer.Ejercicio5_Asignaturas();

        // * EJERCICIO 6 */
        // ejer.Ejercicio6("Ph%l", 180);
        // ejer.Ejercicio6_Preparada("P%", 170);

        // * EJERCICIO 8 */
        // String[] propiedades = { "no null" };
        // ejer.Ejercicio8("alumnos", "años", "int", propiedades);

        // * EJERCICIO 9 */
        // ejer.Ejercicio9_A();
        // ejer.Ejercicio9_B();
        // ejer.Ejercicio9_C();
        // ejer.Ejercicio9_D();
        // ejer.Ejercicio9_E();
        // ejer.Ejercicio9_F("add");
        // ejer.Ejercicio9_G();
        // ejer.Ejercicio9_H();

        // * EJERCICIO 10 */
        // ejer.Ejercicio10();

        // * EJERCICIO 11 */
        ejer.Ejercicio11();
    }
}
