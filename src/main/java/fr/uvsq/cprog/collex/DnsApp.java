package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Paths;

// package fr.uvsq.cprog.dns;

// import fr.uvsq.cprog.collex.commandes.*;
import fr.uvsq.cprog.collex.commandes.Commande;
import java.util.Scanner;

public class DnsApp {

   public static void main(String[] args) throws IOException {
      // Chargement du fichier à partir du fichier de configuration
      // Dns dns = new Dns("src/main/resources/data/dns.txt");
      Dns dns = new Dns(Paths.get("src/main/resources/data/dns.txt"));

      DnsTUI tui = new DnsTUI(dns);

      System.out.println("=== Simulation DNS ===");
      System.out.println("Entrez une commande (ou 'quit' pour quitter).");

      Scanner sc = new Scanner(System.in);
      boolean continuer = true;

      while (continuer) {
         System.out.print("> ");
         String saisie = sc.nextLine();
         try {
            Commande commande = tui.nextCommande(saisie);
            if (commande != null) {
               String resultat = commande.execute();
               if (resultat != null && !resultat.isEmpty()) {
                  tui.affiche(resultat);
               }
               // Si la commande demande à quitter :
               if ("quit".equalsIgnoreCase(saisie.trim())) {
                  continuer = false;
               }
            }
         } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
         }
      }

      System.out.println("Fermeture du programme DNS.");
      sc.close();
   }
}
