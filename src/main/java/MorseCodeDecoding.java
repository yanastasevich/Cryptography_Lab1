import java.util.HashMap;
import java.util.Map;

public class MorseCodeDecoding {
    public static Map<Character, String> morseCodeMap = new HashMap<>();
    static String plainText = "everything is pointless there is no meaning to life";
    static boolean decryptAsUpperCase = true;

    public static void main(String[] args) {
        System.out.println("Decoding " + plainText);
        fillMorseCodeMap(morseCodeMap);

        String cipherText = encrypt(plainText, morseCodeMap);
        System.out.println("After encryption, ciphertext is " + cipherText);

        String plainText = decrypt(cipherText, morseCodeMap, decryptAsUpperCase);
        System.out.println("After decryption, plaintext is " + plainText);
    }

    public static void fillMorseCodeMap(Map<Character, String> map) {
        map.put('a', ".-");
        map.put('b', "-...");
        map.put('c', "-.-.");
        map.put('d', "-..");
        map.put('e', ".");
        map.put('f', "..-.");
        map.put('g', "--.");
        map.put('h', "....");
        map.put('i', "..");
        map.put('j', ".---");
        map.put('k', "-.-");
        map.put('l', ".-..");
        map.put('m', "--");
        map.put('n', "-.");
        map.put('o', "---");
        map.put('p', ".--.");
        map.put('q', "--.-");
        map.put('r', ".-.");
        map.put('s', "...");
        map.put('t', "-");
        map.put('u', "..-");
        map.put('v', "...-");
        map.put('w', ".--");
        map.put('x', "-..-");
        map.put('y', "-.--");
        map.put('z', "--..");
        map.put('1', ".----");
        map.put('2', "..---");
        map.put('3', "...--");
        map.put('4', "....-");
        map.put('5', ".....");
        map.put('6', "-....");
        map.put('7', "--...");
        map.put('8', "---..");
        map.put('9', "----.");
        map.put('0', "-----");
        map.put(' ', "|");
    }

    public static String encrypt(String plaintext, Map<Character, String> map) {
        StringBuilder ciphertext = new StringBuilder();
        String plainText = getTrimmedStringWithSingleSpaces(plaintext).toLowerCase();

        for (int i = 0; i < plainText.length(); i++) {
            Character charPos = plainText.charAt(i);
            // skip any unexpected characters not contained in the map
            if (map.containsKey(charPos)) {
                ciphertext.append(map.get(charPos));
            } else continue;
            // don't append a space if it's the end of the string
            if (i < plainText.length() - 1) {
                ciphertext.append(' ');
            }
        }
        return ciphertext.toString();
    }

    private static String getTrimmedStringWithSingleSpaces(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    public static String decrypt(String cipherText, Map<Character, String> map, boolean isUpperCase) {
        int start = 0;
        int end;
        StringBuilder plainText = new StringBuilder();

        cipherText = getTrimmedStringWithSingleSpaces(cipherText);
        cipherText = equalizeCharacters(cipherText);

        for (int i = 0; i < cipherText.length(); i++) {
            String charPos = Character.toString(cipherText.charAt(i));

            if (i == cipherText.length() - 1) {
                end = i + 1;
                String sequence = cipherText.substring(start, end);
                plainText = plainText.append(getKey(sequence, map));
                break;
            }
            if (charPos.equals("|")) {
                start += 1;
            }
            if (charPos.equals(" ")) {
                end = i;
                String sequence = cipherText.substring(start, end);
                plainText = plainText.append(getKey(sequence, map));
                start = i + 1;
            }
        }
        if (isUpperCase) {
            return plainText.toString().toUpperCase();
        }
        return plainText.toString();
    }

    public static String equalizeCharacters(String cipherText) {
        cipherText = cipherText.replace("*", "|");
        cipherText = cipherText.replace("_", "-");
        return cipherText;
    }

    public static String getKey(String value, Map<Character, String> map) {
        Character key = ' ';
        for (Map.Entry<Character, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
            }
        }
        return key.toString();
    }
}
