package customdrops;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import customdrops.json.CustomBlockInfoJson;
import customdrops.json.CustomItemStackJson;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandler {

	private static ArrayList<Drop> dropList = new ArrayList<Drop>();

	@SubscribeEvent
	public void doDrops(HarvestDropsEvent event) {
		for (Drop drop : dropList) {
			BlockInfo info = new BlockInfo(event.getState());
			if (drop.getBlock().equals(info)) {
				event.getDrops().clear();
				event.getDrops().addAll(drop.getDrops());
				event.setDropChance(1.0f);
			}
		}
	}

	private static Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(ItemStack.class, new CustomItemStackJson())
			.registerTypeAdapter(BlockInfo.class, new CustomBlockInfoJson()).create();

	public static void loadJson(File file) {
		dropList.clear();

		if (file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				List<Drop> gsonInput = gson.fromJson(fr, new TypeToken<List<Drop>>() {
				}.getType());

				dropList.addAll(gsonInput);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			registerExample();
			saveJson(file);
		}
	}

	public static void saveJson(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			gson.toJson(dropList, fw);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void registerExample() {
		ArrayList<ItemStack> example = new ArrayList<ItemStack>();
		example.add(new ItemStack(Items.IRON_INGOT, 2));
		dropList.add(new Drop(new BlockInfo(Blocks.IRON_ORE.getDefaultState()), example));
	}

}
