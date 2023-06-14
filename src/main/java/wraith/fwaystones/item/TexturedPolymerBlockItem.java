package wraith.fwaystones.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.FabricWaystones;

public class TexturedPolymerBlockItem extends BlockItem implements PolymerItem {

    private final Item polymerItem;
    private PolymerModelData modelData;

    public TexturedPolymerBlockItem(Block block, Settings settings, Item polymerItem, String modelPath) {
        super(block, settings);
        this.polymerItem = polymerItem;
        this.registerPolymerModel(polymerItem, modelPath);
    }

    protected void registerPolymerModel(Item item, String modelPath) {
        this.modelData = PolymerResourcePackUtils.requestModel(item, new Identifier(FabricWaystones.MOD_ID, modelPath));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelData.value();
    }
}
