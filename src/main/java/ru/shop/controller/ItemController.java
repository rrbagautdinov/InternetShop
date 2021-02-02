package ru.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.shop.dto.ItemDto;
import ru.shop.entity.Item;
import ru.shop.exception.ItemNotFoundException;
import ru.shop.service.ItemService;


@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Page<ItemDto> findAllItems(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "5") int size) {
        if (page < 1) page = 1;
        return itemService.findAllItems(page, size);
    }

    @PostMapping
    public Item saveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) throws ItemNotFoundException {
        return itemService.updateItem(id, item);
    }

    @GetMapping("/{id}")
    public ItemDto findItemById(@PathVariable Long id) throws ItemNotFoundException {
        return new ItemDto(itemService.findItemById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }
}
