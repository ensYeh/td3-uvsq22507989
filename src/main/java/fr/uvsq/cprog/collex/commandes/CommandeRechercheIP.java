package fr.uvsq.cprog.collex.commandes;

// package fr.uvsq.cprog.dns.commandes;

import fr.uvsq.cprog.dns.Commande;
import fr.uvsq.cprog.dns.Dns;
import fr.uvsq.cprog.dns.DnsItem;
import fr.uvsq.cprog.dns.NomMachine;

public class CommandeRechercheIP implements Commande {
   private final Dns dns;
   private final String nom;

   public CommandeRechercheIP(Dns dns, String nom) {
      this.dns = dns;
      this.nom = nom;
   }

   @Override
   public String execute() {
      DnsItem item = dns.getItem(new NomMachine(nom));
      if (item == null) {
         return "Aucune adresse trouvée pour " + nom;
      }
      return item.getAdresseIP().toString();
   }
}
