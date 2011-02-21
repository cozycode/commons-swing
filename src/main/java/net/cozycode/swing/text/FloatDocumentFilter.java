/*
 * Copyright (C) 2011 Cozycode.net
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cozycode.swing.text;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class FloatDocumentFilter extends DocumentFilter {
   private static final Pattern POS_PATTERN = Pattern.compile( "[0-9]*\\.?[0-9]*" );
   private static final Pattern NEG_PATTERN = Pattern.compile( "-?[0-9]*\\.?[0-9]*" );

   private final boolean allowsNegative;

   /**
    * @param precision - Maximum number of decimal places
    * @param allowsNegative - Whether negative values are acceptable
    */
   public FloatDocumentFilter( boolean allowsNegative ) {
      this.allowsNegative = allowsNegative;
   }

   protected Pattern getPattern() {
      return allowsNegative ? NEG_PATTERN : POS_PATTERN;
   }

   protected boolean matches( String text ) {
      return getPattern().matcher( text ).matches();
   }

   protected boolean misplacedNegative( String text, int offset ) {
      return text.length() > 0 && text.charAt(0) == '-' && offset != 0;
   }

   protected boolean multipleDecimals( FilterBypass fb, int offset, int length, String text ) throws BadLocationException {
      Document doc = fb.getDocument();
      String s = doc.getText(0, doc.getLength() );

      boolean prefixHasDecimal = s.substring(0, offset).indexOf( '.') != -1 ;
      boolean suffixHasDecimal = s.substring( offset + length ).indexOf( '.' ) != -1;

      boolean insertingDecimal = text.indexOf( '.' ) != -1;

      return (prefixHasDecimal || suffixHasDecimal) && insertingDecimal;
   }

   @Override
   public void insertString( FilterBypass fb, int offset, String text, AttributeSet attrs ) throws BadLocationException {
      if( matches( text ) && !misplacedNegative( text, offset ) && !multipleDecimals( fb, offset, 0, text )) {
         super.insertString( fb, offset, text, attrs );
         checkUnderOverflow( fb, attrs );
      }
   }

   @Override
   public void replace( FilterBypass fb, int offset, int length, String text, AttributeSet attrs ) throws BadLocationException {
      if( matches( text ) && !misplacedNegative( text, offset ) && !multipleDecimals( fb, offset, length, text )) {
         super.replace(fb, offset, length, text, attrs);
         checkUnderOverflow( fb, attrs );
      }
   }

   private final void checkUnderOverflow( FilterBypass fb, AttributeSet attr ) throws BadLocationException {
      Document doc = fb.getDocument();
      String s = doc.getText(0, doc.getLength() );

      if( "".equals(s) || "-".equals(s) || ".".equals(s) || "-.".equals(s) ) {
         return;
      }

      double value = Double.valueOf( s );

      if( value > 0 ) {
         if( value > Float.MAX_VALUE ) {
            fb.replace(0, doc.getLength(), Float.toString( Float.MAX_VALUE ), attr );
         }
         if( value < Float.MIN_VALUE ) {
            fb.replace(0, doc.getLength(), Float.toString( Float.MIN_VALUE ), attr );
         }
      }
      else if( value < 0 ) {
         if( value < -Float.MAX_VALUE ) {
            fb.replace(0, doc.getLength(), Float.toString( -Float.MAX_VALUE ), attr );
         }
         if( value > -Float.MIN_VALUE ) {
            fb.replace(0, doc.getLength(), Float.toString( -Float.MIN_VALUE ), attr );
         }
      }

      Float.valueOf( s );
   }
}
