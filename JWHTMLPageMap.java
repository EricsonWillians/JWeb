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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * JWHTMLPage Associative Array.
 * @author EricsonWillians
 * @param <J> Extends JWHTMLPage.
 */
@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPageMap<J extends JWHTMLPage> implements Map<String, J> , JWDisplayable {
 
    /** The page names array list ("Keys") */
    private ArrayList<String> pageNames;
    /** The pages array list ("Values") */
    private ArrayList<J> pages;
    
    /**
     * Initializes the inner array lists.
     */
    public JWHTMLPageMap() {
        pageNames = new ArrayList();
        pages = new ArrayList();
    }

    @Override
    public int size() {
        return pageNames.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object pageName) {
        return pageNames.contains((String) pageName);
    }
    
    @Override
    public boolean containsValue(Object pageName) {
        return pageNames.contains((String) pageName);
    }
    
    @Override
    public J get(Object pageName) {
        int i = pageNames.indexOf(pageName);
        if (i == -1) {
            return null;
        }
        return (J) pages.get(i);
    }

    @Override
    public J put(String pageName, J page) {
        for (int i = 0; i < pageNames.size(); i++) {
            String old = pageNames.get(i);
            if (((Comparable) pageName).compareTo(pageNames.get(i)) == 0) {
                pageNames.set(i, pageName);
                return pages.get(i);
            }
            if (((Comparable) pageName).compareTo(pageNames.get(i)) == +1) {
                int where = i > 0 ? i -1 : 0;
                pageNames.add(where, pageName);
                pages.add(where, page);
                return null;
            }
        }
        
        pageNames.add(pageName);
        pages.add(page);
        return null;
    }

    @Override
    public J remove(Object pageName) {
        int i = pageNames.indexOf(pageName);
        if (i == -1) {
            return null;
        }
        J old = pages.get(i);
        pageNames.remove(i);
        pages.remove(i);
        return old;
    }

    @Override
    public void putAll(Map<? extends String, ? extends J> m) {
        Iterator pagesIter = m.keySet().iterator();
        while (pagesIter.hasNext()) {
            String pageName = (String) pagesIter.next();
            J page = m.get(pageName);
            put(pageName, page);
        }
    }

    @Override
    public void clear() {
        pageNames.clear();
        pages.clear();
    }

    @Override
    public Set<String> keySet() {
        return new TreeSet(pageNames);
    }

    @Override
    public Collection<J> values() {
        return pages;
    }

    /**
     * Adds a Cascading Style Sheet link to all the JWHTMLPage objects within the Associative Array.
     * @param link String
     */
    @Override
    public void addCSS(String link) {
        pages.stream().forEach((page) -> {
            page.addCSS(link);
        });
    }

    /**
     * Adds a link to a JavaScript file to all the JWHTMLPage objects within the Associative Array.
     * @param link String
     */
    @Override
    public void addJS(String link) {
        pages.stream().forEach((page) -> {
            page.addJS(link);
        });
    }

    /**
     * Sets the contents of the viewports of all the JWHTMLPage objects within the Associative Array.
     * @param content String
     */
    @Override
    public void setViewport(String content) {
        pages.stream().forEach((page) -> {
            page.setViewport(content);
        });
    }

    /**
     * Appends HTML to the body of all the JWHTMLPage objects within the Associative Array.
     * @param html String
     */
    @Override
    public void appendToBody(String html) {
        pages.stream().forEach((page) -> {
            page.appendToBody(html);
        });
    }

    /**
     * Updates the inner HTML String of all the JWHTMLPage objects within the Associative Array.
     */
    @Override
    public void updateHTML() {
        pages.stream().forEach((page) -> {
            page.updateHTML();
        });
    }
    
    @Override
    public Set<Entry<String, J>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
