package fr.uvsq.cprog.collex.commandes;

import fr.uvsq.cprog.collex.commandes.Commande;

public class CommandeQuitter implements Commande {

   @Override
   public String execute() {
      System.out.println("Fermeture du programme...");
      System.exit(0);
      return "";
   }
}
