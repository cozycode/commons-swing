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

package net.cozycode.swing.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public final class FocusListeners {
   private FocusListeners() { /* Static Singleton */ }

   public static FocusListener SELECT_ALL = new SelectAll();


   private static final class SelectAll implements FocusListener {
      @Override
      public void focusGained( final FocusEvent e) {
         if( e.getSource() instanceof JTextComponent ) {

            // The invoke later is required or some text components
            // will reset the selection after this is invoked.
            SwingUtilities.invokeLater( new Runnable() {
               @Override
               public void run() {
                  ((JTextComponent)e.getSource()).selectAll();
               }
            });
         }
      }

      @Override
      public void focusLost(FocusEvent e) { /* noop */ }
   }
}
