package com.jwillians.jweb;

import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 * A JWTag is an HTML element.
 * @author EricsonWRP
 */
public class JWTag {

    /** The HTML element itself. */
    private StringBuilder tag;
    private Document doc;
    private Element element;
    
    /** The default HTML 5 doctype. */
    public static String doctype = "<!DOCTYPE html>";

    /**
     * The simplest constructor.
     * It builds an empty tag (For example: "&lt;h1&gt;&lt;/h1&gt;").
     * @param tag The name of the tag, such as "h1" or "p".
     */
    public JWTag(String tag) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append('>').
                append("</").
                append(tag).
                append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    /**
     * It builds a simple tag with a content.
     * @param tag The name of the tag, such as "h1" or "p".
     * @param content The content of the tag (For example: "&lt;h1&gt;content&lt;/h1&gt;")
     */
    public JWTag(String tag, String content) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append('>').
                append(content).
                append("</").
                append(tag).
                append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    /**
     * It builds a tag with a content and a simple attribute.
     * @param tag The name of the tag, such as "h1" or "p".
     * @param content The content of the tag (For example: "&lt;h1&gt;content&lt;/h1&gt;")
     * @param attribute A simple attribute as a Pair tuple. 
     * <p>
     * An attribute is comprised of two Strings, such as in:
     * "&lt;h1 style="color: red"&gt;content&lt;/h1&gt;", where "style" is the first String, and its value the second one (Thus, a pair).
     */
    public JWTag(String tag, String content, Pair<String, String> attribute) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append(' ').
                append(attribute.getValue0()).
                append('=').
                append('"').
                append(attribute.getValue1()).
                append('"').
                append('>').
                append(content).
                append("</").
                append(tag).
                append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    /**
     * It builds a tag with no content, with a simple attribute.
     * @param tag The name of the tag, such as "h1" or "p".
     * @param attribute A simple attribute as a Pair tuple. 
     * <p>
     * An attribute is comprised of two Strings, such as in:
     * "&lt;h1 style="color: red"&gt;content&lt;/h1&gt;", where "style" is the first String, and its value the second one (Thus, a pair).
     */
    public JWTag(String tag, Pair<String, String> attribute) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append(' ').
                append(attribute.getValue0()).
                append('=').
                append('"').
                append(attribute.getValue1()).
                append('"').
                append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    /**
     * It builds a tag with content and more than one attribute.
     * @param tag The name of the tag, such as "h1" or "p".
     * @param content The content of the tag (For example: "&lt;h1&gt;content&lt;/h1&gt;")
     * @param attributes An array of attributes as Pair tuple objects.
     * <p>
     * An attribute is comprised of two Strings, such as in:
     * "&lt;h1 style="color: red"&gt;content&lt;/h1&gt;", where "style" is the first String, and its value the second one (Thus, a pair).
     */
    public JWTag(String tag, String content, Pair<String, String>[] attributes) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append(' ');
        int i = 1;
        for (Pair<String, String> attribute : attributes) {
            this.tag.append(attribute.getValue0()).
                    append('=').
                    append('"').
                    append(attribute.getValue1()).
                    append('"');
            if (i++ != attributes.length) {
                this.tag.append(' ');
            }
        }
        this.tag.append('>').
                append(content).
                append("</").
                append(tag).
                append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    /**
     * It builds a simple tag with no content, but with more than one attribute (A good example of this is an img tag).
     * @param tag The name of the tag, such as "h1" or "p".
     * @param attributes An array of attributes as Pair tuple objects.
     * <p>
     * An attribute is comprised of two Strings, such as in:
     * "&lt;h1 style="color: red"&gt;content&lt;/h1&gt;", where "style" is the first String, and its value the second one (Thus, a pair).
     */
    public JWTag(String tag, Pair<String, String>[] attributes) {
        this.tag = new StringBuilder();
        this.tag.append('<').
                append(tag).
                append(' ');
        int i = 1;
        for (Pair<String, String> attribute : attributes) {
            this.tag.append(attribute.getValue0()).
                    append('=').
                    append('"').
                    append(attribute.getValue1()).
                    append('"');
            if (i++ != attributes.length) {
                this.tag.append(' ');
            }
        }
        this.tag.append('>');
        doc = Jsoup.parse(this.tag.toString(), "", Parser.xmlParser());
        element = doc.select(tag).first();
    }

    @Override
    public String toString() {
        return getTag().toString();
    }
    
    /**
     * Appends text to the tag.
     * @param text 
     */
    public void appendText(String text) {
        getElement().appendText(text);
        setTag(new StringBuilder(getElement().toString()));
    }
    
    /**
     * Prepends HTML to the tag (Nested tags, for example)
     * @param html 
     */
    public void prepend(String html) {
        getElement().prepend(html);
        setTag(new StringBuilder(getElement().toString()));
    }
    
    /**
     * Appends HTML to the tag (Nested tags, for example)
     * @param html 
     */
    public void append(String html) {
        getElement().append(html);
        setTag(new StringBuilder(getElement().toString()));
    }
    
    /**
     * @return StringBuilder
     */
    public StringBuilder getTag() {
        return tag;
    }
    
    /**
     * @param tag 
     */
    public void setTag(StringBuilder tag) {
        this.tag = tag;
    }

    /**
     * @return Document
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * @param doc 
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     * @return 
     */
    public Element getElement() {
        return element;
    }

    /**
     * @param element 
     */
    public void setElement(Element element) {
        this.element = element;
    }
    
}
