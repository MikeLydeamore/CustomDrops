package customdrops.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CustomItemStackJson implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack>
{
    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonHelper helper = new JsonHelper(json);
        
        String name = helper.getString("name");
        int amount = helper.getNullableInteger("amount", 1);
        int meta = helper.getNullableInteger("meta", 0);
        
        Item item = Item.getByNameOrId(name);
        
        return new ItemStack(item, amount, meta);
    }
    
    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.addProperty("name", src.getItem().getRegistryName().toString());
        jsonObject.addProperty("amount", src.stackSize);
        jsonObject.addProperty("meta", src.getItemDamage());
        
        return jsonObject;
    }
}
