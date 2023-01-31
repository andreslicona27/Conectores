public class Alumno {
    private String nombre;
    private String apellidos;
    private int altura;
    private int aula;

    // BUILDER 
    public Alumno(String nombre, String apellidos, int altura, int aula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.aula = aula;
    }

    // SETTERS AND GETTERS
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public int getAula() {
        return aula;
    }
    public void setAula(int aula) {
        this.aula = aula;
    }
    
}
