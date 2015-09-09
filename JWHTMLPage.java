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
 * @author EricsonWillians
 * Developer at JWillians.
 */
@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPage implements JWDisplayable {

    private String html;
    private Document doc;
    private Element head;
    private Element body;
    
    /**
     * JWHTMLPage Constructor.
     * @param title - String
     * @param charset - Charset
     */
    public JWHTMLPage(String title, Charset charset) {
        html = "<!DOCTYPE html><html><head></head><body></body></html>";
        doc = Jsoup.parse(html);
        doc.charset(charset);
        head = doc.head();
        body = doc.body();
        head.append("<title>" + title + "</title>");
        updateHTML();
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
        setHTML(getDoc().html());
        setDoc(Jsoup.parse(getHTML()));
        head = doc.head();
        body = doc.body();
    }
    
    /**
     * Returns the html of the HTML-like object.
     * @return String
     */
    public String getHTML() {
        return html;
    }

    /**
     * Sets the html of the HTML-like object.
     * @param html - String
     */
    public void setHTML(String html) {
        this.html = html;
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
     * @param doc - Document
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
     * @param head - Element
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
     * @param body - Element
     */
    public void setBody(Element body) {
        this.body = body;
    }

}