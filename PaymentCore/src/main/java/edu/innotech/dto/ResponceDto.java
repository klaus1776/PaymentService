package edu.innotech.dto;

import java.util.List;

public record ResponceDto(String message, List<Product> products) {
}
