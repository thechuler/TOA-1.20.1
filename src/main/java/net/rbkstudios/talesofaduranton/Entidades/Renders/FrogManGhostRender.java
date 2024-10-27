package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManBeastEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManGhostEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManBeastModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManGhostModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.parser.Entity;


public class FrogManGhostRender<type extends FrogManGhostEntity> extends MobRenderer<type, FrogManGhostModel<type>> {



    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmanghost.png");



    public FrogManGhostRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManGhostModel<>(pContext.bakeLayer(FrogManGhostModel.LAYER_LOCATION)), 0.5f);

    }


    @Nullable
    @Override
    protected RenderType getRenderType(type pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {

        return RenderType.entityTranslucent(TEXTURAS);
    }


    @Override
    protected int getBlockLightLevel(type pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    protected int getSkyLightLevel(type pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    public void render(FrogManGhostEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {
        return TEXTURAS;
    }
}
