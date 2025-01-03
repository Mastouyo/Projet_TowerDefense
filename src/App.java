public class App {
    public static void main(String[] args) throws Exception {
        
        /*Game g = new Game () ;
        g . launch () ;*/
        Player player = new Player();
        InterfaceJeu test = new InterfaceJeu(player);
        test.afficheJeu();
        
    }
}
    