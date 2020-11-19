package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q5 { // should be called Logger
        private static int loggerCount = 0;
        private final int logLevel;

        public static void main(String args[]) {
            createInstance(-1);
        }

        private Q5(int logLevel) throws IllegalArgumentException {
            if(logLevel < 0 || logLevel > 10) {
                throw new IllegalArgumentException("logLevel only takes values 0-10");
            }
            this.logLevel = logLevel;
        }

        public static Q5 createInstance(int logLevel) {
            Q5 retObj = null;
            try {
                retObj = new Q5(logLevel);
            }
            catch(IllegalArgumentException e) {
                System.out.println(e);
            }
            finally {
                loggerCount++;
            }
            return retObj;
        }
    /**
     * What is the output of the following code?
     *
     * A. java.lang.IllegalArgumentException: logLevel only takes values 0-10
     * B. 0
     * C. 1
     * D. Code will not compile
     */

}
