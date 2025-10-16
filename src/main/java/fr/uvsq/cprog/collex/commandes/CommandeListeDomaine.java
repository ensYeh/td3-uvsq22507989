package fr.uvsq.cprog.collex.commandes;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;
import fr.uvsq.cprog.collex.commandes.Commande; // ← bon import
import java.util.List;

public class CommandeListeDomaine implements Commande {
   private final Dns dns;
   private final String domaine;
   private final boolean triParAdresse;

   public CommandeListeDomaine(Dns dns, String domaine, boolean triParAdresse) {
      this.dns = dns;
      this.domaine = domaine;
      this.triParAdresse = triParAdresse;
   }

   @Override
   public String execute() {
      List<DnsItem> items = dns.getItems(domaine, triParAdresse);
      if (items.isEmpty()) {
         return "Aucune machine trouvée pour le domaine " + domaine;
      }

      StringBuilder sb = new StringBuilder();
      for (DnsItem item : items) {
         sb.append(item.getIp())
               .append(" ")
               .append(item.getNom())
               .append("\n");
      }
      return sb.toString().trim();
   }
}
