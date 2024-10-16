package proyecto.java.IMC;


public class Persona {
    private String nombre;
    private double altura; // en metros
    private double peso; // en kilogramos
    private double imc;

    public Persona(String nombre, double altura, double peso) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.imc = calcularIMC();
    }

    public String getNombre() {
        return nombre;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public double getImc() {
        return imc;
    }

    private double calcularIMC() {
        return peso / (altura * altura); // Fórmula del IMC
    }

    public String clasificarIMC() {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}
