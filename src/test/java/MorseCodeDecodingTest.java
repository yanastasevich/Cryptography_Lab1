import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorseCodeDecodingTest {

    //TODO: result plaintext produces null, debug
    @ParameterizedTest
    @ValueSource(strings = {". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. ."})
    void decryptMorseCodeInEnglishWithoutSpaceInTheEnd(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode);
        String expectedPlaintext = "everything is pointless there is no meaning to life";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }
}