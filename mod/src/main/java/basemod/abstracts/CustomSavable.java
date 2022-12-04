package basemod.abstracts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface CustomSavable<T> extends CustomSavableRaw
{
    Gson saveFileGson = new Gson();

    T onSave();
    void onLoad(T object);

    default Type savedType()
    {
        Class<?> clz = getClass();
        for (Type t : clz.getGenericInterfaces()) {
            if (t instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) t;
                if (pt.getRawType().equals(CustomSavable.class)) {
                    return pt.getActualTypeArguments()[0];
                }
            }
        }

        throw new RuntimeException("Implement [Type savedType()]");
    }

    @Override
    default JsonElement onSaveRaw() {
        return saveFileGson.toJsonTree(onSave());
    }

    @Override
    default void onLoadRaw(JsonElement value) {
        if (value != null) {
            T parsed = saveFileGson.fromJson(value, savedType());
            onLoad(parsed);
        } else {
            onLoad(null);
        }
    }
}