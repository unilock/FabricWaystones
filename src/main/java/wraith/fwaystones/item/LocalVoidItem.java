package wraith.fwaystones.item;

import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.FabricWaystones;
import wraith.fwaystones.registry.ItemRegistry;

public class LocalVoidItem extends VoidItem {

    private PolymerModelData modelDataInert, modelDataActive;

    public LocalVoidItem(Settings settings) {
        super(settings, Items.ENDER_PEARL, "item/local_void");
    }

    @Override
    protected void registerPolymerModel(Item item, String modelPath) {
        super.registerPolymerModel(item, modelPath);

        this.modelDataInert = PolymerResourcePackUtils.requestModel(item, new Identifier(FabricWaystones.MOD_ID, modelPath + "_inert"));
        this.modelDataActive = PolymerResourcePackUtils.requestModel(item, new Identifier(FabricWaystones.MOD_ID, modelPath + "_active"));
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        if (ItemRegistry.hasLearned(itemStack)) {
            return this.modelDataActive.value();
        } else {
            return this.modelDataInert.value();
        }
    }
}
