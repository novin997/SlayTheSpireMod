package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method="renderDescription"
)
public class RenderCustomDynamicVariable
{
    public static void Raw(CtBehavior ctMethodToPatch) throws CannotCompileException
    {
        final int[] insertLine = {-1};
        ctMethodToPatch.instrument(new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException
            {
                if (m.getMethodName().equals("renderDynamicVariable")) {
                    if (insertLine[0] == -1) {
                        insertLine[0] = m.getLineNumber();
                    }
                    m.replace("$_ = basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariable.Inner.myRenderDynamicVariable(this, tmp, $$);");
                }
            }
        });

        if (insertLine[0] > 0) {
            ctMethodToPatch.insertAt(insertLine[0]-1,
                    "if (tmp.length() != 4 && tmp.length() != 5) {" +
                            "start_x += basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCustomDynamicVariable.Inner.myRenderDynamicVariable(this, tmp, tmp.charAt(1), start_x, draw_y, i, font, sb, null);" +
                            "}");
        }
    }

    public static class Inner
    {
        private static Logger logger = LogManager.getLogger();
        private static final GlyphLayout gl = new GlyphLayout();

        public static float myRenderDynamicVariable(Object __obj_instance, String key, char ckey, float start_x, float draw_y, int i, BitmapFont font, SpriteBatch sb, Character cend)
        {
            AbstractCard __instance = (AbstractCard) __obj_instance;
            // Get any private variables we need
            Color textColor = Settings.CREAM_COLOR;
            try {
                Field f = AbstractCard.class.getDeclaredField("textColor");
                f.setAccessible(true);
                textColor = (Color) f.get(__instance);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            String end = "";

            Pattern pattern = Pattern.compile("!(.+)!(.*) ");
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                key = matcher.group(1);
                end = matcher.group(2);
            }

            // Main body of method
            StringBuilder stringBuilder = new StringBuilder();
            Color c = null;
            int num = 0;
            DynamicVariable dv = BaseMod.cardDynamicVariableMap.get(key);
            if (dv != null) {
                if (dv.isModified(__instance)) {
                    num = dv.value(__instance);
                    if (num >= dv.baseValue(__instance)) {
                        c = dv.getIncreasedValueColor();
                    } else {
                        c = dv.getDecreasedValueColor();
                    }
                } else {
                    c = dv.getNormalColor();
                    num = dv.baseValue(__instance);
                }
            } else {
                logger.error("No dynamic card variable found for key \"" + key + "\"!");
                c = textColor;
            }
            stringBuilder.append(num);
            gl.setText(font, stringBuilder.toString());
            FontHelper.renderRotatedText(sb, font, stringBuilder.toString(),
                    __instance.current_x, __instance.current_y,
                    start_x - __instance.current_x + gl.width / 2.0f,
                    i * 1.45f * -font.getCapHeight() + draw_y - __instance.current_y + -6.0f,
                    __instance.angle, true, c);
            if (!end.isEmpty()) {
                FontHelper.renderRotatedText(sb, font, end,
                        __instance.current_x, __instance.current_y,
                        start_x - __instance.current_x + gl.width + 4.0f * Settings.scale,
                        i * 1.45f * -font.getCapHeight() + draw_y - __instance.current_y + -6.0f,
                        0.0f, true, Settings.CREAM_COLOR);
                stringBuilder.append(end);
            }
            stringBuilder.append(' ');
            gl.setText(font, stringBuilder.toString());
            return gl.width;
        }
    }
}
