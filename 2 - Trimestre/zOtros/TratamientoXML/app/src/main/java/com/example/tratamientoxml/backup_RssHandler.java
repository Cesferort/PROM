package com.example.tratamientoxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class backup_RssHandler extends DefaultHandler
{
    private List<Noticia> noticias; //Lista de noticias
    private Noticia noticiaActual; //Noticia Actual
    private StringBuilder sbTexto; //Texto del elemento

    public List<Noticia> getNoticias() {
        return noticias;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        noticias = new ArrayList<Noticia>();
        sbTexto = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("item")) {
            noticiaActual = new Noticia();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        if (this.noticiaActual != null)
            sbTexto.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (this.noticiaActual != null) {
            if (localName.equals("title")) {
                noticiaActual.setTitulo(sbTexto.toString());
            } else if (localName.equals("link")) {
                noticiaActual.setLink(sbTexto.toString());
            } else if (localName.equals("description")) {
                noticiaActual.setDescripcion(sbTexto.toString());
            } else if (localName.equals("guid")) {
                noticiaActual.setGuid(sbTexto.toString());
            } else if (localName.equals("pubDate")) {
                noticiaActual.setFecha(sbTexto.toString());
            } else if (localName.equals("item")) {
                noticias.add(noticiaActual);
            }
            sbTexto.setLength(0);
        }
    }
}