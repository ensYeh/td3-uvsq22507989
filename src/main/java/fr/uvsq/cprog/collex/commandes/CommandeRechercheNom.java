package fr.uvsq.cprog.collex.commandes;

// package fr.uvsq.cprog.dns.commandes;

import fr.uvsq.cprog.dns.AdresseIP;
import fr.uvsq.cprog.dns.Commande;
import fr.uvsq.cprog.dns.Dns;
import fr.uvsq.cprog.dns.DnsItem;

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
      return item.getNomMachine().toString();
   }
}
