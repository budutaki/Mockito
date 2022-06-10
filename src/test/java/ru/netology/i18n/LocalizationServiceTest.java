package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.stream.Stream;

public class LocalizationServiceTest {

    private static Stream<Arguments> methodSource() {
        Stream<Arguments> of = Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.GERMANY, "Welcome")

        );
        return of;
    }

    @ParameterizedTest
    @MethodSource("methodSource")
    public void LocaleTest(Country country, String expectedResult) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        Assertions.assertEquals(result, expectedResult);

    }
}
