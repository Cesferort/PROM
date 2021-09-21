package com.example.tratamientoxml_ejercicio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDOM
{
    private URL rssURL;

    public RssParserDOM(String url)
    {
        try
        {
            this.rssURL = new URL (url);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse()
    {
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Tiempo> tiempos = new ArrayList<Tiempo>();
        try
        {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol (<rss>)
            Element root = dom.getDocumentElement();
            //Localizamos todos los elementos <dia>
            NodeList dias = root.getElementsByTagName("dia");

            //TODO Número, mes y año funcionales
            int numDia = 22;
            //Recorremos la lista de dias
            for (int i = 0; i < dias.getLength(); i++)
            {
                //Obtenemos el dia actual
                Node dia = dias.item(i);
                String fecha = "2021-05-" + numDia++;
                ArrayList<String>precipitacion = new ArrayList<String>();
                ArrayList<String>cotaNieve = new ArrayList<String>();
                ArrayList<String>estadosCielo = new ArrayList<String>();

                NodeList datosDia = dia.getChildNodes();
                for (int j = 0; j < datosDia.getLength(); j++)
                {
                    Node dato = datosDia.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("prob_precipitacion"))
                    {
                        String prob_precipitacion = obtenerTexto(dato);
                        precipitacion.add(prob_precipitacion);
                    }
                    else if(etiqueta.equals("cota_nieve_prov"))
                    {
                        String cota_nieve_prov = obtenerTexto(dato);
                        cotaNieve.add(cota_nieve_prov);
                    }
                    else if(etiqueta.equals("estado_cielo"))
                    {
                        String estado_cielo = obtenerTexto(dato);
                        estadosCielo.add(estado_cielo);
                    }
                }
                Tiempo tiempo = new Tiempo(fecha,precipitacion,cotaNieve,estadosCielo);
                tiempos.add(tiempo);
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        return tiempos;
    }

    public String obtenerTexto (Node dato)
    {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();
        for (int k = 0; k < fragmentos.getLength(); k++)
            texto.append(fragmentos.item(k).getNodeValue());

        return texto.toString();
    }

    private InputStream getInputStream()
    {
        try
        {
            return rssURL.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}