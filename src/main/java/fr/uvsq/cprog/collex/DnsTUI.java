package fr.uvsq.cprog.collex;

import java.util.Scanner;

import fr.uvsq.cprog.collex.commandes.Commande;
import fr.uvsq.cprog.collex.commandes.CommandeQuitter;
import fr.uvsq.cprog.collex.commandes.CommandeAddItem;
import fr.uvsq.cprog.collex.commandes.CommandeListeDomaine;
import fr.uvsq.cprog.collex.commandes.CommandeRechercheNom;
import fr.uvsq.cprog.collex.commandes.CommandeRechercheIP;

public class DnsTUI {
   private final Dns dns;
   private final Scanner scanner;

   private static final String REGEX_IP = "^[0-9]{1,3}(\\.[0-9]{1,3}){3}$";
   private static final String REGEX_FQDN = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";

   public DnsTUI(Dns dns) {
      this.dns = dns;
      this.scanner = new Scanner(System.in);
   }

   public String lireLigne() {
      System.out.print("> ");
      return scanner.nextLine().trim();
   }

   public Commande nextCommande(String saisie) {
      if (saisie == null || saisie.isEmpty()) {
         return () -> "Commande invalide";
      }
      String[] tokens = saisie.trim().split("\\s+");
      String cmd = tokens[0];

      // quitter
      if (cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit")) {
         return new CommandeQuitter();
      }

      // add <ip> <nom.qualifie>
      if (cmd.equals("add")) {
         if (tokens.length != 3)
            return () -> "ERREUR : Usage : add <ip> <nom.qualifie>";
         return new CommandeAddItem(dns, tokens[1], tokens[2]);
      }

      // ls [-a] <domaine>
      if (cmd.equals("ls")) {
         boolean triParAdresse = false;
         String domaine = null;
         if (tokens.length == 2) {
            domaine = tokens[1];
         } else if (tokens.length == 3 && tokens[1].equals("-a")) {
            triParAdresse = true;
            domaine = tokens[2];
         } else {
            return () -> "ERREUR : Usage : ls [-a] <domaine>";
         }
         if (!domaine.matches(REGEX_FQDN))
            return () -> "ERREUR : Domaine invalide";
         return new CommandeListeDomaine(dns, domaine, triParAdresse);
      }

      // ligne seule : IP -> nom
      if (saisie.matches(REGEX_IP)) {
         return new CommandeRechercheNom(dns, saisie);
      }

      // ligne seule : nom -> IP
      if (saisie.matches(REGEX_FQDN)) {
         return new CommandeRechercheIP(dns, saisie);
      }

      return () -> "Commande invalide";
   }

   public void affiche(String texte) {
      if (texte != null && !texte.isBlank()) {
         System.out.println(texte);
      }
   }
}
