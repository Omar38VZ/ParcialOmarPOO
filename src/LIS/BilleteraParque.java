package LIS;

public class BilleteraParque {
    
    private int tickets; 
    private static boolean festivo = false; 

    public BilleteraParque() {
        this.tickets = 0;
    }

    public BilleteraParque (int tickets) { 
        this.tickets = tickets;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        if (tickets >= 0) {
            this.tickets = tickets;
        }
    }

    public static boolean getFestivo() {
        return festivo;
    }
    
    public static void establecerFestivo() {
        festivo = !festivo;
    } 

    public void agregarTickets (int cantidad){
        if (cantidad >= 0) {
            this.tickets += cantidad;
        } 
    } 
 
    public boolean removerTickets (int cantidad){
        if (cantidad < 0) {
            return false;
        }
        
        if (this.tickets >= cantidad) {
            this.tickets -= cantidad;
            return true;
        }
    
        return false;
    }
}