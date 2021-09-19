package com.example.tratamientoxml_dom;
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

    public List<Noticia> parse()
    {
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Noticia> noticias = new ArrayList<Noticia>();
        try
        {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol (<rss>)
            Element root = dom.getDocumentElement();
            //Localizamos todos los elemntos <item>
            NodeList items = root.getElementsByTagName("item");
            //Recorremos la lista de noticias
            for (int i = 0; i < items.getLength(); i++)
            {
                Noticia noticia = new Noticia();
                //Obtenemos la noticia actual
                Node item = items.item(i);
                //Obtenemos la lista de datos de la noticia actual
                NodeList datosNoticia = item.getChildNodes();
                //Procesamos cada dato de la noticia
                for (int j = 0; j < datosNoticia.getLength(); j++)
                {
                    Node dato = datosNoticia.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("title"))
                    {
                        String texto = obtenerTexto(dato);
                        noticia.setTitulo(texto);
                    }
                    else if (etiqueta.equals("link"))
                    {
                        String texto = dato.getFirstChild().getNodeValue();
                        noticia.setLink(texto);
                        //noticia.setLink(dato.getFirstChild().getNodeValue());
                    }
                    else if (etiqueta.equals("guid"))
                    {
                        noticia.setGuid(dato.getFirstChild().getNodeValue());
                    }
                    else if (etiqueta.equals("pubDate"))
                    {
                        noticia.setFecha(dato.getFirstChild().getNodeValue());
                    }
                }
                noticias.add(noticia);
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
        return noticias;
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