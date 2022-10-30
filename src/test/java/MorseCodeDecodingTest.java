import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorseCodeDecodingTest {

    static Map<Character, String> morseCodeMap = new HashMap<>();

    @BeforeAll
    static void fillTheMap() {
        MorseCodeDecoding.fillMorseCodeMap(morseCodeMap);
    }

    @ParameterizedTest
    @ValueSource(strings = {". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. ."})
    void decryptMorseCodeInEnglishWithoutSpaceInTheEnd(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode, morseCodeMap, false);
        String expectedPlaintext = "everything is pointless there is no meaning to life";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"everything is pointless there is no meaning to life"})
    void encryptMorseCodeInEnglishWithoutSpaceInTheEnd(String plainText) {
        String actualMorseCode = MorseCodeDecoding.encrypt(plainText, morseCodeMap);
        String expectedMorseCode = ". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. .";

        assertEquals(expectedMorseCode, actualMorseCode,
                "The encoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. ."})
    void encryptAndDecryptMorseCodeInEnglishWithoutSpaceInTheEnd(String morseCode) {
        String cipherText = MorseCodeDecoding.encrypt("everything is pointless there is no meaning to life".toUpperCase(), morseCodeMap);
        String resultPlaintext = MorseCodeDecoding.decrypt(cipherText, morseCodeMap, false);

        String expectedPlaintext = "everything is pointless there is no meaning to life";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. . "})
    void decryptMorseCodeInEnglishWithSpaceInTheEnd(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode, morseCodeMap, false);
        String expectedPlaintext = "everything is pointless there is no meaning to life";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"everything is pointless there is no meaning to life "})
    void encryptMorseCodeInEnglishWithSpaceInTheEnd(String plainText) {
        String actualMorseCode = MorseCodeDecoding.encrypt(plainText, morseCodeMap);
        String expectedMorseCode = ". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. .";

        assertEquals(expectedMorseCode, actualMorseCode,
                "The encoding of Morse code is wrong.");
    }

    // not working test case
    @ParameterizedTest
    @ValueSource(strings = {"everything is pointless. There, +is, no, meaning to !life!"})
    void encryptMorseCodeWithSpecialCharacters(String plainText) {
        String actualMorseCode = MorseCodeDecoding.encrypt(plainText, morseCodeMap);
        String expectedMorseCode = ". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. .";

        assertEquals(expectedMorseCode, actualMorseCode,
                "The encoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {".  ...- .   .-. -.-- -  .... .. -. --. |  .. ... |  .--. --- .. -. - .-.. . ... ... | -  ....  . .-. . |  ..  ...  |  -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. . "})
    void decryptMorseCodeInEnglishWithSeveralSpacesInBetween(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode, morseCodeMap, false);
        String expectedPlaintext = "everything is pointless there is no meaning to life";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"everything   is   pointless  there   is   no meaning to life"})
    void encryptMorseCodeInEnglishWithSeveralSpacesInBetween(String plainText) {
        String actualMorseCode = MorseCodeDecoding.encrypt(plainText, morseCodeMap);
        String expectedMorseCode = ". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. .";

        assertEquals(expectedMorseCode, actualMorseCode,
                "The encoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-.-- . ... - . .-. -.. .- -.-- | .. ... | .... .. ... - --- .-. -.-- | - --- -- --- .-. .-. --- .-- | .. ... | .- | -- -.-- ... - . .-. -.-- | -... ..- - | - --- -.. .- -.-- | .. ... | .- | --. .. ..-. - | - .... .- - | .. ... | .-- .... -.-- | .. - ... | -.-. .- .-.. .-.. . -.. | - .... . | .--. .-. . ... . -. -"})
    void decryptMorseCodesOfOthers(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode, morseCodeMap, true);
        String expectedPlaintext = "YESTERDAY IS HISTORY TOMORROW IS A MYSTERY BUT TODAY IS A GIFT THAT IS WHY ITS CALLED THE PRESENT";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"_. . ..._ . ._. * __. ___ _. _. ._ * __. .. ..._ . * _.__ ___ .._ * .._ .__. ._._._ * _. . ..._ . ._. * __. ___ _. _. ._ * ._.. . _ * _.__ ___ .._ * _.. ___ .__ _. ._._._ * _. . ..._ . ._. *  __. ___ _. _. ._ * ._. .._ _. * ._ ._. ___ .._ _. _.. * ._ _. _.. * _.. . ... . ._. _ * _.__ ___ .._ ._._._ * _. . ..._ . ._. * __. ___ _. _. ._ * __ ._ _._ . * _.__ ___ .._ * _._. ._. _.__ ._._._ * _. . ..._ . ._. * __. ___ _. _. ._ * ... ._ _.__ * __. ___ ___ _.. _... _.__ . ._._._ * _. . ..._ . ._. * __. ___ _. _. ._ * _ . ._.. ._.. * ._ * ._.. .. . * ._ _. _.. * .... .._ ._. _ * _.__ ___ .._ ._._._"})
    void decryptMorseCodesWithUnderscoresAndAsterisks(String morseCode) {
        String resultPlaintext = MorseCodeDecoding.decrypt(morseCode, morseCodeMap, false);
        String expectedPlaintext = "never gonna give you up  never gonna let you down  never gonna run around and desert you  never gonna make you cry  never gonna say goodbye  never gonna tell a lie and hurt you ";

        assertEquals(expectedPlaintext, resultPlaintext,
                "The decoding of Morse code is wrong.");
    }
}