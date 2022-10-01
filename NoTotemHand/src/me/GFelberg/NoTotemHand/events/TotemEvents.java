package me.GFelberg.NoTotemHand.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.GFelberg.NoTotemHand.utils.TotemUtils;

public class TotemEvents implements Listener {

	public Set<Player> toggleplayers = new HashSet<Player>();
	public Map<Player, ItemStack> totem = new HashMap<Player, ItemStack>();

	@EventHandler
	public void onTotem(EntityResurrectEvent event) {

		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getEntity();

		if (totem.containsKey(p)) {
			p.getInventory().setItemInOffHand(totem.get(p));
			totem.remove(p);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {

		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getEntity();
		PlayerInventory inv = p.getInventory();
		Material item = Material.TOTEM_OF_UNDYING;
		ItemStack mainhand = inv.getItemInMainHand();
		ItemStack offhand = inv.getItemInOffHand();

		if (p.getHealth() - event.getFinalDamage() <= 0 && inv.contains(item)
				&& !(mainhand != null && mainhand.getType() == item)
				&& !(offhand != null && offhand.getType() == item)) {

			if (toggleplayers.contains(p)) {
				return;
			}

			totem.put(p, inv.getItemInOffHand());

			for (ItemStack totem : inv.getContents()) {
				if (totem != null) {
					if (totem.getType().equals(item)) {
						totem.setAmount(totem.getAmount() - 1);
						break;
					}
				}
			}
			inv.setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
		}
	}

	@EventHandler(ignoreCancelled = false)
	public void PlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack hand = p.getInventory().getItemInMainHand();

		if (p.isSneaking() && hand != null && hand.getType() == Material.TOTEM_OF_UNDYING) {

			if (toggleplayers.contains(p)) {
				toggleplayers.remove(p);
				p.sendMessage(TotemUtils.inventoryEnabled);
			} else {
				toggleplayers.add(p);
				p.sendMessage(TotemUtils.inventoryDisabled);
			}
		}
	}
}