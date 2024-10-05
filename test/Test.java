import net.littlecircleoo.bv2av.Converter;

public class Test {
    public static void main(String[] args) {
        System.out.println(Converter.av2bv("av412935552"));
        System.out.println(Converter.av2bv("av113088468616537"));
        System.out.println(Converter.av2bv("412935552"));
        System.out.println(Converter.av2bv("113088468616537"));
        System.out.println(Converter.av2bv(412935552));
        System.out.println(Converter.av2bv(113088468616537L));
        System.out.println(Converter.bv2av("BV1FV411d7u7"));
        System.out.println(Converter.bv2av("BV1EtHme1ESg"));
        System.out.println(Converter.bv2av("BV1FV411d7u7",true));
        System.out.println(Converter.bv2av("BV1EtHme1ESg",true));
        System.out.println(Converter.bv2av("BV1FV411d7u7",false));
        System.out.println(Converter.bv2av("BV1EtHme1ESg",false));
        System.out.println(Converter.convert("av412935552"));
        System.out.println(Converter.convert("av113088468616537"));
        System.out.println(Converter.convert("412935552"));
        System.out.println(Converter.convert("113088468616537"));
        System.out.println(Converter.convert(412935552));
        System.out.println(Converter.convert(113088468616537L));
        System.out.println(Converter.convert("BV1FV411d7u7"));
        System.out.println(Converter.convert("BV1EtHme1ESg"));
        System.out.println(Converter.convert("BV1FV411d7u7",true));
        System.out.println(Converter.convert("BV1EtHme1ESg",true));
        System.out.println(Converter.convert("BV1FV411d7u7",false));
        System.out.println(Converter.convert("BV1EtHme1ESg",false));
    }
}
