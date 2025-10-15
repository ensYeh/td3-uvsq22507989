package fr.uvsq.cprog.collex.commandes;

// package fr.uvsq.cprog.dns.commandes;

import fr.uvsq.cprog.dns.Commande;

public class CommandeQuitter implements Commande {

   @Override
   public String execute() {
      System.out.println("Fermeture du programme...");
      System.exit(0);
      return "";
   }
}
