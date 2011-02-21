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

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class FormattedComboBoxEditor implements ComboBoxEditor {
   protected final JFormattedTextField editor;

   public FormattedComboBoxEditor( AbstractFormatter formatter ) {
      editor = new JFormattedTextField( formatter );
      editor.setBorder( BorderFactory.createEmptyBorder() );
   }

   public FormattedComboBoxEditor( AbstractFormatter formatter, int columns ) {
      this( formatter );
      editor.setColumns( columns );
   }


   @Override
   public JFormattedTextField getEditorComponent() {
      return editor;
   }

   @Override
   public Object getItem() {
      return editor.getValue();
   }

   @Override
   public void setItem( Object value ) {
      editor.setValue( value );
   }


   @Override
   public void selectAll() {
      editor.selectAll();
      editor.requestFocus();
   }

   @Override
   public void addActionListener(ActionListener l) {
      editor.addActionListener( l );
   }

   @Override
   public void removeActionListener(ActionListener l) {
      editor.removeActionListener( l );
   }
}
