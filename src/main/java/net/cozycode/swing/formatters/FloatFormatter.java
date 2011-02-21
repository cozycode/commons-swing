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

package net.cozycode.swing.formatters;

import java.text.ParseException;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;

import net.cozycode.swing.text.FloatDocumentFilter;

public class FloatFormatter extends DefaultFormatter {
   private static final long serialVersionUID = 1L;
   private final FloatDocumentFilter filter;

   public FloatFormatter( boolean allowsNegative ) {
      this.filter = new FloatDocumentFilter( allowsNegative );
      setAllowsInvalid( true );
      setCommitsOnValidEdit( true );
      setOverwriteMode( false );
      setValueClass( Float.class );
   }

   @Override
   public Object stringToValue(String string) throws ParseException {
      if( "".equals( string ) || "-".equals( string ) || ".".equals( string ) || "-.".equals( string )) {
         return 0f;
      }
      return Float.valueOf( string );
   }

   @Override
   protected DocumentFilter getDocumentFilter() {
      return filter;
   }

}
