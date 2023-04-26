#Palindrome in java function 

String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        // Compare original string with reversed string
        if (input.equals(reversed)) {
            System.out.println("The input string is a palindrome.");
        } else {
            System.out.println("The input string is not a palindrome.");
        }

-----------------------------------------------------------------------------

#Factorial in java
 int factorial = 1;

    for (int i = 1; i <= n; i++) {
      factorial *= i;
    }


-----------------------------------------------------------------------------
#vowels extract
private static String extractVowels(String sentence) {
        StringBuilder vowels = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                vowels.append(c);
            }
        }
        return vowels.toString();
    }
