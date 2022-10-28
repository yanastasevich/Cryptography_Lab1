import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MorseCodeDecoding {
    public static Map<Character, String> morseCodeMap = new HashMap<>();
    static String plainText = "everything is pointless there is no meaning to life";


    public static void main(String[] args) {
        fillMorseCodeMap(morseCodeMap);

        String cipherText = encrypt(plainText, morseCodeMap);
        System.out.println("Ciphertext is " + cipherText);

        //String value = ". ...- . .-. -.-- - .... .. -. --. | .. ... | .--. --- .. -. - .-.. . ... ... | - .... . .-. . | .. ... | -. --- | -- . .- -. .. -. --. | - --- | .-.. .. ..-. .";
        String plainText = decrypt(cipherText, morseCodeMap);
        //TODO: the phrase is decoded with LIF in the end
        System.out.println("decoded phrase is " + plainText);
    }

    public static void fillMorseCodeMap(Map<Character, String> map) {
        map.put('A', ".-");
        map.put('B', "-...");
        map.put('C', "-.-.");
        map.put('D', "-..");
        map.put('E', ".");
        map.put('F', "..-.");
        map.put('G', "--.");
        map.put('H', "....");
        map.put('I', "..");
        map.put('J', ".---");
        map.put('K', "-.-");
        map.put('L', ".-..");
        map.put('M', "--");
        map.put('N', "-.");
        map.put('O', "---");
        map.put('P', ".--.");
        map.put('Q', "--.-");
        map.put('R', ".-.");
        map.put('S', "...");
        map.put('T', "-");
        map.put('U', "..-");
        map.put('V', "...-");
        map.put('W', ".--");
        map.put('X', "-..-");
        map.put('Y', "-.--");
        map.put('Z', "--..");
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
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext
                    .append(map.get(plaintext.toUpperCase(Locale.ROOT).charAt(i)))
                    .append(' ');
        }
        return ciphertext.toString();
    }

    public static String decrypt(String cipherText, Map<Character, String> map) {
        int start = 0;
        int end = 0;
        StringBuilder plainText = new StringBuilder();


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
        return plainText.toString();
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
