package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceTest {

    @ParameterizedTest
    private static Stream<Arguments> argumentForByIpTest() {
        Stream<Arguments> of = Stream.of(
                Arguments.of("172.0.32.11", Country.RUSSIA),
                Arguments.of("96.44.183.149", Country.USA),
                Arguments.of("96.123.45.90", Country.USA),
                Arguments.of("172.87.90.2", Country.RUSSIA),
                Arguments.of("127.0.0.1", null)

        );
        return of;
    }

    @ParameterizedTest
    private static Stream<Arguments> argumentsForByCoordinatesTest() {
        Stream<Arguments> of = Stream.of(
                Arguments.of(134.6, 144, 8),
                Arguments.of(1, 4, 788, 1),
                Arguments.of(1, 56)

        );
        return of;
    }

    @ParameterizedTest
    @MethodSource("argumentForByIpTest")
    public void ByIpTest(String ip, Country expectedResult) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country result = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(result, expectedResult);

    }

    @ParameterizedTest
    @MethodSource("argumentsForByCoordinatesTest")
    public void byCoordinatesTest(double latitude, double longitude) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude),
                "implemented");

    }
}