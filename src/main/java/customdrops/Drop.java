package customdrops;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.item.ItemStack;

@AllArgsConstructor
public class Drop {
	
	@Getter
	public BlockInfo block;

	public ArrayList<ItemStack> drops;
	
	public ArrayList<ItemStack> getDrops() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		for (ItemStack stack : drops)
			ret.add(stack.copy());
		return ret;
	}

}
