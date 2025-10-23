package LIS;

import java.util.Scanner;

public class Main {
    private static final int PRECIO_CAMISETA = 150;
    private static final int PRECIO_SOMBRERO = 350;
    private static final int PRECIO_TENIS = 600;
    public static String mostrarMenuPrincipal(int ticketsActuales) {
        return String.format(
            "\n==Administrador de billeteras para parques temáticos==\n" +
            "1. Agregar tickets\n" + 
            "2. Establecer tickets\n" + 
            "3. Comprar premio\n" + 
            "4. Establecer festivo\n" + 
            "5. Salir\n" + 
            "Tu billetera tiene %d boletos\n" +
            "Ingrese la opción: ", 
            ticketsActuales
        );
    }

    public static void comprarPremio(Scanner in, BilleteraParque billetera) {

        double descuento = BilleteraParque.getFestivo() ? 0.5 : 1.0;
        int precioCamiseta = (int) (PRECIO_CAMISETA * descuento);
        int precioSombrero = (int) (PRECIO_SOMBRERO * descuento);
        int precioTenis = (int) (PRECIO_TENIS * descuento);

        if (BilleteraParque.getFestivo()) {
            System.out.println("¡Precios de vacaciones!"); 
        }
        
        System.out.println("1. Camiseta (" + precioCamiseta + " tickets)");
        System.out.println("2. Sombrero (" + precioSombrero + " tickets)");
        System.out.println("3. Tenis (" + precioTenis + " tickets)");
        System.out.print("¿Qué premio desea comprar? ");

        if (!in.hasNextInt()) {
            System.out.println("Entrada inválida. Regresando al menú principal.");
            in.next();
            return;
        }

        int opcionPremio = in.nextInt();
        int costo = 0;
        String nombrePremio = "";

        switch (opcionPremio) {
            case 1: costo = precioCamiseta; nombrePremio = "una camiseta"; break;
            case 2: costo = precioSombrero; nombrePremio = "un sombrero"; break;
            case 3: costo = precioTenis; nombrePremio = "unos tenis"; break;
            default:
                System.out.println("Premio no válido.");
                return;
        }

        if (billetera.removerTickets(costo)) {
            System.out.println("Compré " + nombrePremio + " por " + costo + " entradas"); 
        } else {
            System.out.println("No hay suficientes boletos para comprar " + nombrePremio);
        }
    }

    public static void main(String[] args) { 

        BilleteraParque billetera = new BilleteraParque(100); 
        Scanner in = new Scanner(System.in); 
        int opc = 0; 
        boolean salir = false; 

        do {
        
            System.out.print(mostrarMenuPrincipal(billetera.getTickets())); 
            
            if (in.hasNextInt()) {
                opc = in.nextInt();
            } else {
                System.out.println("Entrada inválida. Ingresa un nuevo número.");
                in.next();
                opc = 0;
                continue; 
            }

            switch (opc) {
                case 1:

                    System.out.print("¿Cuántos boletos se agregan? ");
                    if (in.hasNextInt()) {
                        int cantidad = in.nextInt();
                        billetera.agregarTickets(cantidad);
                    } else {
                        System.out.println("Entrada no numérica ignorada.");
                        in.next();
                    }
                    break;
                case 2:

                    System.out.print("Establecer saldo de tickets en: ");
                    if (in.hasNextInt()) {
                        int cantidad = in.nextInt();
                        billetera.setTickets(cantidad); 
                    } else {
                        System.out.println("Entrada no numérica ignorada.");
                        in.next();
                    }
                    break;
                case 3:

                    comprarPremio(in, billetera);
                    break;
                case 4:

                    BilleteraParque.establecerFestivo();
                    if (BilleteraParque.getFestivo()) {
                        System.out.println("Ahora es un día festivo.");
                    } else {
                        System.out.println("Ya no es un día festivo.");
                    }
                    break;
                case 5:
                
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 5.");
            }

        } while (!salir);
        
        in.close();
    }
}