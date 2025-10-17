package fr.uvsq.cprog.collex.commandes;

import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.commandes.Commande;
import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;

public class CommandeRechercheNom implements Commande {
   private final Dns dns;
   private final String ip;

   public CommandeRechercheNom(Dns dns, String ip) {
      this.dns = dns;
      this.ip = ip;
   }

   @Override
   public String execute() {
      DnsItem item = dns.getItem(new AdresseIP(ip));
      if (item == null) {
         return "Aucun nom trouvé pour " + ip;
      }
      return item.getNom().toString();
   }
}
