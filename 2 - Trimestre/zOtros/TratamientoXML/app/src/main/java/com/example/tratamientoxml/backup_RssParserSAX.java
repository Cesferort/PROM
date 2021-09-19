package com.example.tratamientoxml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class backup_RssParserSAX
{
    private URL rssUrl;

    public backup_RssParserSAX(String url) {
        try {
            this.rssUrl = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Noticia> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            backup_RssHandler handler = new backup_RssHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getNoticias();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private InputStream getInputStream (){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}