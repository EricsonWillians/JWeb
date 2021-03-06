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

/**
 * An interface for HTML-like objects.
 * @author EricsonWillians
 * Developer at JWillians.
 */

public interface JWDisplayable {
    
    /**
     * Adds a Cascading Style Sheet link to the given HTML-like object.
     * @param link The link to the css.
     */
    public void addCSS(String link);

    /**
     * Adds a link to a JavaScript file to the given HTML-like object.
     * @param link The link to the javascript file.
     */
    public void addJS(String link);

    /**
     * Sets the viewport content in the given HTML-like object.
     * @param content The content of the viewport meta tag.
     */
    public void setViewport(String content);

    /**
     * Appends HTML to the body of the given HTML-like object.
     * @param html The piece of HTML as a string to be appended.
     */
    public void appendToBody(String html);

    /**
     * Updates the inner HTML String of the given HTML-like object.
     */
    public void updateHTML();
    
}
