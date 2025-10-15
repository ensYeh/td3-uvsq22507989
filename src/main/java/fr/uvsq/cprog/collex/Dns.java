package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dns {
   private final Map<NomMachine, DnsItem> parNom = new HashMap<>();
   private final Map<AdresseIP, DnsItem> parIp = new HashMap<>();
   private final Path fichierBase;

   public Dns(Path fichierBase) throws IOException {
      this.fichierBase = fichierBase;
      chargerBase();
   }

   private void chargerBase() throws IOException {
      if (!Files.exists(fichierBase)) {
         Files.createFile(fichierBase);
      }
      List<String> lignes = Files.readAllLines(fichierBase);
      for (String ligne : lignes) {
         if (ligne.trim().isEmpty())
            continue;
         String[] parts = ligne.split(" ");
         NomMachine nom = new NomMachine(parts[0]);
         AdresseIP ip = new AdresseIP(parts[1]);
         DnsItem item = new DnsItem(ip, nom);
         parNom.put(nom, item);
         parIp.put(ip, item);
      }
   }

   public DnsItem getItem(NomMachine nom) {
      return parNom.get(nom);
   }

   public DnsItem getItem(AdresseIP ip) {
      return parIp.get(ip);
   }

   public List<DnsItem> getItems(String domaine, boolean parAdresse) {
      List<DnsItem> result = parNom.values().stream()
            .filter(item -> item.getNom().getDomaine().equals(domaine))
            .collect(Collectors.toList());
      result.sort(parAdresse ? Comparator.comparing(i -> i.getIp().getIp())
            : Comparator.comparing(i -> i.getNom().getNomQualifie()));
      return result;
   }

   public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
      if (parIp.containsKey(ip))
         throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
      if (parNom.containsKey(nom))
         throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
      DnsItem item = new DnsItem(ip, nom);
      parIp.put(ip, item);
      parNom.put(nom, item);
      sauvegarderBase();
   }

   private void sauvegarderBase() throws IOException {
      List<String> lignes = parNom.values().stream()
            .map(DnsItem::toString)
            .collect(Collectors.toList());
      Files.write(fichierBase, lignes, StandardOpenOption.TRUNCATE_EXISTING);
   }
}
