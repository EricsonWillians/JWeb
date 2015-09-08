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

package JWeb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.nio.charset.Charset;

@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPage implements JWDisplayable {

    private final String title;
    private String html;
    private Document doc;
    private Element head;
    private Element body;
    
    public JWHTMLPage(String title, Charset charset) {
        this.title = title;
        html = "<!DOCTYPE html><html><head></head><body></body></html>";
        doc = Jsoup.parse(html);
        doc.charset(charset);
        head = doc.head();
        body = doc.body();
    }

    @Override
    public void addCSS(String link) {
        head.append("<link rel=\"stylesheet\" href=\"" + link + "\" type=\"text/css\">");
    }

    @Override
    public void addJS(String link) {
        head.append("<script src=\"" + link + "\"></script>");
    }

    @Override
    public void setViewport(String content) {
        head.append("<meta name=\"viewport\" content=\"" + content + "\" />");
    }

    @Override
    public void appendToBody(String html) {
        body.append(html);
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Element getHead() {
        return head;
    }

    public void setHead(Element head) {
        this.head = head;
    }

    public Element getBody() {
        return body;
    }

    public void setBody(Element body) {
        this.body = body;
    }
    
}
