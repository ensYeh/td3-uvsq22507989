package fr.uvsq.cprog.collex.commandes;

import fr.uvsq.cprog.collex.commandes.Commande;
import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;
import fr.uvsq.cprog.collex.NomMachine;

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
      return item.getIp().toString();
   }
}
