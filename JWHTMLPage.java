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
 *
 * @author EricsonWillians
 * JWHTMLPage.
 * An editable HTML-like object.
 */
@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPage implements JWDisplayable {

    private String html;
    private Document doc;
    private Element head;
    private Element body;
    
    /**
     *
     * @param title
     * @param charset
     * HTML title and default encoding.
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
     *
     * @return
     * Returns the html of the HTML-like object.
     */
    public String getHTML() {
        return html;
    }

    /**
     *
     * @param html
     * Sets the html of the HTML-like object.
     */
    public void setHTML(String html) {
        this.html = html;
    }

    /**
     *
     * @return
     * Returns the doc of the HTML-like object.
     */
    public Document getDoc() {
        return doc;
    }

    /**
     *
     * @param doc
     * Sets the doc of the HTML-like object.
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     *
     * @return
     * Returns the Jsoup head element of the HTML-like object.
     */
    public Element getHead() {
        return head;
    }

    /**
     *
     * @param head
     * Sets the Jsoup head element of the HTML-like object.
     */
    public void setHead(Element head) {
        this.head = head;
    }

    /**
     *
     * @return
     * Returns the Jsoup body element of the HTML-like object.
     */
    public Element getBody() {
        return body;
    }

    /**
     *
     * @param body
     * Sets the Jsoup head element of the HTML-like object.
     */
    public void setBody(Element body) {
        this.body = body;
    }

}
