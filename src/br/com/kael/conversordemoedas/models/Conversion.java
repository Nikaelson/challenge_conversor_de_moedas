package br.com.kael.conversordemoedas.models;

public record Conversion(String base_code, String target_code, double conversion_result) {
}
