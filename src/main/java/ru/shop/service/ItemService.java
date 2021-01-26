package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.shop.dto.ItemDto;
import ru.shop.entity.Item;
import org.springframework.stereotype.Service;
import ru.shop.exception.ItemNotFoundException;
import ru.shop.repository.ItemRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<ItemDto> findAllItems(int page, int size) {
        return itemRepository.findAll(PageRequest.of(page - 1, size)).map(ItemDto::new);
    }

    public Item findItemById(Long id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Продукт не найден!"));
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item item) throws ItemNotFoundException {
        Item itemForUpdate = itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Продукт не найден!")
        );
        itemForUpdate.setName(item.getName());
        itemForUpdate.setPrice(item.getPrice());
        itemRepository.save(itemForUpdate);
        return itemForUpdate;
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
