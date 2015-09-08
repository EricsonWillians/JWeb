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

@SuppressWarnings("FieldMayBeFinal")
public class JWHTMLPageMap<String, JWHTMLPage> implements Map<String, JWHTMLPage> {
 
    private ArrayList<String> pageNames;
    private ArrayList<JWHTMLPage> pages;
    
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
    public JWHTMLPage get(Object pageName) {
        int i = pageNames.indexOf(pageName);
        if (i == -1) {
            return null;
        }
        return pages.get(i);
    }

    @Override
    public Object put(Object pageName, Object page) {
        for (int i = 0; i < pageNames.size(); i++) {
            Object old = pageNames.get(i);
            if (((Comparable) pageName).compareTo(pageNames.get(i)) == 0) {
                pageNames.set(i, (String) pageName);
                return old;
            }
            if (((Comparable) pageName).compareTo(pageNames.get(i)) == +1) {
                int where = i > 0 ? i -1 : 0;
                pageNames.add(where, (String) pageName);
                pages.add(where, (JWHTMLPage) page);
                return null;
            }
        }
        
        pageNames.add((String) pageName);
        pages.add((JWHTMLPage) page);
        return null;
    }

    @Override
    public JWHTMLPage remove(Object pageName) {
        int i = pageNames.indexOf(pageName);
        if (i == -1) {
            return null;
        }
        JWHTMLPage old = pages.get(i);
        pageNames.remove(i);
        pages.remove(i);
        return old;
    }

    @Override
    public void putAll(Map<? extends String, ? extends JWHTMLPage> m) {
        Iterator pagesIter = m.keySet().iterator();
        while (pagesIter.hasNext()) {
            String pageName = (String) pagesIter.next();
            JWHTMLPage page = m.get(pageName);
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
    public Collection<JWHTMLPage> values() {
        return pages;
    }

    @Override
    public Set<Entry<String, JWHTMLPage>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
