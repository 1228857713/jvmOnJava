/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Copyright (c) 2000 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See W3C License http://www.w3.org/Consortium/Legal/ for more
 * details.
 */

package org.w3c.dom.html;

/**
 *  The object used to represent the <code>TH</code> and <code>TD</code>
 * elements. See the  TD element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLTableCellElement extends HTMLElement {
    /**
     *  The index of this cell in the row, starting from 0. This index is in
     * document tree order and not display order.
     */
    public int getCellIndex();

    /**
     *  Abbreviation for header cells. See the  abbr attribute definition in
     * HTML 4.0.
     */
    public String getAbbr();
    public void setAbbr(String abbr);

    /**
     *  Horizontal alignment of data in cell. See the  align attribute
     * definition in HTML 4.0.
     */
    public String getAlign();
    public void setAlign(String align);

    /**
     *  Names group of related headers. See the  axis attribute definition in
     * HTML 4.0.
     */
    public String getAxis();
    public void setAxis(String axis);

    /**
     *  Cell background color. See the  bgcolor attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    public String getBgColor();
    public void setBgColor(String bgColor);

    /**
     *  Alignment character for cells in a column. See the  char attribute
     * definition in HTML 4.0.
     */
    public String getCh();
    public void setCh(String ch);

    /**
     *  Offset of alignment character. See the  charoff attribute definition
     * in HTML 4.0.
     */
    public String getChOff();
    public void setChOff(String chOff);

    /**
     *  Number of columns spanned by cell. See the  colspan attribute
     * definition in HTML 4.0.
     */
    public int getColSpan();
    public void setColSpan(int colSpan);

    /**
     *  List of <code>id</code> attribute values for header cells. See the
     * headers attribute definition in HTML 4.0.
     */
    public String getHeaders();
    public void setHeaders(String headers);

    /**
     *  Cell height. See the  height attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    public String getHeight();
    public void setHeight(String height);

    /**
     *  Suppress word wrapping. See the  nowrap attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    public boolean getNoWrap();
    public void setNoWrap(boolean noWrap);

    /**
     *  Number of rows spanned by cell. See the  rowspan attribute definition
     * in HTML 4.0.
     */
    public int getRowSpan();
    public void setRowSpan(int rowSpan);

    /**
     *  Scope covered by header cells. See the  scope attribute definition in
     * HTML 4.0.
     */
    public String getScope();
    public void setScope(String scope);

    /**
     *  Vertical alignment of data in cell. See the  valign attribute
     * definition in HTML 4.0.
     */
    public String getVAlign();
    public void setVAlign(String vAlign);

    /**
     *  Cell width. See the  width attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    public String getWidth();
    public void setWidth(String width);

}
