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

package net.cozycode.swing.components;

import javax.swing.JLabel;

public class BooleanLabel extends JLabel {
   private static final long serialVersionUID = 1L;
   private final String trueText;
   private final String falseText;

   public BooleanLabel() {
      this( "true", "false" );
   }

   public BooleanLabel( String trueText, String falseText ) {
      this.trueText = trueText;
      this.falseText = falseText;
   }

   public void setBoolean( boolean value ) {
      setText( value ? trueText : falseText );
   }
}
