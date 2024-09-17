package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManTropicalEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManTropicalModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManTropicalRender<type extends FrogManTropicalEntity> extends MobRenderer<type, FrogManTropicalModel<type>> {


    FrogManTropicalEntity entity;

    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmantropical.png");




    public FrogManTropicalRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManTropicalModel<>(pContext.bakeLayer(FrogManTropicalModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new FrogmanTropicalEyesLayer(this));
    }





    @Override
    public void render(FrogManTropicalEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        entity = pEntity;
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {
            return TEXTURAS;
    }
}
