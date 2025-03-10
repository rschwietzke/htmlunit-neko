/*
 * Copyright (c) 2017-2024 Ronald Brill
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.htmlunit.cyberneko.xerces.xni;

/**
 * The XMLAttributes interface defines a collection of attributes for an
 * element. In the parser, the document source would scan the entire start
 * element and collect the attributes. The attributes are communicated to the
 * document handler in the startElement method.
 * <p>
 * The attributes are read-write so that subsequent stages in the document
 * pipeline can modify the values or change the attributes that are propagated
 * to the next stage.
 *
 * @see XMLDocumentHandler#startElement
 *
 * @author Andy Clark, IBM
 */
public interface XMLAttributes {

    /**
     * Adds an attribute. The attribute's non-normalized value of the attribute will
     * have the same value as the attribute value until. Also, the added attribute
     * will be marked as specified in the XML instance document unless set otherwise
     * using the <code>setSpecified</code> method.
     * <p>
     * <strong>Note:</strong> If an attribute of the same name already exists, the
     * old values for the attribute are replaced by the new values.
     *
     * @param attrName  The attribute name.
     * @param attrType  The attribute type. The type name is determined by the type
     *                  specified for this attribute in the DTD. For example:
     *                  "CDATA", "ID", "NMTOKEN", etc. However, attributes of type
     *                  enumeration will have the type value specified as the pipe
     *                  ('|') separated list of the enumeration values prefixed by
     *                  an open parenthesis and suffixed by a close parenthesis. For
     *                  example: "(true|false)".
     * @param attrValue The attribute value.
     *
     * @return Returns the attribute index.
     *
     * @see #setSpecified(int, boolean)
     */
    int addAttribute(QName attrName, String attrType, String attrValue);

    /**
     * Removes all of the attributes. This method will also remove all entities
     * associated to the attributes.
     */
    void removeAllAttributes();

    /**
     * Removes the attribute at the specified index.
     * <p>
     * <strong>Note:</strong> This operation changes the indexes of all attributes
     * following the attribute at the specified index.
     *
     * @param attrIndex The attribute index.
     */
    void removeAttributeAt(int attrIndex);

    /**
     * @return the number of attributes in the list.
     *         <p>
     *         Once you know the number of attributes, you can iterate through the
     *         list.
     *
     * @see #getURI(int)
     * @see #getLocalName(int)
     * @see #getQName(int)
     * @see #getType(int)
     * @see #getValue(int)
     */
    int getLength();

    /**
     * Look up the index of an attribute by XML 1.0 qualified name.
     *
     * @param qName The qualified (prefixed) name.
     *
     * @return The index of the attribute, or -1 if it does not appear in the list.
     */
    int getIndex(String qName);

    /**
     * Look up the index of an attribute by Namespace name.
     *
     * @param uri       The Namespace URI, or the empty string if the name has no
     *                  Namespace URI.
     * @param localPart The attribute's local name.
     *
     * @return The index of the attribute, or -1 if it does not appear in the list.
     */
    int getIndex(String uri, String localPart);

    /**
     * Sets the name of the attribute at the specified index.
     *
     * @param attrIndex The attribute index.
     * @param attrName  The new attribute name.
     */
    void setName(int attrIndex, QName attrName);

    /**
     * Gets the fields in the given QName structure with the values of the attribute
     * name at the specified index.
     *
     * @param attrIndex The attribute index.
     * @param attrName  The attribute name structure to fill in.
     */
    void getName(int attrIndex, QName attrName);

    /**
     * Returns the QName structure of the name. Because QName is a modifiable
     * data structure, make sure you know what you do when you take this
     * shortcut route.
     *
     * @param attrIndex The attribute index.
     */
    QName getName(int attrIndex);

    /**
     * Look up an attribute's Namespace URI by index.
     *
     * @param index The attribute index (zero-based).
     *
     * @return The Namespace URI, or the empty string if none is available, or null
     *         if the index is out of range.
     *
     * @see #getLength()
     */
    String getURI(int index);

    /**
     * Look up an attribute's local name by index.
     *
     * @param index The attribute index (zero-based).
     *
     * @return The local name, or the empty string if Namespace processing is not
     *         being performed, or null if the index is out of range.
     *
     * @see #getLength()
     */
    String getLocalName(int index);

    /**
     * Look up an attribute's XML 1.0 qualified name by index.
     *
     * @param index The attribute index (zero-based).
     *
     * @return The XML 1.0 qualified name, or the empty string if none is available,
     *         or null if the index is out of range.
     *
     * @see #getLength()
     */
    String getQName(int index);

    /**
     * Sets the type of the attribute at the specified index.
     *
     * @param attrIndex The attribute index.
     * @param attrType  The attribute type. The type name is determined by the type
     *                  specified for this attribute in the DTD. For example:
     *                  "CDATA", "ID", "NMTOKEN", etc. However, attributes of type
     *                  enumeration will have the type value specified as the pipe
     *                  ('|') separated list of the enumeration values prefixed by
     *                  an open parenthesis and suffixed by a close parenthesis. For
     *                  example: "(true|false)".
     */
    void setType(int attrIndex, String attrType);

    /**
     * Look up an attribute's type by index.
     * <p>
     * The attribute type is one of the strings "CDATA", "ID", "IDREF", "IDREFS",
     * "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES", or "NOTATION" (always in upper
     * case).
     * <p>
     * If the parser has not read a declaration for the attribute, or if the parser
     * does not report attribute types, then it must return the value "CDATA" as
     * stated in the XML 1.0 Recommendation (clause 3.3.3, "Attribute-Value
     * Normalization").
     * <p>
     * For an enumerated attribute that is not a notation, the parser will report
     * the type as "NMTOKEN".
     *
     * @param index The attribute index (zero-based).
     *
     * @return The attribute's type as a string, or null if the index is out of
     *         range.
     *
     * @see #getLength()
     */
    String getType(int index);

    /**
     * Look up an attribute's type by XML 1.0 qualified name.
     * <p>
     * See {@link #getType(int) getType(int)} for a description of the possible
     * types.
     *
     * @param qName The XML 1.0 qualified name.
     *
     * @return The attribute type as a string, or null if the attribute is not in
     *         the list or if qualified names are not available.
     */
    String getType(String qName);

    /**
     * Look up an attribute's type by Namespace name.
     * <p>
     * See {@link #getType(int) getType(int)} for a description of the possible
     * types.
     *
     * @param uri       The Namespace URI, or the empty String if the name has no
     *                  Namespace URI.
     * @param localName The local name of the attribute.
     *
     * @return The attribute type as a string, or null if the attribute is not in
     *         the list or if Namespace processing is not being performed.
     */
    String getType(String uri, String localName);

    /**
     * Sets the value of the attribute at the specified index. This method will
     * overwrite the non-normalized value of the attribute.
     *
     * @param attrIndex The attribute index.
     * @param attrValue The new attribute value.
     */
    void setValue(int attrIndex, String attrValue);

    /**
     * Look up an attribute's value by index.
     * <p>
     * If the attribute value is a list of tokens (IDREFS, ENTITIES, or NMTOKENS),
     * the tokens will be concatenated into a single string with each token
     * separated by a single space.
     *
     * @param index The attribute index (zero-based).
     *
     * @return The attribute's value as a string, or null if the index is out of
     *         range.
     *
     * @see #getLength()
     */
    String getValue(int index);

    /**
     * Look up an attribute's value by XML 1.0 qualified name.
     * <p>
     * See {@link #getValue(int) getValue(int)} for a description of the possible
     * values.
     *
     * @param qName The XML 1.0 qualified name.
     *
     * @return The attribute value as a string, or null if the attribute is not in
     *         the list or if qualified names are not available.
     */
    String getValue(String qName);

    /**
     * Look up an attribute's value by Namespace name.
     * <p>
     * See {@link #getValue(int) getValue(int)} for a description of the possible
     * values.
     *
     * @param uri       The Namespace URI, or the empty String if the name has no
     *                  Namespace URI.
     * @param localName The local name of the attribute.
     *
     * @return The attribute value as a string, or null if the attribute is not in
     *         the list.
     */
    String getValue(String uri, String localName);

    /**
     * Sets whether an attribute is specified in the instance document or not.
     *
     * @param attrIndex The attribute index.
     * @param specified True if the attribute is specified in the instance document.
     */
    void setSpecified(int attrIndex, boolean specified);

    /**
     * @return true if the attribute is specified in the instance document.
     *
     * @param attrIndex The attribute index.
     */
    boolean isSpecified(int attrIndex);
}
