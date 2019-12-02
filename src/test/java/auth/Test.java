package auth;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	private static Test INSTANCE = new Test();
    private static String pattern = null;
 
    /**
     * No one can make a direct instance
     * */
   
    /**
     * Force the user to build a validator using this way only
     * */
    public static Test buildValidator( boolean forceSpecialChar,
                                                    boolean forceCapitalLetter,
                                                    boolean forceNumber,
                                                    int minLength,
                                                    int maxLength)
    {
        StringBuilder patternBuilder = new StringBuilder("((?=.*[a-z])");
 
        if (forceSpecialChar)
        {
            patternBuilder.append("(?=.*[@#$%])");
        }
 
        if (forceCapitalLetter)
        {
            patternBuilder.append("(?=.*[A-Z])");
        }
 
        if (forceNumber)
        {
            patternBuilder.append("(?=.*d)");
        }
 
        patternBuilder.append(".{" + minLength + "," + maxLength + "})");
        pattern = patternBuilder.toString();
 
        return INSTANCE;
    }
 
    /**
     * Here we will validate the password
     * */
    public static boolean validatePassword(final String password)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }
	
	/**
	 * @param args
	 */
	public static void main(String [] args) {
	/*	
		  Pattern pattern;
		   Matcher matcher;
		   pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}$");
		   matcher = pattern.matcher("Naresh@123");
	        System.out.println(matcher.matches());*/
		
		String resourceName = "admin1.jsp";
		 
		ClassLoader classLoader = Test.class.getClassLoader();
		File file = new File(classLoader.getResource("/Test.class").getFile());
		String absolutePath = file.getAbsolutePath();
		 
		System.out.println(absolutePath);
		 
		System.out.println(absolutePath);
		
	 
	}

}
