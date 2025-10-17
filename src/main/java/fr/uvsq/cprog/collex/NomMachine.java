package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {
   private final String nomQualifie;

   public NomMachine(String nomQualifie) {
      if (!nomQualifie.matches("^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$"))
         throw new IllegalArgumentException("Nom machine invalide : " + nomQualifie);
      this.nomQualifie = nomQualifie;
   }

   public String getNom() {
      return nomQualifie.split("\\.")[0];
   }

   public String getDomaine() {
      return nomQualifie.substring(nomQualifie.indexOf('.') + 1);
   }

   public String getNomQualifie() {
      return nomQualifie;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (!(o instanceof NomMachine))
         return false;
      NomMachine that = (NomMachine) o;
      return nomQualifie.equals(that.nomQualifie);
   }

   @Override
   public int hashCode() {
      return Objects.hash(nomQualifie);
   }

   @Override
   public String toString() {
      return nomQualifie;
   }
}
