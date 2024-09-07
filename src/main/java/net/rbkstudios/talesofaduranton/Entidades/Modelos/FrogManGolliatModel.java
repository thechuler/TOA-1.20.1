package net.rbkstudios.talesofaduranton.Entidades.Modelos;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManGolliatAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManGolliatEntity;

public class FrogManGolliatModel<T extends FrogManGolliatEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frogmangolliatmodel"), "main");
	private final ModelPart Goliat;


	public FrogManGolliatModel(ModelPart root) {
		this.Goliat = root.getChild("Goliat");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Goliat = partdefinition.addOrReplaceChild("Goliat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pierna1 = Goliat.addOrReplaceChild("pierna1", CubeListBuilder.create().texOffs(104, 42).addBox(-6.0F, -1.0F, -4.0F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 18.0F, 11.0F));

		PartDefinition Pierna2 = Goliat.addOrReplaceChild("Pierna2", CubeListBuilder.create().texOffs(98, 68).addBox(0.0F, 0.0F, -4.0F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 17.0F, 11.0F));

		PartDefinition Torso = Goliat.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 7.0F));

		PartDefinition Brazo1 = Torso.addOrReplaceChild("Brazo1", CubeListBuilder.create().texOffs(0, 83).addBox(-8.0F, -1.0F, -6.0F, 8.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -15.0F, -7.0F));

		PartDefinition Artinf = Brazo1.addOrReplaceChild("Artinf", CubeListBuilder.create().texOffs(40, 97).addBox(-7.0F, 0.0F, -5.0F, 7.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition Brazo2 = Torso.addOrReplaceChild("Brazo2", CubeListBuilder.create().texOffs(56, 68).addBox(0.0F, -1.0F, -6.0F, 9.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -15.0F, -7.0F));

		PartDefinition Artsup2 = Brazo2.addOrReplaceChild("Artsup2", CubeListBuilder.create().texOffs(88, 87).addBox(0.0F, 0.0F, -5.0F, 7.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition Sup = Torso.addOrReplaceChild("Sup", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, -3.0F));

		PartDefinition cube_r1 = Sup.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.4089F, -13.1863F, 24.0F, 18.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.9F, 7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Mid = Sup.addOrReplaceChild("Mid", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 5.0F));

		PartDefinition cube_r2 = Mid.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 31).addBox(-10.0F, -10.9369F, -10.2262F, 20.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition Inf = Mid.addOrReplaceChild("Inf", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 3.0F));

		PartDefinition cube_r3 = Inf.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(58, 46).addBox(-8.0F, -8.0495F, -8.133F, 16.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Cabeza = Goliat.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition MandibulaSup = Cabeza.addOrReplaceChild("MandibulaSup", CubeListBuilder.create().texOffs(62, 19).addBox(-8.0F, -10.0F, -12.0F, 16.0F, 11.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(90, 3).addBox(-10.0F, -10.0F, -12.0F, 2.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(74, 0).addBox(8.0F, -10.0F, -12.0F, 2.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition MandibulaInf = Cabeza.addOrReplaceChild("MandibulaInf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r4 = MandibulaInf.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 60).addBox(-8.0F, -24.0F, -6.0F, 16.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, -2.0F, 0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Goliat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.Goliat;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(FrogManGolliatAnimaciones.WALK, v, v1, 1f, 2.5f);
		this.animate(((FrogManGolliatEntity) t).idleAnimationState, FrogManGolliatAnimaciones.IDLE, v2, 1f);
		this.animate(((FrogManGolliatEntity) t).gruñirAnimationState, FrogManGolliatAnimaciones.ATTACK2, v2, 1f);
		this.animate(((FrogManGolliatEntity) t).atacarAnimationState, FrogManGolliatAnimaciones.ATTACK1, v2, 1f);

	}
}