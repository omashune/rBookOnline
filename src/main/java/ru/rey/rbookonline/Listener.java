package ru.rey.rbookonline;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * Created by Rey on 05.06.2020
 */
public class Listener implements org.bukkit.event.Listener {

    // Открытие инвенторя
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        Inventory inventory = e.getInventory();

        for (ItemStack item : inventory.getContents()) { // проходимся циклом по вещам и обновляем книги
            if (item == null) continue;

            updateBook(item);
        }
    }

    // shift + ПКМ
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!p.isSneaking()) return; // проверяем, если игрок сидит на шифте
        if (e.getAction() != Action.RIGHT_CLICK_AIR &&
                e.getAction() != Action.RIGHT_CLICK_BLOCK) return; // проверяем, если "действие" не правый клик

        // тут все и так очевидно
        ItemStack item = e.getItem();

        if (item == null) return;
        updateBook(item);
    }

    private String extractAuthor(BookMeta meta) {
        // реплейсим регекс и тримим
        return meta.getAuthor().replaceAll("(§a\\(онлайн\\)|§c\\(оффлайн\\))", "").trim();
    }

    private void updateBook(ItemStack item) {
        if (item.getType() != Material.WRITTEN_BOOK) return; // Проверяем подписанная ли это книга
        BookMeta bookMeta = (BookMeta) item.getItemMeta(); // Получаем мету

        String authorName = extractAuthor(bookMeta); // достаем имя автора

        bookMeta.setAuthor(authorName + (Bukkit.getPlayer(authorName) == null ? " §c(оффлайн)" : " §a(онлайн)")); // устанавливаем автора

        item.setItemMeta(bookMeta); // Устанавливаем мету
    }


}
