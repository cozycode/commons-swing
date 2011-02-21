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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;

public class JTitledSeparator extends JLabel {
   private static final long serialVersionUID = 1L;

   public JTitledSeparator( String title ) {
      super( title );
      setUI( new BasicLabelUI(){
         @Override
         protected void paintEnabledText(JLabel lbl, Graphics g, String s, int textX, int textY) {
            super.paintEnabledText(lbl, g, s, textX, textY);

            Dimension size = lbl.getSize();
            Rectangle2D bounds = g.getFontMetrics().getStringBounds( s, g );

            int lineStart = textX + (int)bounds.getWidth() + 5;
            int lineStop = size.width - 5;
            int y = size.height / 2;            

            g.drawLine( lineStart, y, lineStop, y );
         }
      });
   }

}
