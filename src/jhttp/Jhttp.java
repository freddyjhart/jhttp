package jhttp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Jhttp
{
	public static void main(String[] args)
	{
		String arg; 
		int i = 0;
		String method = "GET";
		
		while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            
            if (arg.equals("-h")) {
          		usage();
          		System.exit(0);
            } else if (arg.equals("-m")) {
            	method=args[i];
            	if (!method.equalsIgnoreCase("GET") &&
            	    !method.equalsIgnoreCase("PUT") &&
            	    !method.equalsIgnoreCase("POST")) {
            		System.out.println("Unrecognized HTTP method: "+method);
            		usage();
            	}
            	i++;
            }
            else
            {
            	System.out.println("Unrecognized option: "+arg+"\n");
            	usage();
            }
		}
		try {
			URL url = new URL(args[i]);
			HttpURLConnection c = (HttpURLConnection)url.openConnection();
			c.setRequestMethod(method);
			InputStream inputStream = c.getInputStream();
			byte[] b = new byte[1];
			int result = inputStream.read(b);
			while (result >= 0) {
				System.out.print((char)(b[0]));
				result = inputStream.read(b);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void usage() {
		System.out.println("Usage: java jhttp/JHttp [-h]|[-m GET|PUT|POST] \n     -h: prints this message\n     -m HTTP method name");
	}
}
