package com.example.demo.dto;

import lombok.Builder;

@Builder
public record ProductDto(Integer recId, String ProductName, Integer updatedBy, java.util.Date updatedOn,Integer price) {
	
}
