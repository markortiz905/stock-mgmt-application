package com.stock.mgmt.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stock.mgmt.entities.Product;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ProductSerializer extends JsonSerializer<Product> {
    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator,
                          SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("reference", product.getReference());
        jsonGenerator.writeStringField("brand", product.getBrand().getName());
        jsonGenerator.writeStringField("price", product.getPrice().toString());
        jsonGenerator.writeStringField("weight", product.getWeight().toString());
        jsonGenerator.writeStringField("volume", product.getVolume().toString());
        jsonGenerator.writeEndObject();
    }
}
