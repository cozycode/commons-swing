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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import net.cozycode.swing.text.FontUtilities;

public class LinkButton extends JButton {
   private static final long serialVersionUID = 1L;

   private static final Color HOVER_COLOR = new Color( 0, 0, 128 );
   private static final Color NORMAL_COLOR = new Color( 0, 0, 255 );
   private static final LinkColorChanger colorChanger = new LinkColorChanger();

   public LinkButton( String text ) {
      super( text );
      setUI( new BasicButtonUI() );

      setFont( FontUtilities.deriveUnderline( getFont(), TextAttribute.UNDERLINE_ON ));
      setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
      setForeground( NORMAL_COLOR );
      setBackground( null );
      setBorder( null );

      addMouseListener( colorChanger );
   }

   private static class LinkColorChanger extends MouseAdapter {
      @Override
      public void mouseEntered(MouseEvent e) {
         e.getComponent().setForeground( HOVER_COLOR );
      }

      @Override
      public void mouseExited(MouseEvent e) {
         e.getComponent().setForeground( NORMAL_COLOR );
      }
   }
}
