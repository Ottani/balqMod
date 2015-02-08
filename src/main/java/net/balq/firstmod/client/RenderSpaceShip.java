package net.balq.firstmod.client;

import net.balq.firstmod.entity.EntitySpaceShip;
import net.balq.firstmod.util.ModInformation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpaceShip extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(ModInformation.ID, "textures/models/spaceship.png");
	private static final ResourceLocation chargedTexture = new ResourceLocation(ModInformation.ID, "textures/models/spaceship_charged.png");

    protected ModelSpaceship model;

    public RenderSpaceShip()
    {
        shadowSize = 0.5F;
        model = new ModelSpaceship();
    }

    public void renderSpaceship(EntitySpaceShip spaceship, double x, double y, double z, float yaw, float partialTickTime)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);     
        bindEntityTexture(spaceship);
        model.render(spaceship, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }


    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime)
    {
        this.renderSpaceship((EntitySpaceShip)entity, x, y, z, yaw, partialTickTime);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntitySpaceShip)entity).isCharged() ? chargedTexture : texture;
	}
}