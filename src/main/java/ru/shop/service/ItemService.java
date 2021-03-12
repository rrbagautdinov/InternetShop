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
import ru.shop.soap.products.Products;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    public static final Function<Item, Products> functionItemToProducts = item -> {
        Products products = new Products();
        products.setId(item.getId());
        products.setName(item.getName());
        products.setPrice(item.getPrice());
        return products;
    };

    public List<Products> getAllProducts() {
        return itemRepository.findAll().stream().map(functionItemToProducts).collect(Collectors.toList());
    }
}
