package net.mcreator.sweetapocalpysebiome.client.model;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
/*
 * NOTE: fixed compile error from the raw Blockbench export (<T extends creeper>).
 * This model is registered in SweetapocalpysebiomeModModels but not used by any renderer;
 * the entity uses ModelStrawberryVineCreeper. If it was pasted by accident it can be deleted
 * (and its layer registration removed) from within MCreator.
 */
public class ModelStrawberry_Vine_Creeper<T extends Creeper> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("sweetapocalpysebiome", "model_strawberry_vine_creeper"), "main");
	public final ModelPart Creeper;
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart lEGS;
	public final ModelPart leg1;
	public final ModelPart leg2;
	public final ModelPart leg3;
	public final ModelPart leg4;
	public final ModelPart Bush;
	public final ModelPart Bush1;
	public final ModelPart Bush2;
	public final ModelPart Bush3;
	public final ModelPart Bush4;

	public ModelStrawberry_Vine_Creeper(ModelPart root) {
		this.Creeper = root.getChild("Creeper");
		this.head = this.Creeper.getChild("head");
		this.body = this.Creeper.getChild("body");
		this.lEGS = this.Creeper.getChild("lEGS");
		this.leg1 = this.lEGS.getChild("leg1");
		this.leg2 = this.lEGS.getChild("leg2");
		this.leg3 = this.lEGS.getChild("leg3");
		this.leg4 = this.lEGS.getChild("leg4");
		this.Bush = root.getChild("Bush");
		this.Bush1 = this.Bush.getChild("Bush1");
		this.Bush2 = this.Bush.getChild("Bush2");
		this.Bush3 = this.Bush.getChild("Bush3");
		this.Bush4 = this.Bush.getChild("Bush4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Creeper = partdefinition.addOrReplaceChild("Creeper", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));
		PartDefinition head = Creeper.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(4.4F, 0.4F, 4.4F, -8.8F, -8.8F, -8.8F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = Creeper.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0).addBox(4.4F, 12.4F, 2.4F, -8.8F, -12.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition lEGS = Creeper.addOrReplaceChild("lEGS", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 4.0F));
		PartDefinition leg1 = lEGS.addOrReplaceChild("leg1",
				CubeListBuilder.create().texOffs(0, 0).addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leg2 = lEGS.addOrReplaceChild("leg2",
				CubeListBuilder.create().texOffs(0, 0).addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, 0.0F));
		PartDefinition leg3 = lEGS.addOrReplaceChild("leg3",
				CubeListBuilder.create().texOffs(0, 0).addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, -8.0F));
		PartDefinition leg4 = lEGS.addOrReplaceChild("leg4",
				CubeListBuilder.create().texOffs(0, 0).addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, -8.0F));
		PartDefinition Bush = partdefinition.addOrReplaceChild("Bush", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition Bush1 = Bush.addOrReplaceChild("Bush1", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, -10.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Bush2 = Bush.addOrReplaceChild("Bush2", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, -10.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 0.0F));
		PartDefinition Bush3 = Bush.addOrReplaceChild("Bush3", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, -10.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Bush4 = Bush.addOrReplaceChild("Bush4", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -10.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		Creeper.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		Bush.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}