package fr.uvsq.cprog.collex.commandes;

import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;

import fr.uvsq.cprog.collex.commandes.Commande;

public class CommandeAddItem implements Commande {
   private final Dns dns;
   private final String ip;
   private final String nom;

   public CommandeAddItem(Dns dns, String ip, String nom) {
      this.dns = dns;
      this.ip = ip;
      this.nom = nom;
   }

   @Override
   public String execute() {
      try {
         dns.addItem(new AdresseIP(ip), new NomMachine(nom));
         return "Ajout réussi : " + nom + " → " + ip;
      } catch (Exception e) {
         return "ERREUR : " + e.getMessage();
      }
   }
}
