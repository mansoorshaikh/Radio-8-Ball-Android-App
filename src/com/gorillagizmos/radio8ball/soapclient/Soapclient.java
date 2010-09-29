package com.gorillagizmos.radio8ball.soapclient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.*;

public class Soapclient {
	
	private static final String SOAP_ACTION = "urn:r8bwsdl:query";
    private static final String METHOD_NAME = "query";
    private static final String NAMESPACE = "urn:r8bwsdl";
    private static final String URL = "http://10.0.2.2/r8b_soap/index.php";
    private static final String TOKEN = "fd01fe934f26df17bac216e0c8f31af5";
    public String token;
    String songtitle;
    String songartist;
    String songalbum;
    String songfile;
    
    public Soapclient() {
    	
    }
    
    public void query(String question) {
    	
    	try {
    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    		request.addProperty("token",TOKEN);
    		request.addProperty("question",question);
    		
    		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION, envelope);

            Object result = envelope.getResponse();

            String results = (String) result;
            StringTokenizer st = new StringTokenizer(results,"|");
            this.songtitle = st.nextToken();
            this.songartist = st.nextToken();
            this.songalbum = st.nextToken();
            this.songfile = st.nextToken();
    		
     	} catch (Exception e) {
			
		}
    }
    
    public void newUser(String f_name, String l_name, String email) {
    	try {
    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        	request.addProperty("f_name",f_name);
        	request.addProperty("l_name",l_name);
        	request.addProperty("email",email);	
        	
        	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION, envelope);

            Object result = envelope.getResponse();

            String results = (String) result;
            StringTokenizer st = new StringTokenizer(results,"|");
            this.songtitle = st.nextToken();
            this.songartist = st.nextToken();
            this.songalbum = st.nextToken();
            this.songfile = st.nextToken();

    	} catch (Exception e) {
    		
    	}
    	
    }

    public String getSongTitle() {
    	return this.songtitle;
    }
    
    public String getSongArtist() {
    	return this.songtitle;
    }
    
    public String getSongAlbum() {
    	return this.songtitle;
    }
    
    public String getSongFile() {
    	return this.songfile;
    }
}
