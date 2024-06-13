package com.elmehdikaalat.geoTourist.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.ParseException;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {
    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        WKTReader reader = new WKTReader(new GeometryFactory());
        try {
            return (Point) reader.read(p.getText());
        } catch (ParseException e) {
            throw new RuntimeException("Failed to deserialize WKT to Point", e);
        }
    }
}


