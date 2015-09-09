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
 *
 * @author EricsonWillians
 * Developer at JWillians.
 */

public interface JWDisplayable {
    
    /**
     * Add CSS.
     * @param link
     * Adds a Cascading Style Sheet link to the given HTML-like object.
     */
    public void addCSS(String link);

    /**
     * AddJS.
     * @param link
     * Adds a link to a JavaScript file to the given HTML-like object.
     */
    public void addJS(String link);

    /**
     * Set viewport.
     * @param content
     * Sets the viewport content in the given HTML-like object.
     */
    public void setViewport(String content);

    /**
     * Append to body.
     * @param html
     * Appends HTML to the body of the given HTML-like object.
     */
    public void appendToBody(String html);

    /**
     * Updates the inner HTML String of the given HTML-like object.
     */
    public void updateHTML();
    
}
