package net.littlecircleoo.bv2av;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) { //no arguments
            new MainGUI();
        }else {
            for (String arg : args)
                System.out.println(arg + " -> " + Converter.convert(arg));
        }
    }
}
