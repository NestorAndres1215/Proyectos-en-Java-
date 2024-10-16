package proyecto.java.IMC;


import java.util.ArrayList;
import java.util.List;

public class Registro {
    private List<Persona> personas = new ArrayList<>();

    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public double calcularPromedioIMC() {
        if (personas.isEmpty()) return 0;
        double totalIMC = 0;
        for (Persona p : personas) {
            totalIMC += p.getImc();
        }
        return totalIMC / personas.size();
    }
}