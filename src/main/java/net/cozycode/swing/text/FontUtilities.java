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

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.font.TextAttribute;
import java.awt.font.TransformAttribute;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.HashMap;
import java.util.Map;

import net.cozycode.constructs.Tuple2;

public final class FontUtilities {
   private FontUtilities() { /* Static Singleton */ }


   //////  GETTERS  /////////////////////////////////////////////////

   public static boolean isSet( Font font, Attribute attribute ) {
      return font.getAttributes().containsKey( attribute );
   }

   public static String getFamily( Font font ) {
      return font.getFamily();
   }

   public static Number getWeight( Font font ) {
      return getAsNumber( font, TextAttribute.WEIGHT );
   }

   public static Number getSize( Font font ) {
      return getAsNumber( font, TextAttribute.SIZE );
   }

   public static Number getWidth( Font font ) {
      return getAsNumber( font, TextAttribute.WIDTH );
   }

   public static Number getPosture( Font font ) {
      return getAsNumber( font, TextAttribute.POSTURE );
   }

   public static Number getUnderline( Font font ) {
      return getAsNumber( font, TextAttribute.UNDERLINE );
   }

   public static Boolean getStrikethrough( Font font ) {
      return getAsBoolean( font, TextAttribute.STRIKETHROUGH );
   }

   public static Number getSuperscript( Font font ) {
      return getAsNumber( font, TextAttribute.SUPERSCRIPT );
   }

   public static Number getKerning( Font font ) {
      return getAsNumber( font, TextAttribute.KERNING );
   }

   public static Number getTracking( Font font ) {
      return getAsNumber( font, TextAttribute.TRACKING );
   }

   public static Number getLigatures( Font font ) {
      return getAsNumber( font, TextAttribute.LIGATURES );
   }

   public static Boolean getSwapColors( Font font ) {
      return getAsBoolean( font, TextAttribute.SWAP_COLORS );
   }

   public static Paint getForeground( Font font ) {
      return getAsPaint( font, TextAttribute.FOREGROUND );
   }

   public static Paint getBackground( Font font ) {
      return getAsPaint( font, TextAttribute.BACKGROUND );
   }

   public static Boolean getRunDirection( Font font ) {
      return getAsBoolean( font, TextAttribute.RUN_DIRECTION );
   }

   public static TransformAttribute getTransform( Font font ) {
      Object obj = font.getAttributes().get( TextAttribute.TRANSFORM );
      return obj instanceof TransformAttribute ? (TransformAttribute)obj : null;
   }

   public static Number getBidiEmbedding( Font font, Attribute attribute ) {
      return getAsNumber( font, TextAttribute.BIDI_EMBEDDING );
   }

   public static Number getAsNumber( Font font, Attribute attribute ) {
      Object obj = font.getAttributes().get( attribute );
      return obj instanceof Number ? (Number)obj : null;
   }

   public static Boolean getAsBoolean( Font font, Attribute attribute ) {
      Object obj = font.getAttributes().get( attribute );
      return obj instanceof Boolean ? (Boolean)obj : null;
   }

   public static Paint getAsPaint( Font font, Attribute attribute ) {
      Object obj = font.getAttributes().get( attribute );
      return obj instanceof Paint ? (Paint)obj : null;
   }


   //////  SETTERS  /////////////////////////////////////////////////

   public static Font deriveFamily( Font font, String name ) {
      return deriveFont( font, TextAttribute.FAMILY, name );
   }

   /**
    * Derives a new font using the specified font weight.
    * While any positive float value can be supplied,
    * there are several default values are provided:
    * <ul>
    *   <li>TextAttribute.WEIGHT_EXTRA_LIGHT</li>
    *   <li>TextAttribute.WEIGHT_LIGHT</li>
    *   <li>TextAttribute.WEIGHT_DEMILIGHT</li>
    *   <li>TextAttribute.WEIGHT_REGULAR</li>
    *   <li>TextAttribute.WEIGHT_SEMIBOLD</li>
    *   <li>TextAttribute.WEIGHT_MEDIUM</li>
    *   <li>TextAttribute.WEIGHT_DEMIBOLD</li>
    *   <li>TextAttribute.WEIGHT_BOLD</li>
    *   <li>TextAttribute.WEIGHT_HEAVY</li>
    *   <li>TextAttribute.WEIGHT_EXTRABOLD</li>
    *   <li>TextAttribute.WEIGHT_ULTRABOLD</li>
    * </ul>
    * 
    * @param font - The font to derive from
    * @param weight - The weight of the derived font
    * @return the derived font
    */
   public static Font deriveWeight( Font font, Float weight ) {
      return deriveFont( font, TextAttribute.WEIGHT, weight );
   }

   /**
    * Derives a new font using the specified font width.
    * while any positive float value can be supplied,
    * there are several default values provided:
    * <ul>
    *   <li>TextAttribute.WIDTH_CONDENSED</li>
    *   <li>TextAttribute.WIDTH_SEMI_CONDENSED</li>
    *   <li>TextAttribute.WIDTH_REGULAR</li>
    *   <li>TextAttribute.WIDTH_SEMI_EXTENDED</li>
    *   <li>TextAttribute.WIDTH_EXTENDED</li>
    * </ul>
    * @param font - The font to derive from
    * @param width - The width of the derived font
    * @return the derived font
    */
   public static Font deriveWidth( Font font, Float width ) {
      return deriveFont( font, TextAttribute.WIDTH, width );
   }

   /**
    * In layman's terms, this is italic slope of the font.
    * The value is roughly the slope of the stems of the font, 
    * expressed as the run over the rise. Positive values lean right. 
    * <p>
    * There are a couple default values:
    * <ul>
    *   <li>TextAttribute.POSTURE_REGULAR</li>
    *   <li>TextAttribute.POSTURE_OBLIQUE</li>
    * </ul>
    * 
    * @param font - The font to derive from
    * @param posture - the posture of the derived font
    * @return the derived font
    */
   public static Font derivePosture( Font font, Float posture ) {
      return deriveFont( font, TextAttribute.POSTURE, posture );
   }

   /**
    * Derives a new font using the specified underline style,
    * The underline style should be one of:
    * <ul>
    *   <li>TextAttribute.UNDERLINE_ON</li>
    *   <li>TextAttribute.UNDERLINE_LOW_ONE_PIXEL</li>
    *   <li>TextAttribute.UNDERLINE_LOW_TWO_PIXEL</li>
    *   <li>TextAttribute.UNDERLINE_LOW_DASHED</li>
    *   <li>TextAttribute.UNDERLINE_LOW_DOTTED</li>
    *   <li>TextAttribute.UNDERLINE_LOW_GRAY</li>
    * <ul>
    * 
    * @param font - The font to derive from
    * @param style - The underline style
    * @return the derived font
    */
   public static Font deriveUnderline( Font font, Integer style ) {
      return deriveFont( font, TextAttribute.UNDERLINE, style );
   }

   /**
    * Derives a new font with the specified strikethrough value.
    */
   public static Font deriveStrikethrough( Font font, Boolean enabled ) {
      return deriveFont( font, TextAttribute.STRIKETHROUGH, enabled );
   }

   /**
    * Derives a new font with the specified superscript/subscript value.
    * Negative values are subscripts and positive values superscripts.
    * <p>
    * The following default values are provided:
    * <ul>
    *   <li>TextAttribute.SUPERSCRIPT_SUPER</li>
    *   <li>TextAttribute.SUPERSCRIPT_SUB</li>
    * </ul>
    *
    * @param font
    * @param value
    * @return
    */
   public static Font deriveSuperscript( Font font, Integer value ) {
      return deriveFont( font, TextAttribute.SUPERSCRIPT, value );
   }

   /**
    * Derives a font with the requested kerning style.
    * <p>
    * Currently, the only supported values are:
    * <ul>
    *   <li>TextAttribute.KERNING_ON</li>
    * <ul>
    * 
    * @param font
    * @param flag
    * @return
    */
   public static Font deriveKerning( Font font, Integer flag ) {
      return deriveFont( font, TextAttribute.KERNING, flag );
   }

   /**
    * Derives a font with the requested tracking value.
    * <p>
    * The tracking value is multiplied by the font point size and
    * passed through the font transform to determine an additional
    * amount to add to the advance of each glyph cluster.  Positive
    * tracking values will inhibit formation of optional ligatures.
    * Tracking values are typically between <code>-0.1</code> and
    * <code>0.3</code>; values outside this range are generally not
    * desirable. (Note: this excerpt was from TextAttribute.TRACKING)
    * <p>
    * The following constants are provided:
    * <ul>
    *   <li>TextAttributes.TRACKING_TIGHT</li>
    *   <li>TextAttributes.TRACKING_LOOSE</li>
    * </ul>
    */
   public static Font deriveTracking( Font font, Float value ) {
      return deriveFont( font, TextAttribute.TRACKING, value );
   }

   /**
    * Derives a font which enables or disables ligatures.
    * <p>
    * Currently available values are:
    * <ul>
    *   <li>TextAttributes.LIGATURES_ON</li>
    * </ul>
    */
   public static Font deriveLigatures( Font font, Integer flag ) {
      return deriveFont( font, TextAttribute.LIGATURES, flag );
   }

   /**
    * Derives a font which enables or disables color swapping.
    * If color swapping is enabled then the paints in FOREGROUND
    * or BACKGROUND will override the paint used by the Graphics
    * object used to render the font.
    * 
    * @param font - the font to derive from
    * @param isEnabled - if true sets SWAP_COLORS to SWAP_COLORS_ON
    * @return the derived font
    */
   public static Font deriveSwapColors( Font font, boolean isEnabled ) {
      Object value = isEnabled ? TextAttribute.SWAP_COLORS_ON : null;
      return deriveFont( font, TextAttribute.SWAP_COLORS, value );
   }

   /**
    * Derives a font which uses the specified foreground paint
    */
   public static Font deriveForeground( Font font, Paint paint ) {
      return deriveFont( font, TextAttribute.FOREGROUND, paint );
   }

   /**
    * Derives a font which uses the specified background paint
    */
   public static Font deriveBackground( Font font, Paint paint ) {
      return deriveFont( font, TextAttribute.BACKGROUND, paint );
   }

   /**
    * Derives a new font using the specified font size
    * 
    * @param font - The font to derive from
    * @param size - The the size of the derived font
    * @return the derived font
    */
   public static Font deriveSize( Font font, Number size ) {
      return deriveFont( font, TextAttribute.SIZE, size );
   }

   /**
    * Lets you override the run direction of the font.
    * See TextAttribute.RUN_DIRECTION for details on how to use this.
    * <p>
    * Available values are:
    * <ul>
    *   <li>TextAttribute.RUN_DIRECTION_LTR</li>
    *   <li>TextAttribute.RUN_DIRECTION_RTL</li>
    * </ul>
    */
   public static Font deriveRunDirection( Font font, Boolean direction ) {
      return deriveFont( font, TextAttribute.RUN_DIRECTION, direction );
   }

   /**
    * Derives a new font using the specified transform.
    * <p>
    * See TextAttribute.TRANSFORM for more details.
    */
   public static Font deriveTransform( Font font, TransformAttribute transform ) {
      return deriveFont( font, TextAttribute.TRANSFORM, transform );
   }

   /**
    * See TextAttribute.BIDI_EMBEDDING for details on how to use this.
    */
   public static Font deriveBidiEmbedding( Font font, Integer level ) {
      return deriveFont( font, TextAttribute.BIDI_EMBEDDING, level );
   }

   /**
    * Derives a new font using the specified key, value pair
    * 
    * @param font - the font to derive from.
    * @param key - the Attribute key to change
    * @param value - The new value to set
    * @return the derived font
    */
   public static Font deriveFont( Font font, Attribute key, Object value ) {
      Map<Attribute, Object> attribs = getAttributes( font );
      attribs.put( key, value );
      return new Font( attribs );
   }

   /**
    * Derives a new font from a list of (Attribute,value) pairs.
    * 
    * @param font - The font to derive from
    * @param derivations - a list of derivations specified as (Attribute,value) pairs.
    * @return the derived font
    */
   public static Font deriveFont( Font font, Tuple2<Attribute,Object>... derivations ) {
      Map<Attribute, Object> attribs = getAttributes( font );
      for( Tuple2<Attribute,Object> item : derivations ) {
         attribs.put( item.getFirst(), item.getSecond() );
      }
      return new Font( attribs );
   }



   //////  OTHER HELPER METHODS  ////////////////////////////////////

   /**
    * Returns a map of known types rather than the unusable (poorly typed) map returned by Font.
    */
   public static Map<Attribute, Object> getAttributes( Font font ) {
      return new HashMap<Attribute, Object>( font.getAttributes() );
   }

   /**
    * @return all available fonts
    */
   public static Font[] getAvailableFonts() {
      return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
   }

   /**
    * @return The names of all available fonts
    */
   public static String[] getAvailableFontNames() {
      return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
   }
}
