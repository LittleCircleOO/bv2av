package net.littlecircleoo.bv2av;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) { //no arguments
            FlatMacLightLaf.setup();
            new MainGUI();
        }else {
            for (String arg : args)
                try {
                    System.out.println(arg + " -> " + Converter.convert(arg));
                }catch (Exception ex){
                    System.err.println(arg + " -> [ERROR]\n" + ex.toString());
                }
        }
    }
}
