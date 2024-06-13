package com.elmehdikaalat.geoTourist.config;


import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<Point, String> {
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public String convertToDatabaseColumn(Point point) {
        if (point == null) {
            return null;
        }
        WKTWriter writer = new WKTWriter();
        return writer.write(point);
    }

    @Override
    public Point convertToEntityAttribute(String wkt) {
        if (wkt == null) {
            return null;
        }
        WKTReader reader = new WKTReader(geometryFactory);
        try {
            return (Point) reader.read(wkt);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert WKT to Point", e);
        }
    }
}

