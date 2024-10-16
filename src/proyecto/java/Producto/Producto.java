public class Producto {
    private String nombre;
    private double precio;
    private boolean conIGV;
    private double descuento;
    private int cantidad;

    public Producto(String nombre, double precio, boolean conIGV, double descuento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.conIGV = conIGV;
        this.descuento = descuento;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public boolean isConIGV() {
        return conIGV;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioFinal() {
        double precioConDescuento = precio - (precio * descuento / 100);
        double precioTotal = precioConDescuento * cantidad;
        if (conIGV) {
            return precioTotal * 1.18; // Aplicar IGV del 18%
        } else {
            return precioTotal;
        }
    }
}