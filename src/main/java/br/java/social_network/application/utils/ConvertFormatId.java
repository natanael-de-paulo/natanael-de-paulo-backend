package br.java.social_network.application.utils;

import java.util.UUID;

public class ConvertFormatId {
    public static String toString(UUID id){
        return id.toString();
    }

    public static UUID toUUID(String id){
        return UUID.fromString(id);
    }
}
