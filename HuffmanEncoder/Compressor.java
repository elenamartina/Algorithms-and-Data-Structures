import java.util.BitSet;

public class Compressor {




    public String compress(char character, HuffmanTree tree) {

        tree.insert(character);

        tree.printTree();

        BitSet set = tree.getCharacterCode(character);

        String code = "";
        for(int i = 0; i <= set.length(); i++) {
            if(set.get(i)) {
                code += "1";
            } else {
                code += "0";
            }
        }

        System.out.println(character + "  CHARACTER OUTPUT  =  " + code);

        return code;
    }



    public String compress(String input) {

        HuffmanTree tree = new HuffmanTree();

        String binaryString = "";


        for (int i = 0; i < input.length(); i++) {

            binaryString += this.compress(input.charAt(i), tree);
        }

        System.out.println("OUTPUT  =  " + binaryString);

        return binaryString;
    }


}
