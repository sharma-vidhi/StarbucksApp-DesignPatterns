package starbucks;

/**
 * String helper
 */
public class StringHelper {

	  /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    public static void dumpLines(String str) {
          String[] lines = str.split("\r\n|\r|\n");
          for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
          }
    }

    /**
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    public static int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) return 0;

        int lines = 0, pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
                lines++;
        }

        return lines ;
    }

    /** 
     * Helper:  Pad lines for a Screen 
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    public static String padLines(int num) {
    	
    	StringBuffer buf = new StringBuffer();
    	for (int i = 0; i < num; ++i) {
    		System.err.print(".") ; buf.append("\n");
    	}
    	System.err.println("") ;
    	return buf.toString();
    	
//hnote
//        String lines = "" ;
//        for ( int i = 0; i<num; i++ ) {
//            System.err.print(".") ;
//            lines += "\n" ;
//        }
//        System.err.println("") ;
//        return lines ;
    }
    
    /**
     * Helper:  Pad Spaces for a Line
     * @param  num Num Spaces to Pad
     * @return     Padded Line
     */
    public static String padSpaces(int num) {
    	StringBuffer buf = new StringBuffer();
    	for (int i = 0; i < num; ++i)  buf.append(" ");
    	return buf.toString();
    	
//        String spaces = "" ;
//        for ( int i = 0; i<num; i++ )
//            spaces += " " ;           
//        return spaces ;     
    }
    

}
