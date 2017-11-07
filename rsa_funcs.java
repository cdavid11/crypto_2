/*
import javax.crypto.*;
import javax.crypto.spec.*;

*/
import java.security.*;
import java.io.*;
import java.math.*;
import java.util.Scanner;

public class rsa_funcs
{

	private static BufferedReader bufferedReader;

	/*
	 * Gets key file from command line with -k command
	 * This returns and array for the 3 values in the key file
	 */
	public static BigInteger[] key_file( String[] args ) throws Exception
	{
		BigInteger key_data[] = null;
				
		for ( int i = 0; i < args.length; i++ )
		{
			//-k <key file>:  required, specifies a file storing a valid AES key as a hex encoded string
			if ( args[i].equals("-k") )
			{
				i++;
				
				key_data =  read_integer_file(args[i]);

				break;
			}

		}

		if (key_data == null)
		{
			System.err.println("No file was found. Need to exit");
			System.exit(0);
		}
		
		return key_data;		
	}
	
	/*
	 * Gets key file from command line with -i command
	 * This returns the string of the value in the input file
	 */
	public static String input_file( String[] args ) throws Exception
	{
		String input_data = null;
				
		for ( int i = 0; i < args.length; i++ )
		{
			//-k <key file>:  required, specifies a file storing a valid AES key as a hex encoded string
			if ( args[i].equals("-i") )
			{
				i++;
				input_data = read_string_file(args[i]);
				break;
			}

		}

		if (input_data == null)
		{
			System.err.println("No file was found. Need to exit");
			System.exit(0);
		}
		
		return input_data;		
	}
	
	/*
	 * Gets output file name from command line with -o
	 */
	public static void output_file ( String[] args, String output ) throws Exception
	{
		for ( int i = 0; i < args.length; i++ )
		{
			//-o <output file>: required, specifies the path of the file where the resulting output is stored
			if ( args[i].equals("-o") )
			{
				i++;
				write_file( args[i], output );
				break;
			}
		}
	}

	/* Reads the data in a file, and converts the values into BigIntegers
	 * 
	 */
	public static BigInteger[] read_integer_file(String filename) throws Exception
	{
		
		BigInteger[] data = new BigInteger[3];

		
		try {
            File file = new File(filename);

            Scanner input = new Scanner(file);

            int i = 0;
            
            while (input.hasNextBigInteger())
            {
                BigInteger bi = input.nextBigInteger();
                data[i] = bi;
                i++;
            }
            
            input.close();
            
            return data;
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/* Reads the information in a file and puts it into a single string
	 * 
	 */
	public static String read_string_file(String filename) throws Exception
	{
		
		
		try {
            FileReader file = new FileReader(filename);

            bufferedReader = new BufferedReader(file);
            
            String data = "";
            String line = null;
           
            while((line=bufferedReader.readLine())!=null)
            {
            	data += line;
            }
            		
            return data;
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Writes data to chosen file
	 */
	public static void write_file(String filename, String data) throws Exception
	{
		
		PrintWriter out = new PrintWriter(filename);
		try 
			{	
				out.print(data);
				
				out.close();
			}
			catch (Exception e)
			{
				System.err.format("Exception occurred trying to write '%s'.", filename);
				e.printStackTrace();
			}
			
	}
	
	
	public static BigInteger encrypt_data(BigInteger[] key_data, BigInteger input_data)
	{
		BigInteger enc_data = BigInteger.ONE;
		BigInteger rsa_number_of_bits = key_data[0];
		BigInteger rsa_N = key_data[1];
		BigInteger rsa_e = key_data[2];
		byte[] random_bytes = create_random_bytes(rsa_number_of_bits);
		
		/* Converts a BigInteger to a byte array */
		byte[] input_data_bytes = input_data.toByteArray();


		
		/*
		formula for encryption:
			( 0x00 || 0x02 || r || 0x00 || m )^e mod N
		*/
		
		return enc_data;
		
	}
	
	public static byte[] create_random_bytes(BigInteger num_bits)
	{
		//TODO: Do we need to check for multiples of 8 or something?
		int num_bytes = num_bits.intValue()/8;
		
		byte[] random_bytes = new byte[num_bytes];
				
		SecureRandom random = new SecureRandom();
		
		random.nextBytes(random_bytes);
		
		return random_bytes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}