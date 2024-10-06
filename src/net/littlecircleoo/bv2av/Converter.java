package net.littlecircleoo.bv2av;

public class Converter {
    private static final long XOR = 23442827791579L;
    private static final long MASK = 2251799813685247L;

    private static final long MAX_AID = 1L << 51;
    private static final long MIN_AID = 1L;

    private static final int BASE = 58;
    private static final int BV_LEN = 12;

    private static final String ALPHABET = "FcwAPNKTMug3GV5Lj7EJnHpWsx4tb8haYeviqBz6rkCy12mUSDQX9RdoZf";

    /**
     * Get the index of a character in the alphabet
     * @param c the character to find
     * @return the index of the character in the alphabet
     */
    private static int indexOf(char c) {
        return ALPHABET.indexOf(c);
    }

    /**
     * Get the character at the given index in the alphabet
     * @param i the index of the character to get
     * @return the character at the given index in the alphabet
     */
    private static char charAt(int i) {
        return ALPHABET.charAt(i);
    }

    /**
     * Swap two characters in a string
     * @param str the string to swap characters in
     * @param i the index of the first character to swap
     * @param j the index of the second character to swap
     */
    private static void swap(StringBuilder str, int i, int j) {
        char tmp = str.charAt(i);
        str.setCharAt(i, str.charAt(j));
        str.setCharAt(j, tmp);
    }

    /**
     * Convert aid to bvid
     * @param av the aid to convert
     * @return the bvid
     */
    public static String av2bv(long av) {
        if (av < MIN_AID || av > MAX_AID)
            throw new IllegalArgumentException("av must be in range [1, 2251799813685248]");
        StringBuilder bv = new StringBuilder(BV_LEN);
        bv.insert(0,"BV1");
        long data = (MAX_AID | av) ^ XOR;
        while(data != 0){
            bv.insert(3, charAt((int) (data % BASE)));
            data /= BASE;
        }
        swap(bv,3,9);
        swap(bv,4,7);
        return bv.toString();
    }

    /**
     * Convert aid to bvid, aid can be a string starting with "av" or a number
     * @param av the aid to convert
     * @return the bvid
     */
    public static String av2bv(String av){
        if(av.startsWith("av")){
            av = av.substring(2);
        }
        if(av.isEmpty())
            throw new IllegalArgumentException("av cannot be empty");
        return av2bv(Long.parseLong(av));
    }

    /**
     * Convert bvid to aid
     * @param bv the bvid to convert
     * @return the aid
     */
    public static long bv2av(String bv) {
        StringBuilder bvConverter = new StringBuilder(bv.trim());
        if(bvConverter.isEmpty())
            throw new IllegalArgumentException("bv cannot be empty");
        if(bvConverter.length() != BV_LEN)
            throw new IllegalArgumentException("bv must be 12 characters long");
        if(!bvConverter.toString().toUpperCase().startsWith("BV1"))
        //if(!bvConverter.substring(0,2).equalsIgnoreCase("BV") || bvConverter.charAt(2) != '1')
            throw new IllegalArgumentException("bv must start with BV1");
        swap(bvConverter,3,9);
        swap(bvConverter,4,7);
        String subStr = bvConverter.substring(3);
        long avData = 0;
        for(char c : subStr.toCharArray()) {
            avData = avData * BASE + indexOf(c);
        }
        return (avData & MASK) ^ XOR;
    }

    /**
     * Convert bvid to aid, aid can be a string starting with "av" or a number
     * @param bv the bvid to convert
     * @param withPrefix whether to add "av" prefix to the aid
     * @return the aid
     */
    public static String bv2av(String bv, boolean withPrefix) {
        if(withPrefix)
            return "av" + bv2av(bv);
        return String.valueOf(bv2av(bv));
    }

    public static String convert(String id, boolean withPrefix){
        if(id.isEmpty())
            throw new IllegalArgumentException("id cannot be empty");
        if(id.startsWith("av")){
            return av2bv(id);
        }else if(id.toUpperCase().startsWith("BV1")){
            //id.substring(0,2).equalsIgnoreCase("BV") && id.charAt(2) == '1'
            return bv2av(id, withPrefix);
        }else{
            try {
                long aid = Long.parseLong(id);
                return av2bv(aid);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("id must be a valid aid or bvid");
            }
        }
    }

    public static String convert(long aid){
        return av2bv(aid);
    }

    public static String convert(String id){
        return convert(id, true);
    }
}
