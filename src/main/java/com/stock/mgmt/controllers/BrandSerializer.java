package com.stock.mgmt.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stock.mgmt.entities.Brand;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class BrandSerializer extends JsonSerializer<Brand> {
    @Override
    public void serialize(Brand brand, JsonGenerator jsonGenerator,
                          SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", brand.getName());
        jsonGenerator.writeStringField("country", brand.getCountry());
        jsonGenerator.writeArrayFieldStart("products");
        brand.getProducts().forEach( p -> {
            try {
                jsonGenerator.writeString(p.getReference());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
