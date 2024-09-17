package net.rbkstudios.talesofaduranton.Entidades.Renders;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManTropicalEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManTropicalModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


@OnlyIn(Dist.CLIENT)
public class FrogmanTropicalEyesLayer<T extends FrogManTropicalEntity> extends EyesLayer<T, FrogManTropicalModel<T>> {
    private static final RenderType FROGMAN_EYES = RenderType.eyes(new ResourceLocation(TalesOfAduranton.MODID,"textures/entity/frogmantropicaleyes.png"));

    public FrogmanTropicalEyesLayer(RenderLayerParent<T, FrogManTropicalModel<T>> pRenderer) {
        super(pRenderer);
    }

    public RenderType renderType() {
        return FROGMAN_EYES;
    }
}
