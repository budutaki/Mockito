package ru.netology.sender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderTest {

    private static Stream<Arguments> argumentsForSendTest() {
        Stream<Arguments> of = Stream.of(
                Arguments.of("172.123.45.90", "Добро пожаловать"),
                Arguments.of("96.123.45.90", "Welcome")

        );
        return of;
    }

    @ParameterizedTest
    @MethodSource("argumentsForSendTest")

    public void sendTest(String ip, String expectedResult) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.123.45.90")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96.123.45.90")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);
        Assertions.assertEquals(result, expectedResult);

    }
}

