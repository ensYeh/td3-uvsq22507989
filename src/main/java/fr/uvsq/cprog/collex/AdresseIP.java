package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {
    private final String ip;

    public AdresseIP(String ip) {
        if (ip == null || ip.isEmpty())
            throw new IllegalArgumentException("IP ne peut pas être vide");

        String[] parts = ip.split("\\.");
        if (parts.length != 4)
            throw new IllegalArgumentException("IP invalide : " + ip);

        for (String p : parts) {
            try {
                int val = Integer.parseInt(p);
                if (val < 0 || val > 255)
                    throw new IllegalArgumentException("IP invalide : " + ip);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("IP invalide : " + ip, e);
            }
        }
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AdresseIP))
            return false;
        AdresseIP adresseIP = (AdresseIP) o;
        return ip.equals(adresseIP.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public String toString() {
        return ip;
    }
}