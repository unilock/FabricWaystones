package wraith.fwaystones.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.block.WaystoneBlock;

import java.util.List;

public class WaystoneItem extends TexturedPolymerBlockItem {

    public WaystoneItem(WaystoneBlock block, Settings settings) {
        super(block, settings, block.getStyle().lower().getBlock().asItem(), "item/" + Registries.BLOCK.getId(block).getPath());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        NbtCompound tag = stack.getSubNbt("BlockEntityTag");
        if (tag == null) {
            return;
        }
        String name = tag.getString("waystone_name");
        boolean global = tag.getBoolean("waystone_is_global");
        tooltip.add(Text.translatable(
                "fwaystones.waystone_tooltip.name",
                Text.literal(name).styled(style ->
                        style.withColor(TextColor.parse(Text.translatable("fwaystones.waystone_tooltip.name.arg_color").getString()))
                )
        ));
        tooltip.add(Text.translatable("fwaystones.waystone_tooltip.global").append(" ")
                .append(Text.translatable("fwaystones.waystone_tooltip.global_" + (global ? "on" : "off"))));
    }
}
