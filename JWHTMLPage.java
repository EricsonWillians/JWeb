package com.jwillians.jweb;

/*

JWeb - Request-based Java Web Framework
Copyright (C) <2015>  <Ericson Willians - JWillians>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>. 

*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.nio.charset.Charset;

/**
 * A JWHTMLPage is an HTML document.
 * @author EricsonWillians
 */
@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPage implements JWDisplayable {

    /** The HTML document as a String. */
    private StringBuilder html;
    /** The tags that comprise the HTML document. */
    private JWTag[] tags;
    /** The HTML document as a Jsoup Document object. */
    private Document doc;
    /** The head of the HTML document as a Jsoup Element object. */
    private Element head;
    /** The body of the HTML document as a Jsoup Element object. */
    private Element body;
    
    /**
     * It builds a template HTMl document with a given title, encoded with the default charset (Which is probably UTF-8).
     * @param title The title of the HTML document.
     */
    public JWHTMLPage(String title) {
        html = new StringBuilder();
        setDefaultTags();
        doc = Jsoup.parse(html.toString());
        doc.charset(Charset.defaultCharset());
        head = doc.head();
        body = doc.body();
        head.append("<title>" + title + "</title>");
        updateHTML();
    }
    
    /**
     * It builds a template HTMl document with a given title and Charset for the document.
     * @param title The title of the HTML document.
     * @param charset The encoding of the HTML document.
     */
    public JWHTMLPage(String title, Charset charset) {
        html = new StringBuilder();
        setDefaultTags();
        doc = Jsoup.parse(html.toString());
        doc.charset(charset);
        head = doc.head();
        body = doc.body();
        head.append("<title>" + title + "</title>");
        updateHTML();
    }

    /**
     * Initializes the array of JWTag objects.
     */
    private void setDefaultTags() {
        html.append(JWTag.doctype);
        JWTag[] mainTags = new JWTag[] {
            new JWTag("html"),
            new JWTag("head"),
            new JWTag("body"),
        }; 
        for (JWTag t : mainTags) {
            html.append(t);
        }
    }
    
    @Override
    public void addCSS(String link) {
        head.append("<link rel=\"stylesheet\" href=\"" + link + "\" type=\"text/css\">");
        updateHTML();
    }

    @Override
    public void addJS(String link) {
        head.append("<script src=\"" + link + "\"></script>");
        updateHTML();
    }

    @Override
    public void setViewport(String content) {
        head.append("<meta name=\"viewport\" content=\"" + content + "\" />");
        updateHTML();
    }

    @Override
    public void appendToBody(String html) {
        body.append(html);
        updateHTML();
    }

    @Override
    public final void updateHTML() {
        setHTML(new StringBuilder(getDoc().html()));
        setDoc(Jsoup.parse(getHTML().toString()));
        head = doc.head();
        body = doc.body();
    }
    
    @Override
    public String toString() {
        return getHTML().toString();
    }
    
    /**
     * Returns the html of the HTML-like object.
     * @return String
     */
    public StringBuilder getHTML() {
        return html;
    }

    /**
     * Sets the html of the HTML-like object.
     * @param html String
     */
    public void setHTML(StringBuilder html) {
        this.html = html;
    }

    /**
     * Returns the array of JWTag objects that comprise the html of the page.
     * @return JWTag[]
     */
    public JWTag[] getTags() {
        return tags;
    }
    
    /**
     * Returns the Jsoup doc of the HTML-like object.
     * @return Document
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * Sets the Jsoup doc of the HTML-like object.
     * @param doc Document
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     * Returns the Jsoup head element of the HTML-like object.
     * @return Element
     */
    public Element getHead() {
        return head;
    }

    /**
     * Sets the Jsoup head element of the HTML-like object.
     * @param head Element
     */
    public void setHead(Element head) {
        this.head = head;
    }

    /**
     * Returns the Jsoup body element of the HTML-like object.
     * @return Element
     */
    public Element getBody() {
        return body;
    }

    /**
     * Sets the Jsoup head element of the HTML-like object.
     * @param body Element
     */
    public void setBody(Element body) {
        this.body = body;
    }

}
